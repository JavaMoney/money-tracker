package com.skizware.money.tracker.persistence.repository.impl;

import com.skizware.money.tracker.persistence.repository.UserRepository;
import com.skizware.money.tracker.persistence.document.UserMoneyTrackers;
import com.skizware.user.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for Users and their money trackers.
 */
public class UserRepositoryImpl implements UserRepository {

    public static final String SQL_USER_INSERT = "INSERT INTO USER (EMAIL_ADDRESS) VALUES ('%s')";
    public static final String SQL_QUERY_USER_BY_ID = "SELECT * FROM USER u WHERE u.id = %d";
    public static final String SQL_FIND_ID_FOR_USER_BY_EMAIL = "SELECT id FROM USER WHERE EMAIL_ADDRESS = '%s'";
    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL_ADDRESS = '%s'";
    public static final String MONGO_MONEY_TRACKERS_BY_EMAIL = "{userEmailAddress: \"%s\"}";
    private final JdbcTemplate jdbcTemplate;
    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, MongoTemplate mongoTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Integer save(final User user) {

        Integer userId = jdbcTemplate.update(String.format(SQL_USER_INSERT, user.getEmailAddress()));
        saveOrUpdateUserMoneyTrackers(user);

        return userId;

    }

    @Override
    public User findById(final Integer id) {
        User user = jdbcTemplate.queryForObject(String.format(SQL_QUERY_USER_BY_ID, id), new UserRowMapper());


        return user;
    }

    @Override
    public User findByEmail(final String emailAddress){
        User user = jdbcTemplate.queryForObject(String.format(SQL_FIND_USER_BY_EMAIL, emailAddress), new UserRowMapper());
        user.addAllMoneyTrackers(findUserMoneyTrackersByEmail(emailAddress).getMoneyTrackers());


        return user;
    }

    private class UserRowMapper implements RowMapper<User> {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getString("EMAIL_ADDRESS"));
            }
    }

    private void saveOrUpdateUserMoneyTrackers(User user){
        UserMoneyTrackers userMoneyTrackers =  findUserMoneyTrackersByEmail(user.getEmailAddress());
        if(userMoneyTrackers == null){
            userMoneyTrackers = new UserMoneyTrackers(user.getEmailAddress(), user.getMoneyTrackers());
        }else{
            userMoneyTrackers.setMoneyTrackers(user.getMoneyTrackers());
        }

        mongoTemplate.save(userMoneyTrackers);
    }

    private UserMoneyTrackers findUserMoneyTrackersByEmail(String email){
        Query findByEMailAddress = new BasicQuery(String.format(MONGO_MONEY_TRACKERS_BY_EMAIL, email));
        return mongoTemplate.findOne(findByEMailAddress, UserMoneyTrackers.class);
    }



}

package com.skizware.money.tracker.repository.relational.impl;

import com.skizware.money.tracker.repository.relational.UserRepository;
import com.skizware.user.User;
import org.h2.jdbc.JdbcResultSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/14
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRepositoryImpl implements UserRepository {

    public static final String SQL_USER_INSERT = "INSERT INTO USER (EMAIL_ADDRESS) VALUES ('%s')";
    public static final String SQL_QUERY_USER_BY_ID = "SELECT * FROM USER u WHERE u.id = %d";
    public static final String SQL_FIND_ID_FOR_USER_BY_EMAIL = "SELECT id FROM USER WHERE EMAIL_ADDRESS = '%s'";
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer save(final User user) {

        return jdbcTemplate.update(String.format(SQL_USER_INSERT, user.getEmailAddress()));

    }

    @Override
    public User findById(final Integer id) {

        return jdbcTemplate.queryForObject(String.format(SQL_QUERY_USER_BY_ID, id), new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getString("EMAIL_ADDRESS"));
            }
        });
    }

    @Override
    public Integer findIdForUser(final User user){
        return jdbcTemplate.queryForObject(String.format(SQL_FIND_ID_FOR_USER_BY_EMAIL, user.getEmailAddress()), Integer.class);
    }

}

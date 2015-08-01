package com.skizware.money.tracker.repository.relational.impl;

import com.skizware.money.tracker.repository.relational.UserRepository;
import com.skizware.user.User;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/14
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRepositoryImpl implements UserRepository {

    public static final String SQL_USER_INSERT = "INSERT INTO USER (EMAIL_ADDRESS) VALUES ('%s')";
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
        return new User("djohnanderson@gmail.com");
    }
}

package com.skizware.money.tracker.repository;

import com.skizware.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/14
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {

    Integer save(final User user);

    User findById(final Integer id);

    Integer findIdForUser(final User user);

    User findByEmail(final String emailAddress);
}

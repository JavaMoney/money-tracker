package com.skizware.money.tracker.persistence.repository;

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

    User findByEmail(final String emailAddress);

    void saveOrUpdateUserMoneyTrackers(User user);
}

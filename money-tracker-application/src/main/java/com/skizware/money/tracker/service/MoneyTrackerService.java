package com.skizware.money.tracker.service;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/08/23
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MoneyTrackerService {

    public User getUserByEmail(final String emailAddress);

    public User enrollUser(final String emailAddress);

    public List<MoneyTracker> createMoneyTracker(final String emailAddress, final Double initialTrackerAmount);

}

package com.skizware.user;

import com.skizware.money.tracker.MoneyTracker;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/14
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private final String emailAddress;
    private Map<Date, MoneyTracker> moneyTrackers;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public final String getEmailAddress() {
        return emailAddress;
    }

    public final void addMoneyTracker(final MoneyTracker moneyTracker) {
        if (moneyTrackers == null) {
            moneyTrackers = new TreeMap<Date, MoneyTracker>();
        }
        moneyTrackers.put(moneyTracker.getDateTimeCreated(), moneyTracker);
    }
}

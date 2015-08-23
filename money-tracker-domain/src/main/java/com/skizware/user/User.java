package com.skizware.user;

import com.skizware.money.tracker.domain.MoneyTracker;

import java.util.*;

/**
 * User class - holds the list of money trackers this user has created, and is identified by their email address.
 */
public class User {

    private final String emailAddress;
    private List<MoneyTracker> moneyTrackers;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
        init();
    }

    private void init(){
        moneyTrackers = new ArrayList<MoneyTracker>();
    }

    public final String getEmailAddress() {
        return emailAddress;
    }

    public final void addMoneyTracker(final MoneyTracker moneyTracker) {
        moneyTrackers.add(moneyTracker);
    }

    public final List<MoneyTracker> getMoneyTrackers(){
        return moneyTrackers;
    }

    public final MoneyTracker getMoneyTrackerByUUID(final UUID uuid){
        MoneyTracker matchingMoneyTracker = null;
        Iterator it = moneyTrackers.iterator();
        while(matchingMoneyTracker == null && it.hasNext()){
            MoneyTracker moneyTracker = (MoneyTracker)it.next();
            matchingMoneyTracker = moneyTracker.matches(uuid) ? moneyTracker : null;
        }

        return matchingMoneyTracker;
    }

    public final void addAllMoneyTrackers(final List<MoneyTracker> aMoneyTrackers){
        moneyTrackers.addAll(aMoneyTrackers);
    }
}

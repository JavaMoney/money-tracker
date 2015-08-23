package com.skizware.money.tracker.repository.model;

import com.skizware.money.tracker.MoneyTracker;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Mongo document for storing the user's money trackers against their email address.
 */
@Document(collection = "userMoneyTrackers")
public class UserMoneyTrackers {

    String userEmailAddress;

    List<MoneyTracker> moneyTrackers;

    public UserMoneyTrackers(String userEmailAddress, List<MoneyTracker> moneyTrackers) {
        this.userEmailAddress = userEmailAddress;
        this.moneyTrackers = moneyTrackers;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public List<MoneyTracker> getMoneyTrackers() {
        return moneyTrackers;
    }

    public void setMoneyTrackers(List<MoneyTracker> moneyTrackers) {
        this.moneyTrackers = moneyTrackers;
    }
}

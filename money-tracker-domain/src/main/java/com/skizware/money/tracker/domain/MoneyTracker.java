package com.skizware.money.tracker.domain;

import java.util.*;

/**
 * Hello world!
 */
public class MoneyTracker {

    public static final String UNCATEGORIZED = "Uncategorized";
    private Double moneyForTheMonth;
    private Map<String, List<MoneyTransaction>> moneyTransactions;
    private Date dateTimeCreated;

    public MoneyTracker(Double moneyForTheMonth) {
        this.moneyForTheMonth = moneyForTheMonth;
        init();
    }

    private void init() {
        moneyTransactions = new HashMap<String, List<MoneyTransaction>>();
        dateTimeCreated = new Date();
    }

    public Double getMoneyForTheMonth() {
        return moneyForTheMonth;
    }

    public MoneyTracker addTransaction(Double transactionAmount) {
        addTransaction(transactionAmount, UNCATEGORIZED);
        return this;
    }

    public MoneyTracker addTransaction(Double transactionAmount, String category) {
        if (!moneyTransactions.containsKey(category)) {
            moneyTransactions.put(category, new LinkedList<MoneyTransaction>());
        }

        moneyTransactions.get(category).add(new MoneyTransaction(transactionAmount));
        return this;
    }

    public Double getTotalRemaining() {
        Double total = moneyForTheMonth;
        for (List<MoneyTransaction> categoryTransactions : moneyTransactions.values()) {
            for (MoneyTransaction transaction : categoryTransactions) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public Double getMoneySpentOn(String category) {
        Double total = 0D;
        if (moneyTransactions.containsKey(category)) {
            for (MoneyTransaction transaction : moneyTransactions.get(category)) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public Date getDateTimeCreated() {
        return dateTimeCreated;
    }


}

package com.skizware.money.tracker.domain;

import java.util.*;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * Hello world!
 */
public class MoneyTracker {
	private static final CurrencyUnit TRACK_CURR = Monetary.getCurrency("EUR");
    public static final String UNCATEGORIZED = "Uncategorized";
    private final UUID uuid;
    private final MonetaryAmount moneyForTheMonth;
    private Map<String, List<MoneyTransaction>> moneyTransactions;
    private Date dateTimeCreated;

    public MoneyTracker(MonetaryAmount moneyForTheMonth) {
        this.moneyForTheMonth = moneyForTheMonth;
        this.uuid = UUID.randomUUID();
        init();
    }

    private void init() {
        moneyTransactions = new HashMap<String, List<MoneyTransaction>>();
        dateTimeCreated = new Date();
    }

    public MonetaryAmount getMoneyForTheMonth() {
        return moneyForTheMonth;
    }

    public MoneyTracker addTransaction(MonetaryAmount transactionAmount) {
        addTransaction(transactionAmount, UNCATEGORIZED);
        return this;
    }

    public MoneyTracker addTransaction(MonetaryAmount transactionAmount, String category) {
        if (!moneyTransactions.containsKey(category)) {
            moneyTransactions.put(category, new LinkedList<MoneyTransaction>());
        }

        moneyTransactions.get(category).add(new MoneyTransaction(transactionAmount));
        return this;
    }

    public MonetaryAmount getTotalRemaining() {
    	MonetaryAmount total = moneyForTheMonth;
        for (List<MoneyTransaction> categoryTransactions : moneyTransactions.values()) {
            for (MoneyTransaction transaction : categoryTransactions) {
                total = total.add(transaction.getAmount());
            }
        }

        return total;
    }

    public MonetaryAmount getMoneySpentOn(String category) {
    	MonetaryAmount total = FastMoney.of(0D, TRACK_CURR);
        if (moneyTransactions.containsKey(category)) {
            for (MoneyTransaction transaction : moneyTransactions.get(category)) {
            	 total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public Map<String, List<MoneyTransaction>> getTransactionHistory(){
        return this.moneyTransactions;
    }

    public Date getDateTimeCreated() {
        return dateTimeCreated;
    }

    public boolean matches(final UUID inputUUID){
        return uuid.equals(inputUUID);
    }

    public UUID getUuid() {
        return uuid;
    }
}

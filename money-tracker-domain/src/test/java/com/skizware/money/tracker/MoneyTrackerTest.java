package com.skizware.money.tracker;

import com.skizware.money.tracker.domain.MoneyTracker;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * In order to keep track of how much money I spend in a month
 * As a user
 * I want to be able to capture how much money I have left for the month
 * And I want to be able to capture whenever I spend money, and against what category I've spent it on.
 */
public class MoneyTrackerTest extends TestCase {

    @Test
    public void testAddSingleTransaction() {

        //Given some money tracker with 50 units of money to track
        //When a transaction is added of -25 units
        MoneyTracker moneyTracker = new MoneyTracker(50D);
        moneyTracker.addTransaction(-25D);

        //Then the total left should be 25
        Double remaining = moneyTracker.getTotalRemaining();

        assertEquals("Incorrect monetary recon", 25D, remaining);

    }

    @Test
    public void testMultipleTransactions() {
        //Given some money tracker with 10000 monies to track
        //When transactions of -99.23 for airtime, -55.26 for groceries, -101.2 for airtime and 22 for income are added
        MoneyTracker moneyTracker = createMoneyTrackerScenario(10000D);
        moneyTracker.addTransaction(-99.23, "Airtime")
                .addTransaction(-55.26, "Groceries")
                .addTransaction(-101.2, "Airtime")
                .addTransaction(22D, "Income")
                .addTransaction(-25D, "meh");


        //Then the total left should decrease by the amount on the transaction
        Double remaining = moneyTracker.getTotalRemaining();
        assertEquals("Incorrect monetary recon", 9741.31D, remaining);
        assertEquals("Money for the month should remain unchanged", 10000D, moneyTracker.getMoneyForTheMonth());

        //And the airtime category should have a total of -200.43
        assertEquals("Incorrect amount tracked for airtime spent", -200.43D, moneyTracker.getMoneySpentOn("Airtime"));

        //And the groceries category should have a total of -55.26
        assertEquals("Incorrect amount tracked for groceries spent", -55.26D, moneyTracker.getMoneySpentOn("Groceries"));

        //And the income category should have a total of 22
        assertEquals("Incorrect amount tracked for income received", 22D, moneyTracker.getMoneySpentOn("Income"));
    }

    private MoneyTracker createMoneyTrackerScenario(final Double initialCashAllowance, final Double... transactionAmounts) {
        MoneyTracker moneyTracker = new MoneyTracker(initialCashAllowance);

        for (Double transactionAmount : transactionAmounts) {
            moneyTracker.addTransaction(transactionAmount);
        }

        return moneyTracker;
    }

}

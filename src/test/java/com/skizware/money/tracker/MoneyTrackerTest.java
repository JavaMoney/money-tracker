package com.skizware.money.tracker;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

/**
 * In order to keep track of how much money I spend in a month
 * As a user
 * I want to be able to capture how much money I have left for the month
 * And I want to be able to capture whenever I spend money, and against what category I've spent it on.
 */
public class MoneyTrackerTest extends TestCase
{

    @Test
    public void testAddSingleTransaction(){

        //Given some money tracker with 50 units of money to track
        //When a transaction is added of -25 units
        MoneyTracker moneyTracker = createMoneyTrackerScenario(50D, -25D);

        //Then the total left should be 25
        Double remaining = moneyTracker.getTotalRemaining();

        assertEquals("Incorrect monetary recon", 25D, remaining);

    }

    @Test
    public void testMultipleTransactions(){
        //Given some money tracker with 10000 monies to track
        //When transactions of -99.23, -55.26, -101.2 and 22 are added
        MoneyTracker moneyTracker = createMoneyTrackerScenario(10000D, -25D, -99.23D, -55.26D, -101.2D, 22D);


        //Then the total left should decrease by the amount on the transaction
        Double remaining = moneyTracker.getTotalRemaining();

        assertEquals("Incorrect monetary recon", 9741.31D, remaining);
    }

    private MoneyTracker createMoneyTrackerScenario(final Double initialCashAllowance, final Double... transactionAmounts) {
        MoneyTracker moneyTracker = new MoneyTracker(initialCashAllowance);

        for (Double transactionAmount : transactionAmounts) {
            moneyTracker.addTransaction(new MoneyTransaction(transactionAmount));
        }

        return moneyTracker;
    }

}

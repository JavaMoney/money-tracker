package com.skizware.money.tracker;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.user.User;
import junit.framework.TestCase;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.Test;

import java.util.UUID;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

/**
 * In order to keep track of how much money I spend in a month
 * As a user
 * I want to be able to capture how much money I have left for the month
 * And I want to be able to capture whenever I spend money, and against what category I've spent it on.
 */
public class MoneyTrackerTest extends TestCase {
	private static final CurrencyUnit TEST_CURRENCY = Monetary.getCurrency("EUR");
    @Test
    public void testAddSingleTransaction() {

        //Given some money tracker with 50 units of money to track
        //When a transaction is added of -25 units
        MoneyTracker moneyTracker = new MoneyTracker(Money.of(50D, TEST_CURRENCY));
        moneyTracker.addTransaction(Money.of(-25D, TEST_CURRENCY));

        //Then the total left should be 25
        MonetaryAmount remaining = moneyTracker.getTotalRemaining();

        assertEquals("Incorrect monetary recon", Money.of(25D, TEST_CURRENCY), remaining);
    }

    @Test
    public void testMultipleTransactions() {
        //Given some money tracker with 10000 monies to track
        //When transactions of -99.23 for airtime, -55.26 for groceries, -101.2 for airtime and 22 for income are added
        MoneyTracker moneyTracker = createMoneyTrackerScenario(createMoney(10000D));
        moneyTracker.addTransaction(createMoney(-99.23), "Airtime")
                .addTransaction(createMoney(-55.26), "Groceries")
                .addTransaction(createMoney(-101.2), "Airtime")
                .addTransaction(createMoney(22D), "Income")
                .addTransaction(createMoney(-25D), "meh");


        //Then the total left should decrease by the amount on the transaction
        MonetaryAmount remaining = moneyTracker.getTotalRemaining();
        assertEquals("Incorrect monetary recon", createMoney(9741.31D), remaining);
        assertEquals("Money for the month should remain unchanged", createMoney(10000D), moneyTracker.getMoneyForTheMonth());

        //And the airtime category should have a total of -200.43
        assertEquals("Incorrect amount tracked for airtime spent", createMoney(-200.43D), moneyTracker.getMoneySpentOn("Airtime"));

        //And the groceries category should have a total of -55.26
        assertEquals("Incorrect amount tracked for groceries spent", createMoney(-55.26D), moneyTracker.getMoneySpentOn("Groceries"));

        //And the income category should have a total of 22
        assertEquals("Incorrect amount tracked for income received", createMoney(22D), moneyTracker.getMoneySpentOn("Income"));
    }

    @Test
    public void testUserUUIDLookup_findsMatchingTracker(){
        User user = new User("someEmail@test.com");
        MoneyTracker moneyTracker = new MoneyTracker(createMoney(5000D));
        user.addMoneyTracker(moneyTracker);
        UUID uuid = moneyTracker.getUuid();

        moneyTracker = new MoneyTracker(createMoney(700D));
        user.addMoneyTracker(moneyTracker);

        moneyTracker = new MoneyTracker(createMoney(19D));
        user.addMoneyTracker(moneyTracker);

        moneyTracker = user.getMoneyTrackerByUUID(uuid);

        assertNotNull(moneyTracker);
        assertEquals(createMoney(5000D), moneyTracker.getMoneyForTheMonth());

        moneyTracker = user.getMoneyTrackerByUUID(UUID.randomUUID());
        assertNull(moneyTracker);
    }

    private MoneyTracker createMoneyTrackerScenario(final MonetaryAmount initialCashAllowance, final MonetaryAmount... transactionAmounts) {
        MoneyTracker moneyTracker = new MoneyTracker(initialCashAllowance);

        for (MonetaryAmount transactionAmount : transactionAmounts) {
            moneyTracker.addTransaction(transactionAmount);
        }

        return moneyTracker;
    }

   private MonetaryAmount createMoney(final Double number) {
	   return FastMoney.of(number, TEST_CURRENCY);
   }
}

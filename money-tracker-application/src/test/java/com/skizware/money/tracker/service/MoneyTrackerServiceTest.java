package com.skizware.money.tracker.service;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.user.User;
import junit.framework.TestCase;

import org.javamoney.moneta.FastMoney;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.skizware.money.tracker.service.impl.MoneyTrackerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

/**
 * Tests for the application logic.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ApplicationTest-config.xml")
public class MoneyTrackerServiceTest extends TestCase {

	private static final CurrencyUnit TEST_CURRENCY = Monetary.getCurrency("EUR");
    private static final String TEST_EMAIL_1 = "testEmail@gmail.com";
    private static final String TEST_EMAIL_2 = "djanderson@test.com";

    @Autowired
    private MoneyTrackerServiceImpl moneyTrackerService;

    public void setupUser(){
        User newUser = moneyTrackerService.enrollUser(TEST_EMAIL_1);
        MoneyTracker moneyTracker = moneyTrackerService.createUserMoneyTracker(newUser, FastMoney.of(5000D, TEST_CURRENCY));
        moneyTracker.addTransaction(FastMoney.of(-500D, TEST_CURRENCY), "Stuff");
        moneyTrackerService.updateUserMoneyTrackers(newUser);
    }

    @Test
    public void testGetUserAccount_Positive_UserExists() {
        //Given a previous user with email address testEmail@gmail.com and existing money tracker.
        setupUser();

        //When the user is retrieved by email address
        User retrievedUser = moneyTrackerService.getUserByEmail(TEST_EMAIL_1);

        //The correct user and money tracker details should be returned
        assertEquals("Incorrect email address", TEST_EMAIL_1, retrievedUser.getEmailAddress());
        assertEquals("User should have 1 moneyTracker", 1, retrievedUser.getMoneyTrackers().size());
    }

    @Test
    public void testEnrollUser() {
        //Given no existing user
        //When enrollUser is called, a user should be created and returned
        User createdUser = moneyTrackerService.enrollUser(TEST_EMAIL_2);

        assertNotNull("A user should have been created and returned", createdUser);
        assertEquals("Email should match the enrollment email", TEST_EMAIL_2, createdUser.getEmailAddress());

    }

    @Test
    public void testEnrollUser_UserEmailAlreadyExists() {
        //given an already existing user
        User alreadyExistingUser = new User(TEST_EMAIL_2);
        User user = moneyTrackerService.enrollUser(TEST_EMAIL_2);

        //Ensure the member is first looked up before enrolling them
        assertNotNull(user);
        assertEquals("Returned user has wrong email address", TEST_EMAIL_2, alreadyExistingUser.getEmailAddress());
    }

    @Test
    public void testCreateMoneyTrackerForUser() {
        //given a user
        User user = new User(TEST_EMAIL_2);

        MoneyTracker moneyTracker = moneyTrackerService.createUserMoneyTracker(user, createMoney(5000D));
        assertNotNull(moneyTracker);
        assertEquals("First tracker created, list should have 1 tracker", 1, user.getMoneyTrackers().size());
        assertEquals("Tracker should have 5000 money for the month", createMoney(5000D), moneyTracker.getMoneyForTheMonth());

        moneyTracker = moneyTrackerService.createUserMoneyTracker(user, createMoney(7000D));
        assertNotNull(moneyTracker);
        assertEquals("Second tracker created, list should have 2 trackers", 2, user.getMoneyTrackers().size());
        assertEquals("Tracker should have 7000 money for the month", createMoney(7000D), moneyTracker.getMoneyForTheMonth());
    }

    private MonetaryAmount createMoney(final Double number) {
   	   return FastMoney.of(number, TEST_CURRENCY);
      }
    
}

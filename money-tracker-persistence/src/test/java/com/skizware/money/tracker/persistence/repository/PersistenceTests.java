package com.skizware.money.tracker.persistence.repository;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test suite for testing persistence.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "PersistenceTests-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersistenceTests extends TestCase {

    @Autowired
    UserRepository userRepository;

    //Feature :: Persist Data
    @Test
    public void testAccountStorageAndRetrieval(){
        //Given a user
        //When I call save
        //The record should be saved to the database (Have an ID)
        User user = new User("test@test.com");

        Integer id = userRepository.save(user);
        assertNotNull("ID should not be null as it should be saved to DB", id);

        User retrievedUser = userRepository.findById(id);
        assertEquals("Retrieved user != saved user - emails do not match", user.getEmailAddress(), retrievedUser.getEmailAddress());

    }

    @Test
    public void testStorage(){
        //Given a user
        //when the user saves a Money Tracker
        //Then the Money Tracker must be saved in the DB
        //And a reference to it should be stored against the user in the DB.
        User user = new User("test2@test.com");
        MoneyTracker moneyTracker = new MoneyTracker(5000D);
        moneyTracker.addTransaction(-100D, "Airtime")
                    .addTransaction(-250D, "Eating Out")
                    .addTransaction(-250D, "Eating Out")
                    .addTransaction(-250D, "Other");

        user.addMoneyTracker(moneyTracker);

        userRepository.save(user);
        userRepository.saveOrUpdateUserMoneyTrackers(user);

        User retrievedUser = userRepository.findByEmail(user.getEmailAddress());

        assertNotNull(retrievedUser);
        assertNotNull("User should have a list of money trackers.", retrievedUser.getMoneyTrackers());
        assertEquals("User should have 1 money tracker", 1, retrievedUser.getMoneyTrackers().size());
        assertEquals(4150D, retrievedUser.getMoneyTrackers().get(0).getTotalRemaining());
        assertEquals("Amount spent on eating out shoulda been -500", -500D, retrievedUser.getMoneyTrackers().get(0).getMoneySpentOn("Eating Out"));
        assertEquals("Amount spent on airtime shoulda been -100", -100D, retrievedUser.getMoneyTrackers().get(0).getMoneySpentOn("Airtime"));
        assertEquals("Amount spent on other shoulda been -250", -250D, retrievedUser.getMoneyTrackers().get(0).getMoneySpentOn("Other"));

        moneyTracker.addTransaction(-1000D, "Eating Out");
        userRepository.saveOrUpdateUserMoneyTrackers(user);

        retrievedUser = userRepository.findByEmail(user.getEmailAddress());
        assertEquals("Amount spent on eating out shoulda been -1500", -1500D, retrievedUser.getMoneyTrackers().get(0).getMoneySpentOn("Eating Out"));
    }

}

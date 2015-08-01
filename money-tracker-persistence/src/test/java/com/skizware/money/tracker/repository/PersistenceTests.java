package com.skizware.money.tracker.repository;

import com.skizware.money.tracker.MoneyTracker;
import com.skizware.money.tracker.repository.relational.UserRepository;
import com.skizware.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "PersistenceTests-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
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

        Integer retrievedUserId = userRepository.findIdForUser(user);
        assertEquals("Incorrect user ID retrieved", id, retrievedUserId);

    }

    public void testStorage(){
        //Given a user
        //when the user saves a Money Tracker
        //Then the Money Tracker must be saved in the DB
        //And a reference to it should be stored against the user in the DB.
        User user = new User("test@test.com");
        MoneyTracker moneyTracker = new MoneyTracker(5000D);
        moneyTracker.addTransaction(-100D, "Airtime")
                    .addTransaction(-250D, "Eating Out")
                    .addTransaction(-250D, "Eating Out")
                    .addTransaction(-250D, "Other");

        user.addMoneyTracker(moneyTracker);

        userRepository.save(user);
    }

}

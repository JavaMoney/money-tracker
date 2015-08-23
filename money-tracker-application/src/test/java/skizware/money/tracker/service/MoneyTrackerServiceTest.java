package skizware.money.tracker.service;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.persistence.repository.UserRepository;
import com.skizware.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import skizware.money.tracker.service.impl.MoneyTrackerServiceImpl;

import java.util.List;

/**
 * Tests for the application logic.
 */
public class MoneyTrackerServiceTest extends TestCase {


    public static final String TEST_EMAIL_1 = "testEmail@gmail.com";
    public static final String TEST_EMAIL_2 = "djanderson@test.com";
    private UserRepository userRepository;


    private MoneyTrackerServiceImpl moneyTrackerService;

    @Override
    protected void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        moneyTrackerService = new MoneyTrackerServiceImpl();
        moneyTrackerService.setUserRepository(userRepository);
    }

    @Test
    public void testGetUserAccount_Positive_UserExists() {
        //Given a previous user with email address testEmail@gmail.com and existing money tracker.
        User existingUser = new User(TEST_EMAIL_1);
        MoneyTracker moneyTracker = new MoneyTracker(5000D);
        moneyTracker.addTransaction(-500D, "Stuff");
        existingUser.addMoneyTracker(moneyTracker);

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq(TEST_EMAIL_1))).thenReturn(existingUser);

        //When the user is retrieved by email address
        User retrievedUser = moneyTrackerService.getUserAccount(TEST_EMAIL_1);

        //The correct user and money tracker details should be returned
        assertEquals("Incorrect email address", TEST_EMAIL_1, retrievedUser.getEmailAddress());
        assertEquals("User has 1 moneyTracker", 1, retrievedUser.getMoneyTrackers().size());
        Mockito.verify(userRepository).findByEmail(Mockito.eq(TEST_EMAIL_1));
    }

    @Test
    public void testEnrollUser() {
        //Given no existing user

        Mockito.reset(userRepository);
        Mockito.when(userRepository.save(Mockito.isA(User.class))).thenReturn(1);

        //When enrollUser is called, a user should be created and returned
        User createdUser = moneyTrackerService.enrollUser(TEST_EMAIL_2);

        assertNotNull("A user should have been created and returned", createdUser);
        assertEquals("Email should match the enrollment email", TEST_EMAIL_2, createdUser.getEmailAddress());

        Mockito.verify(userRepository).save(Mockito.isA(User.class));

    }

    @Test
    public void testEnrollUser_UserEmailAlreadyExists() {
        //given an already existing user

        User alreadyExistingUser = new User(TEST_EMAIL_2);

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq(TEST_EMAIL_2))).thenReturn(alreadyExistingUser);

        User user = moneyTrackerService.enrollUser(TEST_EMAIL_2);

        //Ensure the member is first looked up before enrolling them
        Mockito.verify(userRepository).findByEmail(Mockito.eq(TEST_EMAIL_2));
        assertNotNull(user);
        assertEquals("Returned user has wrong email address", TEST_EMAIL_2, alreadyExistingUser.getEmailAddress());
    }

    @Test
    public void testCreateMoneyTrackerForUser() {
        //given a user
        User user = new User(TEST_EMAIL_2);

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq(user.getEmailAddress()))).thenReturn(user);
        List<MoneyTracker> moneyTrackers = moneyTrackerService.createMoneyTracker(user.getEmailAddress(), 5000D);
        assertNotNull(moneyTrackers);
        assertEquals("First tracker created, list should have 1 tracker", 1, moneyTrackers.size());
        Mockito.verify(userRepository).findByEmail(user.getEmailAddress());
        Mockito.verify(userRepository).saveOrUpdateUserMoneyTrackers(user);

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq(user.getEmailAddress()))).thenReturn(user);
        moneyTrackers = moneyTrackerService.createMoneyTracker(user.getEmailAddress(), 7000D);
        assertNotNull(moneyTrackers);
        assertEquals("Second tracker created, list should have 2 trackers", 2, moneyTrackers.size());
        Mockito.verify(userRepository).findByEmail(user.getEmailAddress());
        Mockito.verify(userRepository).saveOrUpdateUserMoneyTrackers(user);
    }

}

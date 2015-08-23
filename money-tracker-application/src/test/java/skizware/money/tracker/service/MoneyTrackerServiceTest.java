package skizware.money.tracker.service;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.persistence.repository.UserRepository;
import com.skizware.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import skizware.money.tracker.service.impl.MoneyTrackerServiceImpl;

/**
 * Tests for the application logic.
 */
public class MoneyTrackerServiceTest extends TestCase {


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
        User existingUser = new User("testEmail@gmail.com");
        MoneyTracker moneyTracker = new MoneyTracker(5000D);
        moneyTracker.addTransaction(-500D, "Stuff");
        existingUser.addMoneyTracker(moneyTracker);

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq("testEmail@gmail.com"))).thenReturn(existingUser);

        //When the user is retrieved by email address
        User retrievedUser = moneyTrackerService.getUserAccount("testEmail@gmail.com");

        //The correct user and money tracker details should be returned
        assertEquals("Incorrect email address", "testEmail@gmail.com", retrievedUser.getEmailAddress());
        assertEquals("User has 1 moneyTracker", 1, retrievedUser.getMoneyTrackers().size());
        Mockito.verify(userRepository).findByEmail(Mockito.eq("testEmail@gmail.com"));
    }

    @Test
    public void testEnrollUser() {
        //Given no existing user

        Mockito.reset(userRepository);
        Mockito.when(userRepository.save(Mockito.isA(User.class))).thenReturn(1);

        //When enrollUser is called, a user should be created and returned
        User createdUser = moneyTrackerService.enrollUser("djanderson@test.com");

        assertNotNull("A user should have been created and returned", createdUser);
        assertEquals("Email should match the enrollment email", "djanderson@test.com", createdUser.getEmailAddress());

        Mockito.verify(userRepository).save(Mockito.isA(User.class));

    }

    @Test
    public void testEnrollUser_UserEmailAlreadyExists(){
        //given an already existing user

        User alreadyExistingUser = new User("djanderson@test.com");

        Mockito.reset(userRepository);
        Mockito.when(userRepository.findByEmail(Mockito.eq("djanderson@test.com"))).thenReturn(alreadyExistingUser);

        User user = moneyTrackerService.enrollUser("djanderson@test.com");

        //Ensure the member is first looked up before enrolling them
        Mockito.verify(userRepository).findByEmail(Mockito.eq("djanderson@test.com"));
        assertNotNull(user);
        assertEquals("Returned user has wrong email address","djanderson@test.com",alreadyExistingUser.getEmailAddress());
    }

}

package skizware.money.tracker.service.impl;

import com.skizware.money.tracker.persistence.repository.UserRepository;
import com.skizware.user.User;
import skizware.money.tracker.service.MoneyTrackerService;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/08/23
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoneyTrackerServiceImpl implements MoneyTrackerService {

    UserRepository userRepository;

    @Override
    public User getUserAccount(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User enrollUser(String emailAddress) {
        User enrolledUser = getUserAccount(emailAddress);
        if(enrolledUser == null){
            enrolledUser = new User(emailAddress);
            userRepository.save(enrolledUser);
        }

        return enrolledUser;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

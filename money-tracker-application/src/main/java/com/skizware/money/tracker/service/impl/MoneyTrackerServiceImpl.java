package com.skizware.money.tracker.service.impl;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.persistence.repository.UserRepository;
import com.skizware.user.User;
import com.skizware.money.tracker.service.MoneyTrackerService;

import java.util.List;

/**
 * Service for exposing operations for the money tracker application.
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

    @Override
    public List<MoneyTracker> createMoneyTracker(String emailAddress, Double initialTrackerAmount) {
        User user = userRepository.findByEmail(emailAddress);
        MoneyTracker newMoneyTracker = new MoneyTracker(initialTrackerAmount);
        user.addMoneyTracker(newMoneyTracker);

        userRepository.saveOrUpdateUserMoneyTrackers(user);
        return user.getMoneyTrackers();
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

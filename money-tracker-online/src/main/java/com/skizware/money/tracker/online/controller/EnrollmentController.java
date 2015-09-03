package com.skizware.money.tracker.online.controller;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.service.MoneyTrackerService;
import com.skizware.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to route enrollment requests.
 */
@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private MoneyTrackerService moneyTrackerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getEnrollmentPage() {
        return "Hello world";
    }

}

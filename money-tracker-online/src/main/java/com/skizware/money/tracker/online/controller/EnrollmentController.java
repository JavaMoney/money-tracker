package com.skizware.money.tracker.online.controller;

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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getEnrollmentPage() {
        return "Hello world";
    }

}

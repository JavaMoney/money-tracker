package com.skizware.money.tracker.online.controller;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.service.MoneyTrackerService;
import com.skizware.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller to route enrollment requests.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MoneyTrackerService moneyTrackerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage() {
        return "/WEB-INF/jsp/auth/login.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postLogin(HttpServletRequest httpServletRequest, HttpSession httpSession){
        User enrolledUser = moneyTrackerService.enrollUser(httpServletRequest.getParameter("emailAddress"));

        httpSession.setAttribute("loggedInUser", enrolledUser);
        return "redirect:/money-tracker";
    }

}

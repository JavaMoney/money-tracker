package com.skizware.money.tracker.online.controller;

import com.skizware.money.tracker.domain.MoneyTracker;
import com.skizware.money.tracker.service.MoneyTrackerService;
import com.skizware.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/09/06
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/money-tracker")
public class MoneyTrackerController {

    @Autowired
    MoneyTrackerService moneyTrackerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getMoneyTrackerLanding(HttpSession session, Model model) {
        model.addAttribute("user", (session.getAttribute("loggedInUser")));
        return "/WEB-INF/jsp/money-tracker/list.jsp";
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public String getMoneyTrackerDetail(@PathVariable String uuid, Model model, HttpSession httpSession) {
        System.out.println("UUID = " + uuid);
        model.addAttribute("tracker", ((User) httpSession.getAttribute("loggedInUser")).getMoneyTrackerByUUID(UUID.fromString(uuid)));

        return "/WEB-INF/jsp/money-tracker/detail.jsp";
    }

    @RequestMapping(value = "/{uuid}/add-transaction", method = RequestMethod.GET)
    public String getAddTransactionForm(@PathVariable String uuid, Model model, HttpSession httpSession) {
        model.addAttribute("tracker", ((User) httpSession.getAttribute("loggedInUser")).getMoneyTrackerByUUID(UUID.fromString(uuid)));

        return "/WEB-INF/jsp/money-tracker/add-transaction.jsp";
    }

    @RequestMapping(value = "/{uuid}/add-transaction", method = RequestMethod.POST)
    public String postAddTransactionForm(@PathVariable String uuid, HttpSession httpSession, HttpServletRequest request) {
        User user = (User) httpSession.getAttribute("loggedInUser");
        MoneyTracker moneyTracker = user.getMoneyTrackerByUUID(UUID.fromString(uuid));
        if (request.getParameter("category") != null && !"".equals(request.getParameter("category"))) {
            moneyTracker.addTransaction(Double.parseDouble(request.getParameter("amount")), request.getParameter("category"));
        } else {
            moneyTracker.addTransaction(Double.parseDouble(request.getParameter("amount")));
        }

        return "redirect:/money-tracker/" + moneyTracker.getUuid().toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreateForm() {
        return "/WEB-INF/jsp/money-tracker/create.jsp";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreateForm(HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedInUser");
        MoneyTracker moneyTracker = moneyTrackerService.createUserMoneyTracker(user, Double.parseDouble(request.getParameter("startAmount")));
        return "redirect:/money-tracker/" + moneyTracker.getUuid().toString();
    }

}

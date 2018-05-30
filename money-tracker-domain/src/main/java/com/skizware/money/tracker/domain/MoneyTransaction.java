package com.skizware.money.tracker.domain;

import javax.money.MonetaryAmount;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/09
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoneyTransaction {

    private MonetaryAmount amount;

    public MoneyTransaction(MonetaryAmount amount) {
        this.amount = amount;
    }

    public MonetaryAmount getAmount(){
        return amount;
    }

}

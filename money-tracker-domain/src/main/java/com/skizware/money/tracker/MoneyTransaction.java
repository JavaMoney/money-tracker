package com.skizware.money.tracker;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/07/09
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoneyTransaction {

    private Double amount;

    public MoneyTransaction(Double amount) {
        this.amount = amount;
    }

    public Double getAmount(){
        return amount;
    }

}

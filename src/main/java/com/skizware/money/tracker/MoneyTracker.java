package com.skizware.money.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class MoneyTracker
{

    private Double moneyForTheMonth;
    private List<MoneyTransaction> moneyTransactions;

    public MoneyTracker(Double moneyForTheMonth) {
        this.moneyForTheMonth = moneyForTheMonth;
    }

    public Double getMoneyForTheMonth() {
        return moneyForTheMonth;
    }

    public void addTransaction(MoneyTransaction moneyTransaction){
        if(moneyTransactions == null){
            moneyTransactions = new ArrayList<MoneyTransaction>();
        }

        moneyTransactions.add(moneyTransaction);
    }

    public Double getTotalRemaining(){
        Double total = moneyForTheMonth;
        for (MoneyTransaction moneyTransaction : moneyTransactions) {
            total += moneyTransaction.getAmount();
        }

        return total;
    }


}

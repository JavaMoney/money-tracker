package com.skizware.money.tracker;

import java.util.*;

/**
 * Hello world!
 *
 */
public class MoneyTracker
{

    public static final String UNCATEGORIZED = "Uncategorized";
    private Double moneyForTheMonth;
    private Map<String,List<MoneyTransaction>> moneyTransactions;

    public MoneyTracker(Double moneyForTheMonth) {
        this.moneyForTheMonth = moneyForTheMonth;
    }

    public Double getMoneyForTheMonth() {
        return moneyForTheMonth;
    }

    public void addTransaction(MoneyTransaction moneyTransaction){

        addTransaction(moneyTransaction, UNCATEGORIZED);
    }

    public void addTransaction(MoneyTransaction moneyTransaction, String category){
        if(moneyTransactions == null){
            moneyTransactions = new HashMap<String, List<MoneyTransaction>>();
        }

        if(!moneyTransactions.containsKey(category)){
            moneyTransactions.put(category, new LinkedList<MoneyTransaction>());
        }

        moneyTransactions.get(category).add(moneyTransaction);

    }

    public Double getTotalRemaining(){
        Double total = moneyForTheMonth;
        for (List<MoneyTransaction> categoryTransactions : moneyTransactions.values()) {
            for (MoneyTransaction transaction : categoryTransactions) {
                total += transaction.getAmount();
            }

        }

        return total;
    }

    public Double getMoneySpentOn(String category){
        Double total = 0D;
        if(moneyTransactions.containsKey(category)){
            for (MoneyTransaction transaction : moneyTransactions.get(category)) {
                total += transaction.getAmount();
            }

        }
        return total;
    }


}

package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    public Transaction() {
        this.id = String.valueOf(UUID.randomUUID());
    }
    public Transaction(String accountNumber, double amount, String time) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getAmount() {
        return amount;
    }
    public String getTime() {
        return time;
    }
}

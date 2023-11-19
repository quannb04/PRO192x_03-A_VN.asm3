package models;
//Định nghĩa class tài khoản khách hàng
public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    public Account(){
    }
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    //check tk có phải premium không
    public boolean isPremium() {
        if (balance >= 10_000_000) {
            return true;
        } else {
            return false;
        }
    }
    //Hiển thị thông tin tk
    public String toString() {
        String show1 = String.format( "%10s | ", getAccountNumber());
        String show2 = String.format( "%13s | ", getAccountType());
        String show3 = String.format( "%,33d", (long) getBalance());
        return show1 + show2 + show3;
    }
    public boolean withdraw(double amount) {
        return true;
    }
}

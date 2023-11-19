package models;

import java.util.ArrayList;
import java.util.List;
//Định nghĩa class quản lý thông tin tài khoản khách hàng
public class Customer extends User {
    private List<Account> accounts;
    public Customer() {
        this.accounts = new ArrayList<Account>();
    }
    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }
    public Customer(String name, String customerId, List<Account> accounts) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    //check khách hàng có phải Premium ko
    public boolean isPremium() {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).isPremium()) {
                return true;
            }
        }
        return false;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    //thêm tài tk hàng
    public void addAccount(Account newAccount) {
        if(accounts.size() == 0) {
            accounts.add(newAccount);
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (newAccount.getAccountNumber().equals(accounts.get(i).getAccountNumber())) {
                System.out.println("STK da ton tai");
                return;
            }
        }
        accounts.add(newAccount);
    }
    //tổng giá trị các tài khoản khách hàng
    public double getBalance() {
        double total = 0;
        for(int i = 0; i < accounts.size(); i++) {
            total += accounts.get(i).getBalance();
        }
        return total;
    }
    //hiển thị thông tin khách hàng
    public void displayInformation() {
        String isPre = isPremium() == true  ? "Premium" : "Nomal";
        String show2 = String.format( "%,d", (long) getBalance());
        System.out.printf("%12s | %15s | %12s | %15sđ\n",getCustomerId(),getName(),isPre,show2);
        for(int i = 0; i < accounts.size(); i++) {
            System.out.printf("%-2d%-50sđ\n", i+1, accounts.get(i).toString());
        }
    }
}

package models;

import java.util.*;
import java.util.ArrayList;
import java.util.UUID;
//Định nghĩa class quản lý thông tin khách hàng
public class Bank {
    private final String id;
    private final List<Customer> customers;
    private final List<Transaction> transactions;
    public Bank() {
        this.customers = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }
    public String getId() {
        return id;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public List<Transaction> getTransactions() {return transactions; }
    //Khởi tạo phương thức thêm khách hàng
    public void addCustomer(Customer newCustomer) {
        if(isCustomerExisted(newCustomer.getCustomerId())) {
            customers.add(newCustomer);
        }
        else {
            System.out.println("Khach hang da dang ky");
        }
    }
    //Khởi tạo phương thc kiểm tra khách hàng tồn tại ko
    public boolean isCustomerExisted(String customerId) {
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getCustomerId().equals(customerId)) {
                return false;
            }
        }
        return true;
    }
}

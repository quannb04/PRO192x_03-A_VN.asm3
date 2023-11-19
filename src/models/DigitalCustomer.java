package models;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//Định nghĩa class quản lý thông tin khách hàng tiềm năng
public class DigitalCustomer extends Customer {
    private List<Account> accounts;
    public DigitalCustomer(String name, String customerId, List<Account> accounts) {
        super(name, customerId, accounts);
        this.accounts = new ArrayList<Account>();
    }
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<Account>();
    }
    //rút tiền theo stk
    public boolean withdraw(String accountNumber, double amount) {
        boolean check = false;
        List<Account> accounts = this.getAccounts();
        for(int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getAccountNumber().equals(accountNumber)) {
                check = accounts.get(i).withdraw(amount);
                break;
            }
        }
        return check;
    }
    //hiển thị thông tin Khách hàng
    @Override
    public void displayInformation() {
        //toán tử 3 ngôi kiểm tra khách hàng có phải Premium ko
        String isPre = isPremium() == true  ? "Premium" : "Nomal";
        String show1 = String.format( "%,d", (long) getBalance());
        System.out.println("+-----------------+-------------------------+-----------------+");
        System.out.println("|                    THONG TIN KHACH HANG                     |");
        System.out.println("+-----------------+-------------------------+-----------------+");
        System.out.printf("%-15s | %13s | %10s | %15sđ\n", getCustomerId(), getName(), isPre, show1);
        List<Account> accounts = this.getAccounts();
        for(int i = 0; i < accounts.size(); i++) {
            System.out.printf("%-6d%38sđ\n", i+1, accounts.get(i).toString());
        }
        System.out.println("+-----------------+-------------------------+-----------------+");
        System.out.println();
    }
}

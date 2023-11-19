package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//Định nghĩa class quản lý tài khoản tín dụng của khách hàng
public class LoanAccount extends Account implements ReportService, Withdraw{
    private double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private int LOAN_ACCOUNT_MAX_BALANCE = 100_000_000;
    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    //rút tiền
    public boolean withdraw(double amount) {
        boolean check = false;
        double newBalance = 0.0;
        if(isAccepted(amount)) {
            if(isPremium()) {
                newBalance = getBalance() - amount - (amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE);
                //cập nhật số dư mới
                setBalance(newBalance);
                log(amount);
                check = true;
            }
            if(!isPremium()) {
                newBalance = getBalance() - amount - (amount * LOAN_ACCOUNT_WITHDRAW_FEE);
                //cập nhật số dư mới
                setBalance(newBalance);
                log(amount);
                check = true;
            }
        }
        System.out.println("So tien rut khong kha dung");
        System.out.println();
        return check;
      }
    //check đk rút tiền
    @Override
    public boolean isAccepted(double amount) {
        boolean check = false;
        //check đk rt tiền với tk Premium
        if(isPremium()
           && amount <= LOAN_ACCOUNT_MAX_BALANCE
           && getBalance() - amount - (amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE) >= 50_000) {
            check = true;
        }
        //check đk rt tiền với tk thường
        if(!isPremium()
           && amount <= LOAN_ACCOUNT_MAX_BALANCE
           && getBalance() - amount - (amount * LOAN_ACCOUNT_WITHDRAW_FEE) >= 50_000) {
            check = true;
        }
        return check;
    }
    //thời điểm giao dịch
    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
    //ghi đè interface ReportService hiển thị biên lai giao dịch
    @Override
    public void log(double amount) {
        //tính phí rút cho từng loại tài khoản
        double vat = this.isPremium() ?
            amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE :
            amount * LOAN_ACCOUNT_WITHDRAW_FEE;
        System.out.println("+----------+--------------------+----------+");
        System.out.println("           BIEN LAI GIAO DICH LOAN          ");
        System.out.printf("%-15s%29s\n","NGAY G/D:",getDateTime());
        System.out.printf("%-15s%29s\n","ATM ID:","DIGITAL-BANK-ATM 2022");
        System.out.printf("%-15s%29s\n","SO TK:",getAccountNumber());
        //hiển thị số tiền rút đúng form yêu cầu
        String show1 = String.format("%-15s", "SO TIEN:");
        String show2 = String.format("%,29d", (long) amount);
        System.out.println(show1 + show2);
        //hiển thị số dư rút đúng form yêu cầu
        String show3 = String.format("%-15s", "SO DU:");
        String show4 = String.format("%,29d", (long) getBalance());
        System.out.println(show3 + show4);
        //hiển thị VAT đúng form yêu cầu
        String show5 = String.format("%-15s", "PHI + VAT:");
        String show6 = String.format("%,29d", (long) vat);
        System.out.println(show5 + show6);
        System.out.println("+----------+--------------------+----------+");
        System.out.println();
    }
    //xuất thông tin tài khoản: số tk + số dư
    public String toString() {
        String show1 = String.format( "%9s | ", getAccountNumber());
        String show2 = String.format( "%13s | ", "LOAN");
        String show3 = String.format( "%,28d", (long) getBalance());
        return show1 + show2 + show3;
    }
}

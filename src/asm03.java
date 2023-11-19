import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Scanner;
import models.Account;
import models.Bank;
import models.Customer;
import models.User;
import models.DigitalBank;
import models.DigitalCustomer;
import models.LoanAccount;
import models.SavingAccount;
import models.Transaction;
public class asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    public static void giaoDien() {
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| NGAN HANG SO | FX18762@v3.0.0            |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println(" 1. Thong tin khach hang                    ");
        System.out.println(" 2. Them tai khoan ATM                      ");
        System.out.println(" 3. Them tai khoan tin dung                 ");
        System.out.println(" 4. Rut tien                                ");
        System.out.println(" 5. Lich su giao dich                       ");
        System.out.println(" 0. Thoat                                   ");
        System.out.println("+----------+--------------------+----------+");
        System.out.println("Chuc nang:");
    }
    //=====> phần rút tiền đang hiển thị thông báo lỗi , chưa sửa
    //=====>phần transaction ko được lưu ở Bank mà lưu ởDigitalCustomver, chưa sửa
    public static void main(String[] args) {
        //khởi tạo khách hàng mới
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
//        try {
            while (true) {
                giaoDien();
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        chucnang1();
                        break;
                    case 2:
                        chucnang2();
                        break;
                    case 3:
                        chucnang3();
                        break;
                    case 4:
                        chucnang4();
                        break;
                    case 5:
                        chucnang5();
                        break;
                    case 0:
                        chucnang0();
                        break;
                    default:
                        System.out.println("Nhap so khong dung, moi nhap lai:\nChuc nang:");
                        break;
                }
            }
//        } catch (Exception e) {
//            System.out.println("Thong tin nhap khong phai la so");
//        }
    }
    //hiển thị thông tin khách hàng
    public static void chucnang1() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if(customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Khong tim thay khach hang");
        }
    }
    //thêm tk Savings
    public static void chucnang2() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        System.out.println("Nhap ma STK gom 6 chu so:");
        String stk = "";
        while(true) {
            boolean check = true;
            stk = scanner.next();
            //kiểm tra điều kiện nhập stk
            if(stk.length() != 6 || Integer.parseInt(stk) == 0) {
                System.out.println("STK phai la so va co 6 so, xin nhap lai:");
                continue;
            }
            //kiểm tra STK đã có chưa
            for (int i = 0; i < customer.getAccounts().size(); i++) {
                if (customer.getAccounts().get(i).getAccountNumber().equals(stk)) {
                    System.out.println("STK da ton tai, moi nhap lai:");
                    check = false;
                    break;
                }
            }
            if(check == true) {
                break;
            }
        }
        System.out.println("Nhap so du:");
        double sodu = scanner.nextDouble();
        //check điều kiện nạp tiền
        while (sodu < 50_000) {
            System.out.println("So du khong nho hon 50.000 VND, moi nhap lai:");
            sodu = scanner.nextDouble();
        }
        SavingAccount savingAccount = new SavingAccount(stk, sodu);
        activeBank.addAccount(CUSTOMER_ID, savingAccount);
        //Khởi tạo 1 lịch sử giao dịch mới
        Transaction transaction = new Transaction(stk, sodu, getDateTime());
        activeBank.getTransactions().add(transaction);
        System.out.println(savingAccount);
        System.out.println();
    }
    //thêm tk Loan
    public static void chucnang3() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        System.out.println("Nhap ma STK gom 6 chu so:");
        String stk = "";
        while(true) {
            boolean check = true;
            stk = scanner.next();
            //kiểm tra điều kiện nhập stk
            if(stk.length() != 6 || Integer.parseInt(stk) == 0) {
                System.out.println("STK phai la so va co 6 so, xin nhap lai:");
                continue;
            }
            //kiểm tra STK đã có chưa
            for (int i = 0; i < customer.getAccounts().size(); i++) {
                if (customer.getAccounts().get(i).getAccountNumber().equals(stk)) {
                    System.out.println("STK da ton tai, moi nhap lai:");
                    check = false;
                    break;
                }
            }
            if(check == true) {
                break;
            }
        }
        //nhập số dư
        System.out.println("Nhap so du:");
        double sodu = scanner.nextDouble();
        while (sodu < 50_000) {
            System.out.println("So du khong nho hon 50.000 VND, moi nhap lai:");
            sodu = scanner.nextDouble();
        }
        LoanAccount loanAccount = new LoanAccount(stk, sodu);
        activeBank.addAccount(CUSTOMER_ID, loanAccount);
        //Khởi tạo 1 lịch sử giao dịch mới
        Transaction transaction = new Transaction(stk, sodu, getDateTime());
        activeBank.getTransactions().add(transaction);
        System.out.println(loanAccount);
        System.out.println();
    }
    //rút tiền
    public static void chucnang4() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        String withdrawAccount;
        System.out.println("Nhap so tai khoan can rut:");
        //check stk tồn tại ko
        while(true) {
            boolean pass = false;
            withdrawAccount = scanner.next();
            for (int i = 0; i < customer.getAccounts().size(); i++) {
                if(customer.getAccounts().get(i).getAccountNumber().equals(withdrawAccount)) {
                    pass = true;
                }
            }
            if(pass == true) {
                break;
            } else {
                System.out.println("So tai khoan khong dung, moi nhap lai:");
            }
        }
        System.out.println("Nhap so tien can rut:");
        double numberBalance = scanner.nextDouble();
        //vừa chạy hàm withdraw vừa gán cho thuộc tính result
        boolean result = activeBank.withdraw(CUSTOMER_ID, withdrawAccount, numberBalance);
        if(result) {
            //Khởi tạo 1 lịch sử giao dịch mới khi rút tiền thành công
            Transaction transaction = new Transaction(withdrawAccount, -numberBalance, getDateTime());
            activeBank.getTransactions().add(transaction);
        }
    }
    //hiển thị lịch sử giao dịch
    public static void chucnang5() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if(customer != null) {
            //hiển thị thông tin tài khoản
            customer.displayInformation();
            System.out.println("+-----------------+-------------------------+-----------------+");
            System.out.println("|                      LICH SU GIAO DICH                      |");
            System.out.println("+-----------------+-------------------------+-----------------+");
            for(int i = 0; i < activeBank.getTransactions().size(); i++) {
                //Chỉnh sửa cách hiển thị số tiền rút đẹp hơn
                String show = String.format("%,18d", (long) activeBank.getTransactions().get(i).getAmount());
                //hiển thị lịch sử giai dịch
                System.out.printf("%4s%10s | %14sđ | %24s\n",
                        "GD" + (i+1),
                        activeBank.getTransactions().get(i).getAccountNumber(),
                        show,
                        activeBank.getTransactions().get(i).getTime());
            }
            System.out.println("+-----------------+-------------------------+-----------------+");
            System.out.println();
        } else {
            System.out.println("Khong tim thay khach hang");
        }
    }
    //thoát chương trình
    public static void chucnang0() {
        System.out.println("Chuong trinh dang tat ...");
        System.exit(0);
    }
    //tạo thời điểm giao dịch
    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}


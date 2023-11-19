package models;
import java.util.ArrayList;
import java.util.List;

//Định nghĩa class quản lý khách hàng tiềm năng
public class DigitalBank extends Bank{
    public DigitalBank() {};
    //tìm khách hàng theo CCCD
    public Customer getCustomerById (String customerId) {
        for(int i = 0; i < getCustomers().size(); i++) {
            if(getCustomers().get(i).getCustomerId().equals(customerId)) {
                return getCustomers().get(i);
            }
        }
        System.out.println("Khach hang chua dang ky");
        return null;
    }
    //thêm khách hàng mới
    public void addCustomer(String customerId, String name) {
        boolean checkCustomer = true;
        //check CCCD đã tồn tại chưa
        for(int i = 0; i < getCustomers().size(); i++) {
            if(getCustomers().get(i).getCustomerId().equals(customerId)) {
                checkCustomer = false;
            }
        }
        if(checkCustomer) {
            //khởi tạo khách hàng mới
            DigitalCustomer newCustomer = new DigitalCustomer(name, customerId);
            //this tham chiếu đến biến instance của lớp hiện tại
            this.addCustomer(newCustomer);
            //Khởi tạo danh sách lịch sử giao dịch
            Transaction transaction = new Transaction();
            System.out.println("Da them khach hang moi");
        } else {
            System.out.println("Khach hang da dang ky");
        }
    }
    //khách hàng rút tiền với tài khoản tương ứng
    public boolean withdraw(String customerId, String accountNumber, double amount) {
        boolean check = false;
        for(int i = 0; i < getCustomers().size(); i++) {
            if(getCustomers().get(i).getCustomerId().equals(customerId)) {
                Customer cus = getCustomers().get(i);
                //check cus có phải là 1 thể hiện của DigitalCustomer
                if(cus instanceof DigitalCustomer) {
                    check = ((DigitalCustomer) cus).withdraw(accountNumber, amount);
                    break;
                }
            }
        }
        return check;
    }
    //thêm tài khoản
    public void addAccount(String customerId, Account account) {
        for(Customer customer : getCustomers()) {
            if(customer.getCustomerId().equals(customerId)) {
                customer.addAccount(account);
                return;
            }
        }
    }
}

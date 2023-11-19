package models;
//phương thức trừu tượng định nghĩa nghiệp vụ rút tiền
public interface Withdraw {
    boolean withdraw(double amount);
    boolean isAccepted(double amount);
}

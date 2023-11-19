package models;
//Định nghĩa class người dùng
public class User {
    private String name;
    private String customerId;
    public User() {}
    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setcustomerId(String customerId) {
        if(CheckCCCD(customerId)) {
            this.customerId = customerId;
        } else {
            System.out.println("Tai khoan khong le, vui long nhap lai");
        }
    }
    //check CCCD nhập vào có hợp lệ không
    public static boolean CheckCCCD(String check) {
        try{
            if(check.length() == 12) {
                for (int i = 0; i < check.length(); i++) {
                    if (Character.isLetter(check.charAt(i))) {//check ký tự char có phải 1 ký tự trong khoảng azAZ
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("loi nhap thong tin");
            return false;
        }
    }
}


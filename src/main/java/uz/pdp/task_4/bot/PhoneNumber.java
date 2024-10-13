package uz.pdp.task_4.bot;

public class PhoneNumber {
    public static String fix(String phone) {
        if (phone.startsWith("+")){
            return phone;
        } else {
            return "+" + phone;
        }
    }
}

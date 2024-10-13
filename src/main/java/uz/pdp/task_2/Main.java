package uz.pdp.task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        DB.generate();
        userFilter(DB.USERS, user -> user.getAge()>19);
    }
    private static void userFilter(List<User> users, Predicate<User> predicate) {
       List<User> fieldUsers = new ArrayList<>();
        for (User user : users) {
            if (predicate.test(user)){
                fieldUsers.add(user);
            }
        }
        for (User fieldUser : fieldUsers) {
            System.out.println(fieldUser.getFirstname() + " " + fieldUser.getLastname() + " " + fieldUser.getAge());
        }
    }
}
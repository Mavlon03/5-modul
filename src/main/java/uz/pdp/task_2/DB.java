package uz.pdp.task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface DB {
    List<User> USERS = new ArrayList<>();

    static void generate(){
        User user1 = new User(UUID.randomUUID(),"Ali", "Aliyev", 19, Gender.MALE );
        User user2 = new User(UUID.randomUUID(),"Aziza", "Azizove", 28, Gender.FEMALE );
        User user3 = new User(UUID.randomUUID(),"G'ulom", "Zafarov", 15, Gender.MALE );
        User user4 = new User(UUID.randomUUID(),"Sardor", "Ergashev", 29, Gender.MALE );
        User user5 = new User(UUID.randomUUID(),"Laziza", "Qobilova", 24, Gender.FEMALE );
        User user6 = new User(UUID.randomUUID(),"Yusuf", "Akramov", 12, Gender.MALE );
        USERS.add(user1);
        USERS.add(user2);
        USERS.add(user3);
        USERS.add(user4);
        USERS.add(user5);
        USERS.add(user6);
    }
}

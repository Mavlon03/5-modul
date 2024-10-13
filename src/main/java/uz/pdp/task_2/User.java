package uz.pdp.task_2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstname;
    private String lastname;
    private long age;
    private Gender gender;
}

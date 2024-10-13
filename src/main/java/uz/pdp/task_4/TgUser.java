package uz.pdp.task_4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TgUser {
    private Long id;
    private String phone;
    private String firstname;
    private String lastname;
    private TgState tgState = TgState.START;
    private int messageId;
    private String tgGender;
    private String married;
}

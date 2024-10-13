package uz.pdp.task_3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private UUID categoryId;
    private String name;
    private Integer amount;
    private Integer price;
}

package uz.pdp.task_3;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DB.generate();

        Map<UUID, List<Product>> collect = DB.PRODUCTS.stream().
                collect(Collectors.groupingBy(Product::getCategoryId));

        collect.forEach(((categoryId, products) ->{
            String categoryName = DB.CATEGORIES.stream()
                    .filter(category -> category.getId().equals(categoryId))
                    .findFirst()
                    .map(Category::getTitle)
                    .orElse("Kategory topilmadi");

            int totalProducts = products.size();
            int totalsum = products.stream()
                    .mapToInt(product -> product.getPrice() * product.getAmount())
                    .sum();

            System.out.println(categoryName + " kategoriyasida " + totalProducts + " ta mahsulot bor. "
            + " \nUmumiy summa: " + totalsum);


        } ));



    }
}

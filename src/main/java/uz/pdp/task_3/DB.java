package uz.pdp.task_3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface DB {
    List<Category> CATEGORIES = new ArrayList<>();
    List<Product> PRODUCTS = new ArrayList<>();

     static void generate(){
        Category category1 = new Category(UUID.randomUUID(),"Yeguliklar");
         CATEGORIES.add(category1);
         Category category2 = new Category(UUID.randomUUID(),"Ichguliklar");
         CATEGORIES.add(category2);
         Category category3 = new Category(UUID.randomUUID(),"Kiyguliklar");
        CATEGORIES.add(category3);

        Product product1 = new Product(UUID.randomUUID(),category1.getId(), "Banan", 1, 10_000);
        Product product2 = new Product(UUID.randomUUID(),category1.getId(),"Olma",2, 5_000);
        Product product3 = new Product(UUID.randomUUID(),category1.getId(),"Anor",3,6000);
        Product product4 = new Product(UUID.randomUUID(),category2.getId(),"Fanta",1,6000);
        Product product5 = new Product(UUID.randomUUID(),category2.getId(),"Pepsi",1,5000);
        Product product6 = new Product(UUID.randomUUID(),category2.getId(),"Cola",2,8000);
        Product product7 = new Product(UUID.randomUUID(), category3.getId(),"Futbolka",3,100_000);
        Product product8 = new Product(UUID.randomUUID(), category3.getId(),"Shim",1,100_000);
        Product product9 = new Product(UUID.randomUUID(), category3.getId(),"Krasofka",1,100_000);

        PRODUCTS.add(product1);
        PRODUCTS.add(product2);
        PRODUCTS.add(product3);
        PRODUCTS.add(product4);
        PRODUCTS.add(product5);
        PRODUCTS.add(product6);
        PRODUCTS.add(product7);
        PRODUCTS.add(product8);
        PRODUCTS.add(product9);

    }

}

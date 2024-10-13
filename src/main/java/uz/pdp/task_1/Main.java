package uz.pdp.task_1;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> stringSupplier = () -> "Assalomu aleykum!";
        System.out.println(stringSupplier.get());
        //Supplier Interfeysi Hech qanday parametrlarni qabul qilmaydi. Undan yangi qiymat olishda foydalanamiz
        //U ayniqsa, obyektlarni kechiktirilgan holda yaratishda va qayta-qayta yangi qiymat olishda foydali hisoblanadi.
    }
}

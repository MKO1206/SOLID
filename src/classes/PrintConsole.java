package classes;

import interfaces.Printable;

import java.util.HashMap;

public class PrintConsole implements Printable {
    public void printC(HashMap hashMap) {
        System.out.printf("%s %18s %18s %18s\n", "Код", "Наименование", "Цена/за.ед", "Кол-во товара");
        hashMap.entrySet().stream().forEach(System.out::println);
    }


    @Override
    public void print(HashMap hashMap) {
        printC(hashMap);
    }
}

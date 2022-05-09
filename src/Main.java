import interfaces.Colorable;
import interfaces.Printable;
import srpClasses.ColorConsole;
import srpClasses.PrintConsole;
import srpClasses.Product;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //принцип инверсии зависимостей(Dependency Inversion Principle)
        Printable printProductConsole = new PrintConsole();
        Colorable consoleColorGreen = ColorConsole.ANSI_GREEN;
        Colorable consoleColorReset = ColorConsole.ANSI_RESET;
        Colorable consoleColorYellow = ColorConsole.ANSI_YELLOW;

        String inputString = "";
        int productNumber = 0;
        int productCount = 0;

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Product> productMap = new HashMap<>();
        productMap.put(1, new Product("Альбом", 160, 30));
        productMap.put(2, new Product("Карандаши", 80, 20));
        productMap.put(3, new Product("Тетрадь", 10, 100));
        productMap.put(4, new Product("Фломастеры", 240, 25));

        printProductConsole.print(productMap);

        HashMap<Integer, Product> basketMap = new HashMap<>();

        while (!inputString.equals("end")) {
            System.out.println(consoleColorGreen.color() + "Введите КОД товара и желаемое количество товаров через пробел" + consoleColorReset.color());
            inputString = scanner.nextLine();
            String[] pieces = inputString.split("\\s+");
            try {
                if (inputString.equals("del")) {
                    if (!basketMap.isEmpty()) {
                        printProductConsole.print(basketMap);
                        System.out.println("Введите КОД товара, который необходимо удалить");
                        productNumber = Integer.parseInt(scanner.nextLine());

                        Product productToProductMap = productMap.get(productNumber).changeCount(basketMap.get(productNumber).getCount() + productMap.get(productNumber).getCount());
                        productMap.put(productNumber, productToProductMap);

                        basketMap.remove(productNumber);
                        System.out.println("Товар удален");

                        showShopAndBasket(printProductConsole, productMap, basketMap);
                    }
                } else {
                    productNumber = Integer.parseInt(pieces[0]);
                    productCount = Integer.parseInt(pieces[1]);

                    if (productCount <= productMap.get(productNumber).getCount()) {
                        Product productToBasketMap = productMap.get(productNumber).changeCount(productCount);
                        if (basketMap.containsKey(productNumber)) {
                            Product productToProductMap = productMap.get(productNumber).changeCount(basketMap.get(productNumber).getCount() + productMap.get(productNumber).getCount() - productCount);
                            productMap.put(productNumber, productToProductMap);
                        } else {
                            Product productToProductMap = productMap.get(productNumber).changeCount(productMap.get(productNumber).getCount() - productCount);
                            productMap.put(productNumber, productToProductMap);
                        }

                        basketMap.put(productNumber, productToBasketMap);
                    }

                    showShopAndBasket(printProductConsole, productMap, basketMap);
                    System.out.println(consoleColorYellow.color() + "Для удаления товара из корзины введите `del` или введите `end` для завершения покупок" + consoleColorYellow.color());
                }

            } catch (Exception ex) {
                if (!pieces[0].equals("end"))
                    System.out.println("Проверьте код, наименование товара и введите еще раз");
            }
        }

        double sum = result(basketMap);
        printProductConsole.print(basketMap);
        System.out.println("Общая стоимость: " + sum)
    }

    private static double result(HashMap<Integer, Product> basketMap) {
        return basketMap.entrySet().stream().map(x -> x.getValue().getCount() * x.getValue().getPrice()).reduce(0.0, Double::sum);
    }

    private static void showShopAndBasket(Printable printProductConsole, HashMap<Integer, Product> productMap, HashMap<Integer, Product> basketMap) {
        printProductConsole.print(productMap);
        System.out.println("Корзина:");
        printProductConsole.print(basketMap);
    }
}
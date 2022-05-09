package classes;

import interfaces.CountChangable;

import java.util.Objects;

public class Product implements CountChangable {

    private String name;
    private double price;
    private int count;

    public Product(String name, double price, int count) {

        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format("%15s %20s %20s", name, price, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //Open-closed principle
    @Override
    public Product changeCount(int count) {
        return new Product(getName(), getPrice(), count);
    }
}

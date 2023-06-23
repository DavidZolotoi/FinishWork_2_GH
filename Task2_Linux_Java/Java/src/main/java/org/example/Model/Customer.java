package org.example.Model;

import org.example.Controller.Main;
import org.example.Model.db.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Покупатель
 */
public class Customer {

    /**
     * Идентификатор покупателя
     */
    private Integer id;
    protected static Integer customerIdMin = 1000;
    protected static Integer customerIdMax = 9999;

    /**
     * Индикатор потребности купить
     * true - намерен совершить покупку
     * false - не намерен совершать покупки
     */
    private Boolean isPay;

    /**
     * Корзина покупателя
     */
    private List<Toy> cart;

    /**
     * Покупатель
     * @param id Идентификатор покупателя
     */
    public Customer(Integer id) {
        this(id, false);
    }
    /**
     * Покупатель
     * @param id Идентификатор покупателя
     * @param isPay Индикатор потребности купить (true/false)
     */
    public Customer(Integer id, Boolean isPay) {
        this(id, isPay, new ArrayList<>());
    }
    /**
     * Покупатель
     * @param id Идентификатор покупателя
     * @param isPay Индикатор потребности купить (true/false)
     * @param cart Корзина покупателя
     */
    public Customer(Integer id, Boolean isPay, List<Toy> cart) {
        this.id = id;
        this.isPay = isPay;
        this.cart = cart;
    }

    /**
     * Генератор случайного листа покупателей из базы данных
     * @return случайный лист покупателей
     */
    public static List<Customer> getRandomList(Database db) {
        // Количество покупателей
        Integer customersCount = Main.getRandomInt(5, 15);
        // Сам лист
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customersCount; i++) {
            customers.add(db.getCustomers().get(Main.getRandomInt(customerIdMin, customerIdMax)));
        }
        return customers;
    }

    public static List<Customer> tryParseList(List<String> contentList) {
        var customers = new ArrayList<Customer>();
        Customer customer;
        for (var contentLine : contentList) {
            String[] contentElement = contentLine.split(";");
            customer = new Customer(
                    Integer.parseInt(contentElement[0]),
                    Boolean.parseBoolean(contentElement[1])
            );
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Заполнение корзины покупателя случайным набором из базы данных
     */
    public void cartFillRandom(Database db) {
        // Количество игрушек в корзине
        Integer toysCount = Main.getRandomInt(1, 5);
        // Заполнение корзины случайными id из базы игрушек
        for (int i = 0; i < toysCount; i++) {
            this.cart.add(db.getToys().get(Main.getRandomInt(Toy.toyIdMin, Toy.toyIdMax)));
        }
    }

    /**
     * Геттер
     * @return id покупателя
     */
    public Integer getId() {
        return id;
    }
    /**
     * Геттер
     * @return намерение покупателя совершить покупку (true - да, false - нет)
     */
    public Boolean getPay() {
        return isPay;
    }
    /**
     * Геттер
     * @return корзину покупателя
     */
    public List<Toy> getCart() {
        return cart;
    }
}

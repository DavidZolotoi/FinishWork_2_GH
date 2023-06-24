package org.example.Model;

import org.example.Controller.Main;
import org.example.Model.db.Database;
import org.example.View.Monitoring;

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
    protected static Integer idMin = 1000;
    protected static Integer idMax = 9999;

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
     * Мониторинг работы магазина
     */
    public Monitoring monitor;

    /**
     * Покупатель
     * @param id Идентификатор покупателя
     */
    public Customer(Integer id, Monitoring monitor) {
        this(id, false, monitor);
    }
    /**
     * Покупатель
     * @param id Идентификатор покупателя
     * @param isPay Индикатор потребности купить (true/false)
     */
    public Customer(Integer id, Boolean isPay, Monitoring monitor) {
        this(id, isPay, new ArrayList<>(), monitor);
    }
    /**
     * Покупатель
     * @param id Идентификатор покупателя
     * @param isPay Индикатор потребности купить (true/false)
     * @param cart Корзина покупателя
     * @param monitor Мониторинг работы магазина
     */
    public Customer(Integer id, Boolean isPay, List<Toy> cart, Monitoring monitor) {
        this.id = id;
        this.isPay = isPay;
        this.cart = cart;
        this.monitor = monitor;
    }

    /**
     * Генератор случайного листа покупателей из базы данных
     * @return случайный лист покупателей
     */
    public static List<Customer> getRandomList(Database db, Monitoring monitor) {
        // Количество покупателей
        Integer customersCount = Main.getRandomInt(5, 15);
        // Сам лист
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customersCount; i++) {
            customers.add(db.getCustomers().get(Main.getRandomInt(idMin, idMax)));
        }
        return customers;
    }

    /**
     * Распознать лист покупателей из листа строк со свойствами покупателей
     * @param contentList Лист строк со свойствами покупателей
     * @param monitor  Мониторинг работы магазина
     * @return Лист покупателей
     */
    public static List<Customer> tryParseList(List<String> contentList, Monitoring monitor) {
        var customers = new ArrayList<Customer>();
        Customer customer;
        for (var contentLine : contentList) {
            String[] contentElement = contentLine.split(";");
            customer = new Customer(
                    Integer.parseInt(contentElement[0]),
                    Boolean.parseBoolean(contentElement[1]),
                    monitor
            );
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Заполнение корзины покупателя случайным набором из базы данных
     */
    public void cartFillRandom(Database db, Monitoring monitor) {
        // Количество игрушек в корзине
        Integer toysCount = Main.getRandomInt(2, 5);
        // Заполнение корзины случайными id из базы игрушек
        for (int i = 0; i < toysCount; i++) {
            var rndToy = db.getToys().get(Main.getRandomInt(Toy.idMin, Toy.idMax)-Toy.idMin);
            this.cart.add(rndToy);
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

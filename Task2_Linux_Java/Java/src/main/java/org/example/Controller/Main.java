package org.example.Controller;

import org.example.Model.Customer;
import org.example.Model.Database;
import org.example.Model.Store;

import java.util.List;
import java.util.Random;

/** Задание 2.2.
 * Необходимо написать программу – розыгрыша игрушек в магазине детских товаров.
 * Желательный функционал программы:
 * > В программе должен быть минимум один класс со следующими свойствами:
 *    - id игрушки,
 *    - текстовое название,
 *    - количество
 *    - частота выпадения игрушки (вес в % от 100)
 * > Метод добавление новых игрушек и возможность изменения веса (частоты выпадения игрушки)
 * > Возможность организовать розыгрыш игрушек.
 */
public class Main {
    public static void main(String[] args) {

        // Магазин
        Store store = new Store();

        // Продавец пришел в магазин, всё перепроверил и инициировал открытие магазина
        // в db возвращается актуальные БД на сегодня
        Database db = store.open();

        // Лист всех потенциальных покупателей (жителей в округе) для данного магазина
        List<Customer> customers = db.getCustomers();

        // Поочередное оформление сделок (после выборки тех, кто намерен совершить покупку)
        for (var customer : customers) {
            // Покупатель намерен совершить покупку?
            if (!customer.getPay()) continue;
            // Покупатель собрал корзину
            customer.cartFillRandom(db);
            // ПОКУПАТЕЛЬ инициировал ПОКУПКУ, вместе с ПРОДАВЦОМ
            Transaction transaction = new Transaction(store, customer);
        }

        // Продавец инициировал закрытие магазина
        store.close();

    }

    /**
     * Генератор случайного целого числа
     * @param minValue левая граница для выбора случайного целого
     * @param maxValue правая граница для выбора случайного целого
     * @return случайное целое с minValue по maxValue включая границы
     */
    public static Integer getRandomInt(Integer minValue, Integer maxValue){
        return (new Random()).nextInt((maxValue - minValue) + 1) + minValue;
    }
}
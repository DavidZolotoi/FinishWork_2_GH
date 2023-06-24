package org.example.Controller;

import org.example.Model.Customer;
import org.example.Model.Contract;
import org.example.Model.db.Database;
import org.example.Model.Store;
import org.example.View.Monitoring;

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

        Monitoring monitor = new Monitoring();
        monitor.sendInfo("Рабочий день продавца начался.");

        String pathMain = "src\\main\\java\\org\\example\\Main.java";
        monitor.sendInfo("Опорный и относительный путь к Main в проекте: " + pathMain);

        Contract contract = new Contract();

        Store store = new Store(1, pathMain, monitor);

        Database db = store.open();
        monitor.sendInfo("Магазин открылся (продавец пришел в магазин, включил все системы, перепроверил, базы загружены.)");

        List<Customer> customers = db.getCustomers();

        monitor.sendInfo("Покупатели заходят, выходят, у кого-то появляется желание купить товар(ы).");
        for (var customer : customers) {
            if (!customer.getPay()) continue;
            monitor.sendInfo("***");
            customer.cartFillRandom(db, monitor);
            monitor.sendInfo("Покупатель с id=" + customer.getId() + " собрал корзину и пошел на кассу.");
            store.newCart(customer.getCart());

            contract.newTransaction(store, customer, monitor);
        }

        store.close();
        monitor.sendInfo("Рабочий день закончился. Магазин закрыт.");
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
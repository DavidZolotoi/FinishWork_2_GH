package org.example.Model;

import org.example.Controller.Main;
import org.example.Model.db.Database;
import org.example.View.Monitoring;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Магазин
 */
public class Store {

    /**
     * Идентификатор магазина
     */
    private Integer id;

    /**
     * Опорный путь к Main.java
     */
    private String pathMain;

    /**
     * Корзина от покупателя для оформления сделки
     */
    private List<Toy> cart;
    /**
     * Стоимость корзины
     */
    private Double cartPrice;

    /**
     * Мониторинг работы магазина
     */
    public Monitoring monitor;

    /**
     * Магазин
     * @param id Идентификатор магазина
     * @param pathMain Путь к базе данных магазина
     * @param monitor Мониторинг работы магазина
     */
    public Store(Integer id, String pathMain, Monitoring monitor) {
        this.id = id;
        this.pathMain = pathMain;
        this.cart = new ArrayList<>();
        this.cartPrice = 0.0;
        this.monitor = monitor;
    }

    /**
     * Открыть магазин (загрузить базы данных)
     * @return базу данных
     */
    public Database open() {
        Path filePathParent =   Paths.get  (
                                                (new File(this.pathMain)).getAbsolutePath() // относительный в абсолютный
                                            )
                                .getParent()
                                .resolve("Model")
                                .resolve("db");
        this.monitor.sendInfo("Папка-родитель для базы данных: " + filePathParent);
        FileOperation fileOperation = new FileOperation();
        List<Toy> toys = Toy.tryParseList(
                fileOperation.getContentList(filePathParent.toString() +  "\\dbToys.csv"),
                monitor
        );
        this.monitor.sendInfo("Загружена база данных игрушек из файла");
        List<Customer> customers = Customer.tryParseList(
                fileOperation.getContentList(filePathParent.toString() +  "\\dbCustomers.csv"),
                monitor
        );
        this.monitor.sendInfo("Загружена база данных всех потенциальных покупателей (жителей в округе) из файла");
        return new Database(customers, toys, this.monitor);
    }

    /**
     * Получает от покупателя корзину для расчета ее стоимости с учетом проведения розыгрыша
     * @param customerCart корзина от покупателя
     */
    public void newCart(List<Toy> customerCart){
        this.cart = customerCart;
        this.cartPrice = 0.0;
        monitor.sendInfo("Продавец принял корзину. Проверка игрушек в корзине на выигрыш в розыгрыше. Всего их: " + this.cart.size());
        Double totalSum = 0.0;
        for (var toy : this.cart) {
            totalSum += toy.getPrice();
            Long probability = Math.round(toy.getFreq());
            if (lottery(probability)) {
                this.monitor.sendInfo("УРА! Игрушка с артикулом " + toy.getId() +
                        " оказалась выигрышной в розыгрыше и будет предоставлена бесплатно!"
                );
                continue;
            }
            this.monitor.sendInfo("Игрушка с артикулом " + toy.getId() + " будет продана по своей стоимости: " + toy.getPrice());
            this.cartPrice += toy.getPrice();
        }
        this.monitor.sendInfo("Общая сумма чека: " + Math.round(totalSum * 100) / 100.0);
        this.monitor.sendInfo("Cумма чека с учетом розыгрыша: " + Math.round(this.cartPrice * 100) / 100.0);
    }
    /**
     * Проведение розыгрыша
     * @param probability шанс на успех (прописан в свойствах игрушки)
     * @return результат розыгрыша
     */
    private boolean lottery(Long probability){
        // 1) Разбросать true и false в массиве из 1000 элементов, где частота true = probability
        boolean[] razbros = new boolean[100];
        for (int i = 0; i < razbros.length; i++) {
            if ((i + 1) % (int) Math.round(razbros.length / probability) == 0) {
                razbros[i] = true;
                continue;
            }
            razbros[i] = false;
        }
        // 2) Выбрать рандомно
        return razbros[Main.getRandomInt(0, razbros.length - 1)];
    }

    /**
     * Закрыть магазин
     */
    public void close() {
        monitor.sendInfo("***");
        this.monitor.sendInfo("Работа всех процессов, также, как и самой программы завершена.");
        // обновить базы данных и закончить с ними работу
    }

    /**
     * Геттер
     * @return идентификатор
     */
    public Integer getId() {
        return id;
    }

}

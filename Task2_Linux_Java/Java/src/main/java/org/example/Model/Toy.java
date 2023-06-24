package org.example.Model;

import org.example.Controller.Main;
import org.example.Model.db.Database;
import org.example.View.Monitoring;

import java.util.ArrayList;
import java.util.List;

/**
 * Игрушка
 */
public class Toy {

    /**
     * Идентификатор игрушки
     */
    private Integer id;
    protected static  Integer idMin = 1000;
    protected static  Integer idMax = 9999;

    /**
     * Цена игрушки
     */
    private Double price;

    /**
     * Частота попадания в приз при розыгрыше (вес): от 0 до 100
     */
    private Double freq;

    /**
     * Мониторинг работы магазина
     */
    public Monitoring monitor;

    /**
     * Игрушка
     * @param id идентификатор игрушк
     * @param monitor Мониторинг работы магазина
     */
    public Toy(Integer id, Monitoring monitor) {
        this(id, (Main.getRandomInt(1000, 5000)+0.0), (Main.getRandomInt(0, 100) + 0.0), monitor);
    }
    /**
     * Игрушка
     * @param id идентификатор игрушки
     * @param price цена игрушки
     * @param monitor Мониторинг работы магазина
     */
    public Toy(Integer id, Double price, Monitoring monitor) {
        this(id, price, (Main.getRandomInt(0, 100) + 0.0), monitor);
    }
    /**
     * Игрушка
     * @param id идентификатор игрушки
     * @param price цена игрушки
     * @param freq частота попадания в приз при розыгрыше (вес): от 0 до 100
     * @param monitor Мониторинг работы магазина
     */
    public Toy(Integer id, Double price, Double freq, Monitoring monitor) {
        this.id = id;
        this.price = price;
        this.freq = freq;
        this.monitor = monitor;
    }

    /**
     * Генератор случайного листа игрушек из базы данных
     * @return случайный лист игрушек
     */
    public static List<Toy> getRandomList(Database db, Monitoring monitor) {
        // Количество игрушек
        Integer toysCount = Main.getRandomInt(5, 15);
        // Сам лист
        List<Toy> toys = new ArrayList<>();
        for (int i = 0; i < toysCount; i++) {
            toys.add(db.getToys().get(Main.getRandomInt(idMin, idMax)));
        }
        return toys;
    }

    /**
     * Распознать лист игрушек из листа строк со свойствами игрушек
     * @param contentList Лист строк со свойствами игрушек
     * @param monitor  Мониторинг работы магазина
     * @return Лист игрушек
     */
    public static List<Toy> tryParseList(List<String> contentList, Monitoring monitor) {
        var toys = new ArrayList<Toy>();
        Toy toy;
        for (var contentLine : contentList) {
            String[] contentElement = contentLine.split(";");
            toy = new Toy(
                    Integer.parseInt(contentElement[0]),
                    Double.parseDouble(contentElement[1]),
                    Double.parseDouble(contentElement[2]),
                    monitor
            );
            toys.add(toy);
        }
        return toys;
    }

    /**
     * Геттер
     * @return Id игрушки
     */
    public Integer getId() {
        return id;
    }
    /**
     * Геттер
     * @return цена игрушки
     */
    public Double getPrice() {
        return price;
    }
    /**
     * Сеттер
     * @param price цена игрушки
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    /**
     * Геттер
     * @return частота попадания в приз при розыгрыше (вес): от 0,00 до 1,00
     */
    public Double getFreq() {
        return freq;
    }
    /**
     * Сеттер
     * @param freq частота попадания в приз при розыгрыше (вес): от 0,00 до 1,00
     */
    public void setFreq(Double freq) {
        this.freq = freq;
    }
}

package org.example.Model;

import org.example.Controller.Main;

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
    protected static  Integer toyIdMin = 1000;
    protected static  Integer toyIdMax = 9999;
    /**
     * Частота попадания в приз при розыгрыше (вес): от 0,00 до 1,00
     */
    private double freq;

    /**
     * Игрушка
     * @param id идентификатор игрушки
     * @param freq частота попадания в приз при розыгрыше (вес): от 0,00 до 1,00
     */
    public Toy(Integer id, double freq) {
        this.id = id;
        this.freq = freq;
    }

    /**
     * Генератор случайного листа игрушек из базы данных
     * @return случайный лист игрушек
     */
    public static List<Toy> getRandomList(Database db) {
        // Количество игрушек
        Integer toysCount = Main.getRandomInt(5, 15);
        // Сам лист
        List<Toy> toys = new ArrayList<>();
        for (int i = 0; i < toysCount; i++) {
            toys.add(db.getToys().get(Main.getRandomInt(toyIdMin, toyIdMax)));
        }
        return toys;
    }

}

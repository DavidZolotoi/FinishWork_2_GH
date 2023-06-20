package org.example.Model;

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
     * Открыть магазин
     * @return базу данных
     */
    protected Database open() {
        // Загрузить базу игрушек из файла
        List<Toy> toys = new ArrayList<>();
        // Загрузить базу всех потенциальных покупателей (жителей в округе) из файла
        List<Customer> customers;
        return new Database(customers, toys);
    }

    /**
     * Закрыть магазин
     */
    public void close() {
    }
}

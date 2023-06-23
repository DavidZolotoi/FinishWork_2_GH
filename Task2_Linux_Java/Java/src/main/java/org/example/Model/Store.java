package org.example.Model;

import org.example.Model.db.Database;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * Магазин
     * @param id Идентификатор магазина
     * @param pathMain Путь к базе данных магазина
     */
    public Store(Integer id, String pathMain) {
        this.id = id;
        this.pathMain = pathMain;
    }

    /**
     * Открыть магазин
     * @return базу данных
     */
    public Database open() {
        // Родитель для базы данных
        Path filePathParent =   Paths.get  (
                                                (new File(this.pathMain)).getAbsolutePath() // относительный в абсолютный
                                            )
                                .getParent()
                                .getParent()
                                .resolve("Model")
                                .resolve("db");
        FileOperation fileOperation = new FileOperation();
        // Загрузить базу игрушек из файла
        List<Toy> toys = Toy.tryParseList(fileOperation.getContentList(filePathParent.toString() +  "\\dbToys.csv"));
        // Загрузить базу всех потенциальных покупателей (жителей в округе) из файла
        List<Customer> customers = Customer.tryParseList(fileOperation.getContentList(filePathParent.toString() +  "\\dbCustomers.csv"));
        return new Database(customers, toys);
    }

    /**
     * Закрыть магазин
     */
    public void close() {
    }

}

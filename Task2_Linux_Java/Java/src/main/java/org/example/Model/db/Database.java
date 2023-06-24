package org.example.Model.db;

import org.example.Model.Customer;
import org.example.Model.Toy;
import org.example.View.Monitoring;

import java.util.List;

/**
 * База данных
 */
public class Database {

    /**
     * База данных потенциальных покупателей (жители округа) для данного магазина (их от 1000 до 9999)
     */
    private List<Customer> customers;

    /**
     * База данных всех игрушек магазина
     */
    private List<Toy> toys;

    /**
     * Мониторинг работы магазина
     */
    public Monitoring monitor;

    /**
     * База данных
     * @param customers база данных потенциальных покупателей (жители округа) для данного магазина (их от 1000 до 9999)
     * @param toys база данных всех игрушек магазина
     * @param monitor мониторинг работы магазина
     */
    public Database(List<Customer> customers, List<Toy> toys, Monitoring monitor) {
        this.customers = customers;
        this.toys = toys;
        this.monitor = monitor;
        this.monitor.sendInfo("Базы данных прогружены.");
    }


    /**
     * Геттер
     * @return база данных покупателей
     */
    public List<Customer> getCustomers() {
        return customers;
    }
    /**
     * Сеттер в базу данных покупателей
     * @param customers база данных покупателей
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Геттер
     * @return база данных игрушек
     */
    public List<Toy> getToys() {
        return toys;
    }
    /**
     * Сеттер в базу данных игрушек
     * @param toys лист с базой данных для записи
     */
    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}

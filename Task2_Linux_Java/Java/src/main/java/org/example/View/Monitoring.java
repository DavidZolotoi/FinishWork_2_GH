package org.example.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Мониторинг работы магазина (это может быть сайт, бот или просто консоль, как в данном случае)
 */
public class Monitoring {

    /**
     * История процессов работы магазина
     */
    public List<String> history;

    /**
     * Мониторинг работы магазина
     */
    public Monitoring() {
        this.history = new ArrayList<>();
        sendInfo("Монитор для отслеживания процессов работы магазина открыт.");
    }

    /**
     * Показать историю процессов
     */
    public void getInfo(){
        System.out.println(this.history);
    }
    /**
     * Показать процесс из истории процессов с указанным номером
     * @param index необходимый номер процесса
     */
    public void getInfo(Integer index){
        System.out.println(this.history.get(index));
    }

    /**
     * Отправить в монитор сообщение
     * @param message сообщение, отправленное на монитор
     */
    public void sendInfo(String message){
        System.out.println(message);
        this.history.add(message);
    }
}

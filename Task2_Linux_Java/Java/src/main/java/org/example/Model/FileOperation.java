package org.example.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Работа с файлами
 */
public class FileOperation {
    /**
     * Объект для работы с файлами
     */
    public FileOperation() {
    }

    /**
     * Читает содержимое файла
     * @param filePath путь к файлу
     * @return Строки файла в виде листа
     */
    public List<String> getContentList(String filePath) {
        List<String> contentList = new ArrayList<>(); // Список для хранения строк файла

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String contentRow;
            while ((contentRow = bufferedReader.readLine()) != null) {
                contentList.add(contentRow);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentList;
    }
}

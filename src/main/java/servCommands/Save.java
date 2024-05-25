package servCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import server.CollectionSaver;
import server.ProductCollection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Класс команды, сохраняющий файл в заранее заданный файл (Data/FileToSaveCollection.json)
 */

public class Save {
    /**
     * метод, который с использованием objectMapper from jackson.datatype сериализующий коллекцию и записывающий ее в файл
     */
    public void execute(ProductCollection productCollection) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String path = System.getenv("FIFTH_LABA_PATH");
        if (path == null) {
            System.out.println("системной переменной с именем FIFTH_LABA_PATH не было найдено, создана пустая коллекция");
        } else {
            try {
                CollectionSaver saver = new CollectionSaver(productCollection.getProducts());
                String json = objectMapper.writeValueAsString(saver);
                Path collectionPath = Paths.get(path);
                BufferedWriter writer = new BufferedWriter(new FileWriter(collectionPath.toFile()));
                json = json.replace("}},", "}},\n");
                writer.write(json);
                writer.flush();
                //for (int i = 0; i<splitedJson.length; i++){
                //    if(i!=splitedJson.length-1)
                //        writer.write(splitedJson[i]+"}},");
                //    else
                //        writer.write(splitedJson[i]);
                //    writer.newLine();
                //    writer.flush();
                //}
//
                System.out.println("файл успешно сохранен!");
            } catch (IOException e) {
                System.out.println("ошибка записи файла");
            }
        }
    }
}


package servCommands;

import innerClasses.Month;
import server.ProductCollection;
import server.Server;

/**
 * Класс команды, выводящей в консоль краткую информацию о коллекции(дата создания, количество и тип элементов)
 */

public class Info implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        String[] data = productCollection.getDate().toString().split(" ");

        server.addMessage("Коллекция состоит из элементов типа Product, она была создана " +
                data[2] + " " + Month.getMounthTranslation(data[1]) + " " + data[5] + " года в " + data[3] +
                ". На данный момент она содержит " + productCollection.getLen() + " различных элементов");
    }
}

package servCommands;

import server.*;
import client.ProductCli;

/**
 * Класс, команды добавляющий новый элемент в коллекцию
 */
public class Add implements Command {
    /**
     * метод, создающий объект с помощью метода класса Product и записывающий его в коллекцию
     */
    @Override
    public void execute(ProductCollection productCollection, Object p, Server server) {
        ProductCli productCli = (ProductCli) p;
        System.out.println(productCli + "fgsdfgdfs");
        System.out.println("dafadfadsfa");
        Product product = new Product(productCli, productCollection);
        productCollection.addProduct(product);

        server.addMessage("Продукт успешно добавлен");
    }
}

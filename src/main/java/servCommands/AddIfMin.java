package servCommands;

import server.*;
import server.Product;
import client.ProductCli;

/**
 * Класс, команды добавляющий новый элемент в коллекцию, если его значение меньше минимального в коллекции
 */
public class AddIfMin implements Command {
    /**
     * метод, создающий объект с помощью метода класса Product и записывающий его в коллекцию, если он меньше минимального элемента коллекции
     */
    @Override
    public void execute(ProductCollection productCollection, Object p, Server server) {
        ProductCli productCli = (ProductCli) p;
        Product product = new Product(productCli, productCollection);
        if(productCollection.getMinProduct().compareTo(product)>0){
            productCollection.addProduct(product);
            server.addMessage("Продукт успешно добавлен");
        }else{
            server.addMessage("Продукт не был добавлен так как он не является минимальным(сортировка идет в алфавитном порядке имени продукта)");
        }
    }
}

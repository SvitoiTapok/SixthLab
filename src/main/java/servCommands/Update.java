package servCommands;


import client.ProductCli;
import server.Product;
import server.ProductCollection;
import server.Server;

/**
 * Класс команды, обновляющий элемент по заданному id
 */

public class Update implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        long id = (long) p[0];
        if(!productCollection.getID().contains(id)){
            server.addMessage("элемента с введенным id не существует, пожалуйста, введите существующий id");
        }
        else{
            ProductCli productCli = (ProductCli) p[1];
            Product product = new Product(productCli, id, productCollection);
            productCollection.addProduct(product);
            server.addMessage("продукт с id:" + id + " успешно обновлен");
        }
    }
}

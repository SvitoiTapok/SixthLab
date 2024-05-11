package servCommands;

import server.Product;
import client.ProductCli;
import server.ProductCollection;
import server.Server;

public class Update2 implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object p, Server server) {
        ProductCli productCli = (ProductCli) p;
        Product product = new Product(productCli, productCollection);
        productCollection.addProduct(product);

        server.addMessage("Продукт успешно обновлен");
    }
}

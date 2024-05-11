package servCommands;

import server.Product;
import client.ProductCli;
import server.ProductCollection;
import server.Server;

import java.util.LinkedHashSet;

/**
 * Класс команды, удаляющей из коллекции все элементы меньше заданного(сортировка выполняется в лексикографическом порядке по именам продуктов)
 */

public class RemoveLower implements Command{
    public void execute(ProductCollection productCollection, Object p, Server server) {
        ProductCli productCli = (ProductCli) p;
        Product product = new Product(productCli, productCollection);
        LinkedHashSet<Product> prods = productCollection.getProducts();
        LinkedHashSet<Product> removedProds = new LinkedHashSet<>();
        for(Product prod: prods){
            if(prod.compareTo(product)<0)
                removedProds.add(prod);
        }
        productCollection.removeProducts(removedProds);
        server.addMessage("Продукты удалены");
    }
}

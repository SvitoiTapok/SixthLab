package servCommands;

import server.ProductCollection;
import server.*;

import java.util.Set;
import java.util.stream.Collectors;
/**
 * Класс команды, выводящей все элементы коллекции, значение поля name которых начинается с заданной строки
 */

public class FilterStartsWithName implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        String StrBegin = (String) p[0];
        Set<Product> products = productCollection.getProducts().stream().filter(x -> x.getName().startsWith(StrBegin)).collect(Collectors.toSet());
        for(Product product: products){
            server.addMessage(product.toString());
        }
    }

}

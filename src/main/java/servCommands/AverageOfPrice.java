package servCommands;

import server.*;
/**
 * Класс, команды возвращающей среднее значение цены у всех элементов коллекции
 */

public class AverageOfPrice implements Command{
    /**
     * метод, выводящий в консоль среднее значение цены у всех элементов коллекции
     */

    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        if(productCollection.getLen()==0)
            server.addMessage("В коллекции нет ни одного продукта");
        else {
            server.addMessage("Cреднее значение цены по всем продуктам: ");
            server.addMessage(String.valueOf((Math.round(productCollection.getProducts().stream()
                    .map(Product::getPrice).reduce(Float::sum).get() /
                    productCollection.getLen() * 100000) / 100000)));
        }
    }
}

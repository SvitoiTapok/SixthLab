package servCommands;

import server.*;

/**
 * Класс команды, выводящий элементы коллекции в консоль
 * */

public class Show implements Command{
    /**
    * метод, Выводящий все элементы коллекции в консоль(вызывает to string у ProductCollection)
    */
    @Override
    public void execute(ProductCollection productCollection, Object p, Server server) {
        if(productCollection.getProducts().isEmpty()){
            server.addMessage("На данный момент в коллекции нет элементов");
            return;
        }
        for(Product product: productCollection.getProducts()){
            server.addMessage(product.toString());
        }
    }


}

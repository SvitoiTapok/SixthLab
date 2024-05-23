package servCommands;


import server.ProductCollection;
import server.Server;

/**
 * Класс команды, удаляющей из коллекции элемент по id
 */

public class RemoveByID implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        int id = (int) p[0];
        productCollection.removeProduct(id);
        server.addMessage("объект с id=" + id + "(если такой был) удален");
    }
}

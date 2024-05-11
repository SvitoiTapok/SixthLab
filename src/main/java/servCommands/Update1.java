package servCommands;


import server.ProductCollection;
import server.Server;

/**
 * Класс команды, обновляющий элемент по заданному id
 */

public class Update1 implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object p, Server server) {
        long id = (long) p;
        if(!productCollection.getID().contains(id))
            server.addMessage("элемента с введенным id не существует, пожалуйста, введите существующий id");
        else
            server.addMessage("Update2(validID)");
    }
}

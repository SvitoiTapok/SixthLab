package servCommands;

import server.ProductCollection;
import server.Server;

public class Write implements Command{
    @Override
    public void execute(ProductCollection productCollection, Object[] p, Server server) {
        server.addMessage((String) p[0]);
    }

}

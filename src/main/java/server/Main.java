package server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        ProductCollection productCollection = new ProductCollection();
        //boolean isConnectionCreated = server.createConnection();
        server.createConnection();
        communictate(productCollection, server);
        }
    public static void communictate(ProductCollection productCollection, Server server){
        while (!server.serverWaiting()){}
        while (true) {
            server.getCommand(productCollection);
            server.sendMessage();
        }

    }
}

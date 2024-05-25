package server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        ProductCollection productCollection = new ProductCollection();
        //boolean isConnectionCreated = server.createConnection();
        server.createConnection();
        System.out.println("Сервер готов к подключению");
        communictate(productCollection, server);
        }
    public static void communictate(ProductCollection productCollection, Server server){
        while (!server.serverWaiting()){
            server.executeServCommand(productCollection);
        }
        while (true) {
            server.executeServCommand(productCollection);
            server.getCommand(productCollection);
            server.sendMessage();
        }

    }
}

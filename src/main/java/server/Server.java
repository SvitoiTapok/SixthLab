package server;

import servCommands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    private final int port = 1110;
    private SocketChannel sock;
    private ServerSocketChannel serv;
    private ByteBuffer buf;
    private byte[] data = {1, 2, 3,6,6,6,6,6,6,6,6,6,6,6,6,6,66,6,6,6,66,6,6,6,};
    private ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
    private ObjectInputStream objectInputStream;

    {
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String message="";

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private ObjectOutputStream objectOutputStream;

    public boolean createConnection() {
        try {
            serv = ServerSocketChannel.open();
            serv.bind(new InetSocketAddress(port));
            serv.configureBlocking(false);
            return true;
        }catch (IOException e) {
            System.out.println("Проблемы с созданием сервера");
            e.printStackTrace();
            return false;
        }
    }
    public boolean serverWaiting(){
        try {
            //System.out.println("Сервер готов");
            sock = serv.accept();
            if (sock == null){
                //System.out.println("Попытка установить соединение не увенчалась успехом");
                ///потом убрать
                return false;
            }
            System.out.println("Соединение установленно");
            return true;
        } catch (IOException e) {
            System.out.println("Неизвестная ошибка в процессе установления соединения с клиентом");
            return false;
        }
    }

    public void getCommand(ProductCollection productCollection){
        byte[] data = new byte[20000];
        buf = ByteBuffer.wrap(data);
        try {
            sock.read(buf);
        }catch (IOException e) {
            System.out.println("Соединение было прервано, повторая попытка подключения");
            Main.communictate(productCollection, this);
            return;
        }
        try {
                //deserialize
                byteArrayInputStream = new ByteArrayInputStream(data);
                Command command = (Command) objectInputStream.readObject();
                System.out.println(command.getClass());
                Object p = objectInputStream.readObject();
                objectInputStream.close();
                command.execute(productCollection, p, this);

            }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void addMessage(String message){
        this.message += message;
    }

    public byte[] serializeMessage(String message){
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            System.out.println("Возникли проблемы с сериализацией");
            return null;
        }
    }

    public void sendMessage(){
        byte[] data;
        data = serializeMessage(message);
        buf = ByteBuffer.wrap(data);
        try {
            sock.write(buf);
            System.out.println("сообщение отправлено");
            this.message = "";
        }catch (IOException e){
            System.out.println("Возникли проблемы с соединением");
            return;
        }




    }

}

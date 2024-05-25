package server;

import servCommands.Command;
import servCommands.Save;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Server {
    private final int port = 45000;
    //private final int port = 1110;
    private SocketChannel sock;
    private ServerSocketChannel serv;
    private final Scanner scanner = new Scanner(System.in);

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

    //чтение команды вводимой в консоль сервера
    public void executeServCommand(ProductCollection productCollection){
        try {
            if(System.in.available()>0){
                String command = scanner.nextLine();
                if(command.equals("save")){
                    Save save = new Save();
                    save.execute(productCollection);
                }else {
                    if (command.equals("exit")) {
                        Save save = new Save();
                        save.execute(productCollection);
                        System.out.println("Выключение сервера");
                        System.exit(0);
                    } else {
                        System.out.println("у сервера нет команды:" + command + ", у него есть только команды save и exit");

                    }
                }
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    public void getCommand(ProductCollection productCollection){
        byte[] data = new byte[64000];
        ByteBuffer buf = ByteBuffer.wrap(data);
        try {
            sock.read(buf);
        }catch (IOException e) {
            System.out.println("Соединение было прервано, повторная попытка подключения");
            Main.communictate(productCollection, this);
            return;
        }
        try {
                //deserialize
                executeCommand(data, productCollection);
        }
        catch (Exception e) {

            //throw new RuntimeException(e);
        }

    }


    public void executeCommand(byte[] data, ProductCollection productCollection) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            Command command = (Command) objectInputStream.readObject();
            //System.out.println(command);
            //System.out.println(command.getClass() + " " + command);
            Object[] p = new Object[10];
            byte i=0;
            //System.out.println(byteArrayInputStream.available());
            //System.out.println(objectInputStream.readObject());
            while (true){
                try {
                    p[i++] = objectInputStream.readObject();
                }
                catch (IOException e){
                    break;
                }
            }

            //System.out.println(p);
            // Закрываем только ObjectInputStream
            objectInputStream.close();

            command.execute(productCollection, p, this);
        } catch (IOException | ClassNotFoundException e) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Соединение было прервано, повторная попытка подключения");
            Main.communictate(productCollection, this);
        }
    }
    public void addMessage(String message){
        this.message += message;
    }

    public byte[] serializeMessage(String message){
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
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
        //System.out.println(message);
        data = serializeMessage(message);
        ByteBuffer buf = ByteBuffer.allocate(64000);
        buf.put(data);
        //System.out.println(new String(data));
        buf.flip();
        try {
            sock.write(buf);
            buf.clear();
            System.out.println("сообщение отправлено");
            this.message = "";
        }catch (IOException e){
            System.out.println("Возникли проблемы с соединением");
        }




    }

}

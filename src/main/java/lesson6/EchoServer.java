package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(18809);
            System.out.println("Ожидание подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключен к серверу");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            echoMessage(dataInputStream, dataOutputStream);
            messageToClient(dataInputStream, dataOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void echoMessage(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = dataInputStream.readUTF();
                        if (message.equals("/end")) {
                            dataOutputStream.writeUTF(message);
                            break;
                        }
                        System.out.println("На сервер поступило сообщение от клиента: " + message);
                        dataOutputStream.writeUTF("EchoServer: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
    }
    private static void messageToClient(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Введите сообщение к клиенту: ");
                        String messageToClient = (String) scanner.nextLine();
                        dataOutputStream.writeUTF("Сообщение от сервера: " + messageToClient);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}





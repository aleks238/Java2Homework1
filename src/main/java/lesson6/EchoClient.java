package lesson6;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class EchoClient {
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 18809;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    Scanner scanner = new Scanner(System.in);
    private void openConnection() throws IOException, InterruptedException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        getMesageFromServer();
    }
    private void getMesageFromServer() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        String messageFromServer = dataInputStream.readUTF();
                        if(messageFromServer.equals("/end")){
                            break;
                        }
                        System.out.println((messageFromServer));
                        System.out.println();
                    }
                    System.out.println(("Connection closed"));
                    closeConnection();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
    }
    private void sendMessage() {
        try {
            while (true) {
                System.out.println("Введите сообщение: ");
                String clientMessage = scanner.nextLine();
                dataOutputStream.writeUTF(clientMessage);
                if (clientMessage.equals("/end")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void closeConnection(){
        try {
            dataOutputStream.close();
        }catch (Exception e){
     }
        try {
            dataInputStream.close();

        }catch (Exception e){
        }
        try {
            socket.close();

        }catch (Exception e){
        }
    }
    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        try {
            echoClient.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        echoClient.sendMessage();
    }
}


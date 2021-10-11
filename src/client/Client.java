package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client  {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ClientThread clientRun;
    private Scanner scanner;
    private String userInput = "empty";

    public Client(String ip, int port)throws IOException {
        scanner = new Scanner(System.in);
        socket = new Socket(ip,port);
        input = new ObjectInputStream(this.socket.getInputStream());
        output = new ObjectOutputStream(this.socket.getOutputStream());
        clientRun = new ClientThread(this.socket);
        startConnection();

    }
    public void startConnection() throws IOException {
        new Thread(clientRun).start();
        do {
            System.out.println("Envie su numero");
            userInput = scanner.nextLine();
            output.writeObject(new String((userInput)));
            if (userInput.equals("SALIR")){
                input.close();
                break;
            }
        }while (!userInput.equals("SALIR"));
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 23);
    }

}

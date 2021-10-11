package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client  {

    private Socket socket;

    private ClientThread clientRun;
    private String id;
    private DataInputStream input;
    private DataOutputStream output;
    private Scanner scanner;
    private String userInput = "empty";

    public Client(String ip, int port)throws IOException {
        socket = new Socket(ip,port);
        scanner = new Scanner(System.in);
        clientRun = new ClientThread(this.socket);
        input = new DataInputStream(this.socket.getInputStream());
        output = new DataOutputStream(this.socket.getOutputStream());
        startConnection();
        System.out.println("Construido constructor");

    }
    public void startConnection() throws IOException {
        new Thread(clientRun).start();
        /**do {
            System.out.println("Envie su ID");
            userInput = scanner.nextLine();
            output.writeUTF(userInput);
            System.out.println(userInput +"   |||||Esto es el UserInput");
            if (userInput.equals("SALIR")){
                input.close();
                break;
            }
        }while (true);*/

    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 23);
    }

}

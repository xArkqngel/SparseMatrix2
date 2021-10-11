package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
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


    }

    /**
     * Inicialización cliente
     * @throws IOException
     */
    public void startConnection() throws IOException {
        new Thread(clientRun).start();


    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 23);
    }

}

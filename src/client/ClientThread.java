package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable{
    private Socket socket;
    private Scanner scanner;
    private DataInputStream inputStream;
    private DataOutputStream output;
    private String myId;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(System.in);


    }

    @Override
    public void run() {
        System.out.println("Acá en el run del client thread");
        try {
            System.out.println("Escriba su ID");
            this.inputStream = new DataInputStream(this.socket.getInputStream());
            String message = scanner.nextLine();
            System.out.println(message + " <--- Su ID");
            this.output = new DataOutputStream(this.socket.getOutputStream());
            this.output.writeUTF(message);

            //Leo el menu
            System.out.println("Menu -->"+ "\n " + this.inputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendId(String id) throws IOException {

        System.out.println("Dentro del metodo SendId " + id);
        this.output.writeUTF(id);
    }
}

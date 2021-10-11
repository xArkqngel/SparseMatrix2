package client;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
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

    /**
     * Inicialización hilo cliente
     */
    @Override
    public void run() {

        try {
            System.out.println("Escriba su ID");
            this.inputStream = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
            String message = scanner.nextLine();
            System.out.println(message + " <--- Su ID");
            this.output.writeUTF(message);

            //Leo el menu
            int choice = 0;
            String menu = this.inputStream.readUTF();
            do {
                System.out.println(menu);
                System.out.println("Ingrese lo que desea hacer --> ");
                choice = this.scanner.nextInt();
                this.output.writeInt(choice);
                // Envio la opcion
                switch (choice){
                    case 1:
                        System.out.println("--- Esogiste la opcion 1 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la info");
                        String info = this.scanner.next();
                        this.output.writeUTF(info);
                        break;
                    case 2:
                        System.out.println("--- Esogiste la opcion 2 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        String received = this.inputStream.readUTF();
                        System.out.println(received);
                        break;
                    case 3:
                        System.out.println("--- Esogiste la opcion 3 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la nueva info de esa celda");
                        String newInfo = this.scanner.next();
                        this.output.writeUTF(newInfo);
                        String received2 = this.inputStream.readUTF();
                        System.out.println(received2);
                        break;

                }



            }while (choice!=8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendId(String id) throws IOException {
        System.out.println("Dentro del metodo SendId " + id);
        this.output.writeUTF(id);
    }

    public float manageIncomToFloat(String incom){
        return Float.parseFloat(incom);
    }
}

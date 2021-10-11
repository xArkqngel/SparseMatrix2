package server;

import myMatrix.MyMatrix;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList <ServerThread> serverThreads;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String userId = " ";
    private MyMatrix <Float, Float, String> matrix;

    public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) throws IOException {
        this.socket = socket;
        this.serverThreads = serverThreads;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**public void setIncomingUserId(){
        try {
            this.setUserId((String) (objectInputStream.readUTF()));
            System.out.println("Usuario conectado con el ID --> " + this.userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * Inicializcación del hilo
     */
    @Override
    public void run() {
        System.out.println("Esperando para leer ");
        String aux = null;
        try {
            aux = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setUserId(aux);
        System.out.println("User Id  ahora --> " + this.getUserId());
        try {
            while (dataInputStream.readUTF()!="8"){
                dataOutputStream.writeUTF(this.menu());
            }

        }catch (Exception e){
            System.out.println("Ups ha ocurrido un error" + e);

        }





    }

    public String menu(){
        return "1. Añadir un dato en una fila y columna especificada\n" +
                "2. Obtener el dato de una fila y columna especificada" +
                "3. Modificar un dato en una columna, fila y con el valor del dato" +
                "4. Borrar un dato en una columna, fila y con el valor del dato " +
                "5. Encontrar la cantidad de elementos dentro de un area rectangular"+
                "6. Encontrar la cantidad de elementos dentro de un area circular"+
                "7. Encontrar la distancia entre dos elementos";
    }
}

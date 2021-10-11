package server;

import myMatrix.MyMatrix;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
            while (true){

            }

        }catch (Exception e){
            System.out.println("Ups ha ocurrido un error" + e);

        }





    }

    public String menu(){
        return "1.  ";
    }
}

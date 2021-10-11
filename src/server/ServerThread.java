package server;

import myMatrix.MyMatrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList <ServerThread> serverThreads;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String userId;
    private MyMatrix <Float, Float, String> matrix;

    public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) throws IOException {
        this.socket = socket;
        this.serverThreads = serverThreads;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setIncomingUserId(){
        try {
            this.setUserId((String) (objectInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            setIncomingUserId();
            System.out.println("Usuario conectado con el ID --> " + this.userId);
        }
    }
}

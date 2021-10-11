package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket socket;
    private ObjectInputStream inputStream;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
            while (true){
                String message = (String) inputStream.readObject();
                System.out.println(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

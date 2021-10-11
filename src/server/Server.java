package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList <ServerThread> serverThreads;
    private ServerSocket serverSocket;
    private ServerThread serverThread;
    private Socket socket;
    private boolean isRunning = true;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.serverThreads = new ArrayList<>();
        this.startMyThread();
    }
    public void startMyThread() throws IOException {
        System.out.println("[*] Running on port --> " + this.serverSocket.getLocalPort());
        while(isRunning){
            try {
                socket = this.serverSocket.accept();
                System.out.println("[*] Usuario conectado --> "+ socket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
            {
                this.serverThread = new ServerThread(socket,this.serverThreads);
                serverThreads.add(serverThread);
                serverThread.start();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(23);
    }
}

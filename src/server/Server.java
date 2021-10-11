package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
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

    /**
     * Inicializacion del Servidor
     * @throws IOException
     */
    public void startMyThread() throws IOException {
        System.out.println("[*] Running on port --> " + this.serverSocket.getLocalPort());
        while(isRunning){
            try {
                socket = this.serverSocket.accept();
                System.out.println("[*] Usuario conectado --> "+ socket.getInetAddress().getHostAddress());
                this.serverThread = new ServerThread(socket,this.serverThreads);
                serverThreads.add(serverThread);
                serverThread.changeTime();
                serverThread.start();


            } catch (IOException | InterruptedException e) {
                System.out.println("Ups hubo un error " + e);
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(23);
    }
}

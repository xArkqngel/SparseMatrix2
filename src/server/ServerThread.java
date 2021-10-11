package server;

import myMatrix.MyMatrix;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

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
        this.matrix = new MyMatrix<>((x, y) -> x.compareTo(y), (x, y) -> x.compareTo(y), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




    @Override
    public void run() {

        String aux = null;
        try {
            aux = dataInputStream.readUTF();
            this.setUserId(aux);
            System.out.println("User Id  ahora --> " + this.getUserId());
            dataOutputStream.writeUTF(this.menu());
            //Manejo la entrada
            int opcionEntrada = 0;
            do {

                 opcionEntrada = dataInputStream.readInt();
                switch (opcionEntrada){
                    case 1:
                        //TC TR Info
                        // Float Float String
                        Float aux1 = (Float)this.dataInputStream.readFloat();
                        Float aux2 = (Float)this.dataInputStream.readFloat();
                        String info = this.dataInputStream.readUTF();
                        this.matrix.add( aux1,aux2,info);
                        break;
                    case 2:

                        String out = this.matrix.get((Float)this.dataInputStream.readFloat(),(Float)this.dataInputStream.readFloat());
                        this.dataOutputStream.writeUTF("Dato encontrado --> " + out);
                        break;
                    case 3:
                        Float aux3 = (Float)this.dataInputStream.readFloat();
                        Float aux4 = (Float)this.dataInputStream.readFloat();
                        String infoToChange = this.dataInputStream.readUTF();
                        System.out.println(aux3+ "," + aux4 + infoToChange);
                        this.matrix.set(aux3,aux4,infoToChange);
                        String newInfo = "Modificado --> " + this.matrix.get(aux3,aux4);
                        this.dataOutputStream.writeUTF(newInfo);
                        break;

                }
            }while (opcionEntrada!=8);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void add(Float aux1, Float aux2, String aux ){
        this.matrix.add(aux1,aux2,aux);
    }

    public String menu(){
        return  "-------------------------Bienvenido a Pokemon GO-------------------------\n"+
                "1. Añadir un dato en una fila y columna especificada\n" +
                "2. Obtener el dato de una fila y columna especificada\n" +
                "3. Modificar un dato en una columna, fila y con el valor del dato\n" +
                "4. Borrar un dato en una columna, fila y con el valor del dato\n" +
                "5. Encontrar la cantidad de elementos dentro de un area rectangular\n"+
                "6. Encontrar la cantidad de elementos dentro de un area circular\n"+
                "7. Encontrar la distancia entre dos elementos\n"+
                "8. Salir";
    }

    public void handle(int option){
        switch (option){
            case 1:
                System.out.println("Sirve case 1");
                break;
            case 2:
            default:
                break;

        }
    }
}

package server;

import myMatrix.MyMatrix;
import persistence.JsonFileManager;
import pokemons.Pokemon;

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
    private MyMatrix <Float, Float, Pokemon> matrix;
   private ArrayList <Pokemon> pokemons;

    public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) throws IOException {
        this.socket = socket;
        this.serverThreads = serverThreads;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        this.matrix = new MyMatrix<>((x, y) -> x.compareTo(y), (x, y) -> x.compareTo(y), new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o2.getId()-o1.getId();
            }


        });
        this.pokemons = new ArrayList<>();
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
        this.addPokemons();
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

                    /*+case 1:
                        //TC TR Info
                        // Float Float String
                        Float aux1 = (Float)this.dataInputStream.readFloat();
                        Float aux2 = (Float)this.dataInputStream.readFloat();
                        String info = this.dataInputStream.readUTF();
                        this.matrix.add( aux1,aux2,info);
                        break;*/
                    case 1:

                        String out = this.matrix.get((Float)this.dataInputStream.readFloat(),(Float)this.dataInputStream.readFloat()).toString();
                        this.dataOutputStream.writeUTF("Dato encontrado --> " + out);
                        break;
                    case 2:
                        Float aux3 = (Float)this.dataInputStream.readFloat();
                        Float aux4 = (Float)this.dataInputStream.readFloat();
                        String infoToChange = this.dataInputStream.readUTF();
                        //this.matrix.set(aux3,aux4,infoToChange);
                        String newInfo = "Modificado --> " + this.matrix.get(aux3,aux4);
                        this.dataOutputStream.writeUTF(newInfo);
                        break;
                    case 3:
                        Float aux5 = (Float)this.dataInputStream.readFloat();
                        Float aux6 = (Float)this.dataInputStream.readFloat();
                        String infoToDelete = this.dataInputStream.readUTF();
                        this.matrix.delete(aux5,aux6,this.matrix.get(aux5,aux6));
                        String outDelete = "Dato --> " + this.matrix.get(aux5,aux6);
                        this.dataOutputStream.writeUTF(outDelete);
                        System.out.println(outDelete);
                        break;
                    case 4:



                }
            }while (opcionEntrada!=7);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public String menu(){
        return  "-------------------------Bienvenido a Pokemon GO-------------------------\n"+
                "" +
                "1. Obtener el dato de un Pokemon con su ubicacion\n" +
                "2. \n" +
                "3. " +
                "4. Encontrar la cantidad de elementos dentro de un area rectangular\n"+
                "5. Encontrar la cantidad de pokemons cerca a ti, en un radio circular\n"+
                "6. Encontrar la distancia entre el usuario -> " + this.userId + "y un pokemon \n"+
                "7. Salir";
    }



    public void addPokemons(){
        ArrayList <Pokemon> pokemons =  new ArrayList<>();
        pokemons = JsonFileManager.readFile("src/data/pokedex.json");
        System.out.println(pokemons.size());
        for (Pokemon pokemon: pokemons) {
            System.out.println(pokemon);
            float aux1 = this.random()*10;
            float aux2 = this.random()*10;
            System.out.println("Aux1-->" + aux1 +',' + "Aux 2--->" + aux2);
            this.matrix.add(aux1,aux2, pokemon);

        }
    }

    public float random(){
        return (float) Math.random();
    }
}

package server;

import myMatrix.MyMatrix;
import persistence.JsonFileManager;
import pokemons.Pokemon;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> serverThreads;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String userId = " ";
    private MyMatrix<Float, Float, Pokemon> matrix;
    private ArrayList<Pokemon> pokemons;
    private Float longitud;
    private Float latitud;
    private int radio = 30;

    public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) throws IOException {
        this.socket = socket;
        this.serverThreads = serverThreads;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        this.matrix = new MyMatrix<>((x, y) -> x.compareTo(y), (x, y) -> x.compareTo(y), new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o2.getId() - o1.getId();
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


    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    @Override
    public void run() {

        String aux = null;
        this.addPokemons();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            changeMatrix();
          }
        }, 0, 60000);
        try {
            aux = dataInputStream.readUTF();
            this.setUserId(aux);
            System.out.println("Conectado user id  --> " + this.getUserId());
            longitud = this.dataInputStream.readFloat();
            latitud = this.dataInputStream.readFloat();
            dataOutputStream.writeUTF(this.menu());
            //Manejo la entrada
            int opcionEntrada = 0;

            do {

                opcionEntrada = dataInputStream.readInt();
                switch (opcionEntrada) {


                    case 1:

                        String out = " Dato -> " + this.matrix.get((Float) this.dataInputStream.readFloat(), (Float) this.dataInputStream.readFloat());

                        this.dataOutputStream.writeUTF("Dato encontrado --> " + out);

                        break;
                    case 2:

                        ArrayList<Pokemon> auxPokemons = this.matrix.numberInCircualArea(longitud, latitud, this.radio);
                        String newInfo = "";
                        for (Pokemon pokemon : auxPokemons) {
                            newInfo += "Pokemon encontrado -->" + pokemon.toString() + "\n";
                        }
                        this.dataOutputStream.writeUTF(newInfo);
                        break;
                    case 3:

                        String infoToSearch = this.dataInputStream.readUTF();

                        int pos = this.returnPosArray(infoToSearch);
                        if (pos!=-1){
                            System.out.println(pos);
                            Pokemon pokeAux = this.pokemons.get(pos);
                            Float columnSearch = this.matrix.searchColumn(pokeAux);
                            Float rowSearch = this.matrix.searchRow(pokeAux);
                            String distanceBetween = "Distancia entre el pokemon y el usuario en metro es de " + this.userId + " --> " + this.matrix.distanceBetween(this.longitud,this.latitud,rowSearch,columnSearch);
                            this.dataOutputStream.writeUTF(distanceBetween);

                        }else {
                            this.dataOutputStream.writeUTF("Ese pokemon no está en nuestra base de datos :/");
                        }

                        break;
                    case 4:
                        ArrayList<Pokemon> nearPokemons = this.matrix.numberInCircualArea(latitud, longitud, 5);
                        String outCaptured = "";
                        int count = 1;
                        if (nearPokemons.isEmpty()) {
                            this.dataOutputStream.writeUTF("No hay pokemons para atrapar");
                            break;
                        } else {
                            for (Pokemon pokemon : nearPokemons) {
                                outCaptured += "[" + count + "] Tienes cerca al " + pokemon.getName() + " salvaje\n";
                                count++;
                            }
                            outCaptured += "Escribe el numero del pokemon que quieres atrapar";
                            this.dataOutputStream.writeUTF(outCaptured);

                            int option = this.dataInputStream.readInt();

                            Pokemon pokemonMine = nearPokemons.get(option-1);
                            String pokemon = pokemonMine.toString();
                            this.dataOutputStream.writeUTF(pokemon);
                            Float column = this.matrix.searchColumn(pokemonMine);
                            Float row = this.matrix.searchRow(pokemonMine);
                            this.matrix.delete(column, row, pokemonMine);




                        }


                        break;
                    case 6:
                        Float latChange = this.dataInputStream.readFloat();
                        Float lonChange = this.dataInputStream.readFloat();
                        this.setLatitud(latChange);
                        this.setLongitud(lonChange);
                        String notice = "Coordenadas cambiadas satisfactoriamente a " + this.getLatitud() + " latitud, "
                                + this.getLongitud() + " longitud";
                        this.dataOutputStream.writeUTF(notice);
                        break;


                }

            } while (opcionEntrada != 7);

        } catch (IOException  e) {
            e.printStackTrace();
        }


    }

    public int returnPosArray(String  name){

        for (int i = 0; i < this.pokemons.size(); i++) {
            if (pokemons.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;

    }


    public String menu() {
        return "-------------------------Bienvenido a Pokemon GO-------------------------\n" +
                "" +
                "1. Obtener el dato de un Pokemon con su ubicacion\n" +
                "2. Encontrar la cantidad de pokemons cerca a ti, en un radio circular\n" +
                "3. Encontrar la distancia entre el usuario -> " + this.userId + " y un pokemon\n" +
                "4. Preguntar si tienes un pokemon cerca para atraparlo\n" +
                "5. Mostrar tus pokemons capturados\n" +
                "6. Cambiar posicion del cliente\n" +

                "7. Salir\n" +
                "-------------------------------------------------------------------------";
    }


    public void addPokemons() {
        ArrayList<Pokemon> pokemons2 = new ArrayList<>();
        this.pokemons = JsonFileManager.readFile("src/data/pokedex.json");

        for (Pokemon pokemon : pokemons) {

            float aux1 = this.random() * 100;
            float aux2 = this.random() * 100;

            this.matrix.add(aux1, aux2, pokemon);

        }
    }





    public float random() {
        return (float) Math.random();
    }
}

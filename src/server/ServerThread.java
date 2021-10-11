package server;

import myMatrix.MyMatrix;
import persistence.JsonFileManager;
import pokemons.Pokemon;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

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
        try {
            aux = dataInputStream.readUTF();
            this.setUserId(aux);
            System.out.println("User Id  ahora --> " + this.getUserId());
            longitud = this.dataInputStream.readFloat();
            latitud = this.dataInputStream.readFloat();
            System.out.println("latitud " + latitud + "," + "Longitud -> " + longitud);
            dataOutputStream.writeUTF(this.menu());
            //Manejo la entrada
            int opcionEntrada = 0;
            this.changeTime();
            do {

                opcionEntrada = dataInputStream.readInt();
                switch (opcionEntrada) {

                    /*+case 1:
                        //TC TR Info
                        // Float Float String
                        Float aux1 = (Float)this.dataInputStream.readFloat();
                        Float aux2 = (Float)this.dataInputStream.readFloat();
                        String info = this.dataInputStream.readUTF();
                        this.matrix.add( aux1,aux2,info);
                        break;*/
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
                        Float aux5 = (Float) this.dataInputStream.readFloat();
                        Float aux6 = (Float) this.dataInputStream.readFloat();
                        String infoToDelete = this.dataInputStream.readUTF();
                        this.matrix.delete(aux5, aux6, this.matrix.get(aux5, aux6));
                        String outDelete = "Dato --> " + this.matrix.get(aux5, aux6);
                        this.dataOutputStream.writeUTF(outDelete);
                        System.out.println(outDelete);
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
                            String pokemon = nearPokemons.get(option - 1).toString();
                            this.dataOutputStream.writeUTF(pokemon);

                            this.matrix.delete( this.matrix.searchColumn(nearPokemons.get(option-1)),this.matrix.searchRow(nearPokemons.get(option-1)),nearPokemons.get(option-1));
                            String out2 = " Dato -> " + this.matrix.get(this.matrix.searchColumn(nearPokemons.get(option-1)), this.matrix.searchRow(nearPokemons.get(option-1))).toString();
                            System.out.println("OUT 2" + out2);
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


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
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons = JsonFileManager.readFile("src/data/pokedex.json");
        System.out.println(pokemons.size());
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
            float aux1 = this.random() * 100;
            float aux2 = this.random() * 100;
            System.out.println("Aux1-->" + aux1 + ',' + "Aux 2--->" + aux2);
            this.matrix.add(aux1, aux2, pokemon);

        }
    }

    public void changeMatrix() {
        MyMatrix<Float, Float, Pokemon> matrix2 = new MyMatrix<>((x, y) -> x.compareTo(y), (x, y) -> x.compareTo(y), new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o2.getId() - o1.getId();
            }
        });
        ArrayList<Pokemon> pokemonsNews = new ArrayList<>();
        pokemons = JsonFileManager.readFile("src/data/pokedex.json");
        System.out.println(pokemons.size());
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
            float aux1 = this.random() * 100;
            float aux2 = this.random() * 100;
            System.out.println("Aux1-->" + aux1 + ',' + "Aux 2--->" + aux2);
            matrix2.add(aux1, aux2, pokemon);

        }
        System.out.println("CAMBIADOOSSSS");
        this.matrix = matrix2;
    }

    public void changeTime() throws InterruptedException {
        Thread.sleep(100);
        this.changeMatrix();
    }


    public float random() {
        return (float) Math.random();
    }
}

package client;

import pokemons.Pokemon;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class ClientThread implements Runnable{
    private Socket socket;
    private Scanner scanner;
    private DataInputStream inputStream;
    private DataOutputStream output;
    private String myId;
    private ArrayList <Pokemon> pokemonsCaptured;
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(System.in);
        this.pokemonsCaptured = new ArrayList<>();
    }

    /**
     * Inicialización hilo cliente
     */
    @Override
    public void run() {

        try {

            System.out.println("------ Escriba su ID ------");
            this.inputStream = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
            String message = scanner.nextLine();
            System.out.println(message + " <--- Su ID");
            this.output.writeUTF(message);
            System.out.println("Escriba su posicion inicial en formato latitud - longitud");
            Float lat = Float.parseFloat(scanner.next());
            Float longitud = Float.parseFloat(scanner.next());
            this.output.writeFloat(lat);
            this.output.writeFloat(longitud);


            //Leo el menu
            int choice = 0;
            String menu = this.inputStream.readUTF();
            do {
                Thread.sleep(1000);
                System.out.println(menu);
                System.out.println("Ingrese lo que desea hacer --> ");
                choice = this.scanner.nextInt();
                this.output.writeInt(choice);
                // Envio la opcion
                switch (choice){
                    /**case 1:
                        System.out.println("--- Esogiste la opcion 1 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la info");
                        String info = this.scanner.next();

                        this.output.writeUTF(info);
                        break;*/
                    case 1:
                        System.out.println("--- Escogiste la opcion 1 ---\nIngrese la columna especificada");
                        String float1 = this.scanner.next();
                        this.output.writeFloat(Float.parseFloat(float1));
                        System.out.println("Ingrese la fila ");
                        String float2 = this.scanner.next();
                        this.output.writeFloat(Float.parseFloat(float2));
                        String received = this.inputStream.readUTF();
                        System.out.println(received);
                        break;
                    case 2:
                        System.out.println("--- Escogiste la opcion 2 ---\n");
                        String income = this.inputStream.readUTF();
                        System.out.println(income);

                        break;

                    /**case 2:
                        System.out.println("--- Esogiste la opcion 2 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la nueva info de esa celda");
                        String newInfo = this.scanner.next();
                        this.output.writeUTF(newInfo);
                        String received2 = this.inputStream.readUTF();
                        System.out.println(received2);
                        break;
                    case 3:
                        System.out.println("--- Esogiste la opcion 3 ---\nIngrese la columna especificada");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese la fila ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        String receivedDeleted  = this.inputStream.readUTF();
                        System.out.println(receivedDeleted);
                     */
                    case 4:
                        System.out.println("--- Escogiste la opcion 4 ---\nVamos a ver si puedes atrapar un Pokemon");
                        String receivePokemonToCapture = this.inputStream.readUTF();
                        System.out.println(receivePokemonToCapture);
                        if (receivePokemonToCapture.equals("No hay pokemons para atrapar")){
                            break;
                        }else {
                            int option = scanner.nextInt();
                            this.output.writeInt(option);
                            String pokemonCaptured = this.inputStream.readUTF();

                            System.out.println("Has atrapado al pokemon " + pokemonCaptured);
                            String poke[] = pokemonCaptured.split(",");
                            this.pokemonsCaptured.add(new Pokemon(Integer.parseInt(poke[0]),poke[1]));
                        }
                        break;


                    case 5:
                        if (this.pokemonsCaptured.isEmpty()){
                            System.out.println("No has atrapado a ningun pokemon hasta ahora :(");
                            break;
                        }else {
                            for (Pokemon pokemon : this.pokemonsCaptured) {
                                System.out.println("ID --> " + pokemon.getId() + ", Name --> " + pokemon.getName());
                            }
                        }
                        break;
                    case 6:
                        System.out.println("--- Escogiste la opcion 6 ---\nCambiar posicion del cliente");
                        System.out.println("Ingrese latitud ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        System.out.println("Ingrese longitud  ");
                        this.output.writeFloat(this.scanner.nextFloat());
                        String aux = this.inputStream.readUTF();
                        System.out.println(aux);
                        break;



                }



            }while (choice!=7);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendId(String id) throws IOException {
        System.out.println("Dentro del metodo SendId " + id);
        this.output.writeUTF(id);
    }

    public float manageIncomToFloat(String incom){
        return Float.parseFloat(incom);
    }
}

package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import pokemons.Pokemon;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class JsonFileManager {
	/**
	 * Metodo que lee un archivo json con JsonArray o JsonObject
	 * @param filePath
	 * @return ArrayList<Pokemon> lista de los datos solicitados de cada pokemon
	 */
	public static ArrayList<Pokemon> readFile(String filePath) {
		ArrayList<Pokemon> list = new ArrayList<>();
		JsonArray jsonArray;
		try {
		jsonArray = (JsonArray) Jsoner.deserialize(new FileReader(filePath));
			for (Object object : jsonArray) {
				JsonObject jsonObject = (JsonObject) object;
				int id = Integer.parseInt(jsonObject.getString("id"));
				JsonObject jsonName = (JsonObject)jsonObject.get("name");
				String name = String.valueOf(jsonName.get("english"));
				JsonArray jsonType =  (JsonArray) jsonObject.get("type");
				String type = String.valueOf(jsonType.get(0));
				JsonObject jsonBase = (JsonObject)jsonObject.get("base");
				int attack = Integer.parseInt(String.valueOf(jsonBase.get("Attack")));
				list.add(new Pokemon(id,name,type,attack));
			}
		}catch(DeserializationException |IOException e) {
			e.printStackTrace();
		}
		
		return list;

	}

//	public static void main(String[] args) {
//		ArrayList<Pokemon> list = JsonFileManager.readFile( "src/data/pokedex.json" );
//		for (Pokemon objects : list) {
//			System.out.println(objects.toString());
//		}
//	}


}

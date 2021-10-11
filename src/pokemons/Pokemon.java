package pokemons;
/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class Pokemon {
	private int id;
	private String name;
	private String type;
	private int attack;

	/**
	 * Constructor del pokemon
	 * @param id
	 * @param name
	 * @param type
	 * @param attack
	 */
	public Pokemon(int id, String name, String type, int attack) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.attack = attack;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type=" + type + ", attack=" + attack + "]";
	}
}

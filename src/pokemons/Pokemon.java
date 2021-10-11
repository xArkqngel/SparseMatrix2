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


	/**
	 * Constructor del pokemon
	 * @param id
	 * @param name
	 *
	 */
	public Pokemon(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name+"]";
	}

}

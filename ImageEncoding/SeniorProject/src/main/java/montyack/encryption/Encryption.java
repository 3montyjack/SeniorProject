package montyack.encryption;


/* Use this class as a parent to further implement the other items for the encryption */
public class Encryption {

    String name;

    public Encryption(String name) {
        this.name = name;
    }

	public String getName() {
		return this.name;
	}
}
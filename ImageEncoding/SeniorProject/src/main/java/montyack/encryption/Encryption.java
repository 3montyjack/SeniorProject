package montyack.encryption;

import java.awt.image.BufferedImage;

/* Use this class as a parent to further implement the other items for the encryption */
public abstract class Encryption {

    String name;

    public Encryption(String name) {
        this.name = name;
    }

	public String getName() {
		return this.name;
    }
    
    public abstract BufferedImage finalImage(BufferedImage inputImage, String messageToEncrypt);
    protected abstract int[] setPixels(BufferedImage tempImage, int xStart, int yStart, char currentChar);
}
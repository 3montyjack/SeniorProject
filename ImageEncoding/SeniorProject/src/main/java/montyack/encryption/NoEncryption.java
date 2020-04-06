package montyack.encryption;

import java.awt.image.BufferedImage;

public class NoEncryption extends Encryption {

    public NoEncryption() {
        super("No Encryption");
    }

    @Override
    public BufferedImage finalImage(BufferedImage inputImage, String messageToEncrypt) {
        return inputImage;
    }

    @Override
    protected int[] setPixels(BufferedImage tempImage, int xStart, int yStart, char currentChar) {
        return null;
    }

}
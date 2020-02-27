package montyack.encryption;

import java.awt.image.BufferedImage;

public class LeastSignificantBit extends Encryption {
    public LeastSignificantBit() {
        super("Least Significant Bit");
        // TODO Auto-generated constructor stub
    }

    @Override
    public BufferedImage finalImage(BufferedImage inputImage, String inputMessage) {
        int[] imageColorArray = new int[inputImage.getWidth() * inputImage.getHeight()];
        inputImage.getRGB(0, 0, inputImage.getWidth(), inputImage.getHeight(), imageColorArray, 0, 0);
        int x = 0;
        for(int i = 0; i < inputMessage.length(); i++) {
            char tempItem = ((CharSequence) inputImage).charAt(i);
            for (int j = 0; j < 8; j++) {

                imageColorArray[x] = imageColorArray[x] & 0x01111111 | ((tempItem << j) & 0x10000000);
                if (x >= inputImage.getWidth() * inputImage.getHeight()) {
                    break;
                }   
                x++;
            }
        }
        inputImage.setRGB(0, 0, inputImage.getWidth(), inputImage.getHeight(), imageColorArray, 0, 0);
        return inputImage;
    }
}

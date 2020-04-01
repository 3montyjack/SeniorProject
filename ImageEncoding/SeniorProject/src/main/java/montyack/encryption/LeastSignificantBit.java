package montyack.encryption;

import java.awt.image.BufferedImage;

public class LeastSignificantBit extends Encryption {
    public LeastSignificantBit() {
        super("Least Significant Bit");
        // TODO Auto-generated constructor stub
    }

    @Override
    public BufferedImage finalImage(BufferedImage inputImage, String inputMessage) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < inputImage.getWidth()/4 * inputImage.getHeight(); i++) {
            
            int[] temp = setPixels(inputImage, x, y, inputMessage.charAt(i));
            x = temp[0];
            y = temp[1];
            if (i >= inputMessage.length()-1) {
                break;
            }
        }
        return inputImage;
    }

    protected int[] setPixels(BufferedImage tempImage, int xStart, int yStart, char currentChar) {
        int[] endingCords = {xStart, yStart};
        int currentCharInt = Character.getNumericValue(currentChar);
        for (int j = 0; j < 6; j++) {

            //TODO this might be wrong
            int rgb =  (currentCharInt & 0x4) << 14 | (currentCharInt & 0x2) << 7 | (currentCharInt & 0x1); 
            // (currentCharInt & 0x8) << 19 |
            int originalPixel = tempImage.getRGB(endingCords[0], endingCords[1]);
            rgb = rgb | (originalPixel & 0xFFFEFEFE);
            currentCharInt = currentCharInt >> 3;
            tempImage.setRGB(endingCords[0], endingCords[1], rgb);

            endingCords[0]++;
            if (endingCords[0] >= tempImage.getWidth()) {
                endingCords[0] = 0;
                endingCords[1]++;
            }
        }
        return endingCords;
    }

    
}

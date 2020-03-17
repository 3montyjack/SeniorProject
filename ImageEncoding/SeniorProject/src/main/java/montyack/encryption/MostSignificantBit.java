package montyack.encryption;

import java.awt.image.BufferedImage;
/**
 * MostSignifantBit
 */
public class MostSignificantBit extends Encryption {

    public MostSignificantBit() {
        super("Most Signifant Bit");
    }

    public BufferedImage finalImage(BufferedImage inputImage, String inputMessage) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < inputImage.getWidth() / 4 * inputImage.getHeight(); i++) {

            int[] temp = setPixels(inputImage, x, y, inputMessage.charAt(i));
            x = temp[0];
            y = temp[1];
            System.out.println("Here are the x and y vaues" + x + " " + y);
            if (i >= inputMessage.length() - 1) {
                break;
            }
        }
        return inputImage;
    }

    protected int[] setPixels(BufferedImage tempImage, int xStart, int yStart, char currentChar) {
        int[] endingCords = { xStart, yStart };
        int currentCharInt = Character.getNumericValue(currentChar);
        for (int j = 0; j < 6; j++) {
            System.out.println("Here with pixel: " + endingCords[0] + " " + endingCords[1]);

            int rgb = (currentCharInt & 0x4) << 23 | (currentCharInt & 0x2) << 15 | (currentCharInt & 0x1) << 7;
            int originalPixel = tempImage.getRGB(endingCords[0], endingCords[1]);
            System.out.println(rgb + " " + (originalPixel & 0xFF7F7F7F));
            System.out.println(Integer.toHexString(rgb) + " " + Integer.toHexString(originalPixel & 0xFF7F7F7F));
            rgb = rgb | (originalPixel & 0xFF7F7F7F);
            currentCharInt = currentCharInt >> 3;
            System.out.println(Character.getNumericValue(currentChar >> j));
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
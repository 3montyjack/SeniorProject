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
            System.out.println("Here are the x and y vaues" + x + " " + y);
            if (i >= inputMessage.length()-1) {
                break;
            }
        }
        return inputImage;
    }

    private int[] setPixels(BufferedImage tempImage, int xStart, int yStart, char currentChar) {
        int[] endingCords = {xStart, yStart};
        int currentCharInt = Character.getNumericValue(currentChar);
        System.out.println(currentCharInt);
        for (int j = 0; j < 4; j++) {
            System.out.println("Here with pixel: " + endingCords[0] + " " + endingCords[1]);

            int rgb = (currentCharInt & 0x8)>> 12 | (currentCharInt & 0x4) >> 8 | (currentCharInt & 0x2)>> 4 | (currentCharInt & 0x1);
            int originalPixel = tempImage.getRGB(endingCords[0], endingCords[1]);
            rgb = rgb | (originalPixel & 0x7777);
            currentCharInt = currentCharInt >> 4;
            System.out.println(currentCharInt);
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

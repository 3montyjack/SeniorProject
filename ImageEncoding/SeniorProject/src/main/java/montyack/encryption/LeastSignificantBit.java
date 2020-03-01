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
        inputImage.getRGB(0, 0, inputImage.getWidth(), inputImage.getHeight(), imageColorArray, 0, 1);
        int x = 0;
        if (inputMessage.length()%2 == 1) {
            inputMessage = inputMessage + " ";
        }
        for(int i = 0; i < imageColorArray.length; i++) {
            int tempInt = 0;
            imageColorArray[i] = (imageColorArray[i] & 0x7F7F7F);
            for (int j = 0; j < 3; j++) {
                System.out.println("Here with pixel: " + x + " " + j);
                if ((Integer.valueOf((inputMessage.charAt((int)x/3)) >> j) & 0x1) == 1) {
                    tempInt = (tempInt + 0x80);
                }
                tempInt = (tempInt << 8);
                x++;
            }
            tempInt = tempInt >> 8;
            if (x == (inputMessage.length()*3)-1) {
                break;
            }
        }
        inputImage.setRGB(0, 0, inputImage.getWidth(), inputImage.getHeight(), imageColorArray, 0, 1);
        return inputImage;
    }
}

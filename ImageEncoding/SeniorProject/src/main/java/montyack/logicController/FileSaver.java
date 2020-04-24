package montyack.logiccontroller;

import java.lang.Runnable;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import montyack.encryption.Encryption;

public class FileSaver implements Runnable {

    private String fileName;
    private String filePath;
    private String oldFilePath;
    private Encryption encryption;
    private String encryptedText;
    private boolean validation;

    public FileSaver(int fileName, Encryption encryption, String oldFilePath, String encryptedText, boolean validation) {
        this.fileName = fileName + "";
        this.filePath = filePath;
        this.oldFilePath = oldFilePath;
        this.encryption = encryption;
        this.encryptedText = encryptedText;
        this.validation = validation;
    }

    @Override
    public void run() {
        int width = 250;
        int height = width;
        File tempFile = new File(oldFilePath);
        try {
            BufferedImage image = ImageIO.read(tempFile);
            Image tempimage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphic = newImage.createGraphics();
            graphic.drawImage(tempimage, 0, 0, null);
            newImage = encryption.finalImage(newImage, encryptedText);
            FileHandeling.savePhotoToDirectory(newImage, encryption.getName(), (Integer.parseInt(fileName) + 1) + ".png", validation);
        } catch (Exception e) {
            System.out.println("Error with photo" + oldFilePath);
            tempFile.delete();
        }

    }

}
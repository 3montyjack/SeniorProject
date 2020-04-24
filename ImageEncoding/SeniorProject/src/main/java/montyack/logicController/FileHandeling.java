package montyack.logicController;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

import montyack.encryption.Encryption;

public class FileHandeling {

    static String inputDir = "";
    static String outputDir = "";
    static String trainingDir = "";

    static ArrayList<String> stringsList;


    public FileHandeling() {
        inputDir = "";
        outputDir = "";
        trainingDir = "";
        stringsList = new ArrayList<>();
    }

    public void setInputDir(String inputDir) {
        FileHandeling.inputDir = inputDir;
        
    }

    public void setOutputDir(String outputDir) {
        FileHandeling.outputDir = outputDir;
        
    }

    public void makeDirectories(ArrayList<Encryption> encryptions) {
        File fileTraingDir = new File(outputDir + "\\training");
        fileTraingDir.mkdir();
        File fileValidationDir = new File(outputDir + "\\validation");
        fileValidationDir.mkdir();
        for (Encryption encryption: encryptions) {
            File fileEncryption = new File(outputDir + "\\training\\" + encryption.getName());
            File fileEncryptionValid = new File(outputDir + "\\validation\\" + encryption.getName());
            fileEncryption.mkdir();
            fileEncryptionValid.mkdir();
        }
        
    }

    public boolean generateInputList() {
        stringsList = new ArrayList<>();
        try {
            Stream<Path> walk = Files.walk(Paths.get(inputDir));
            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            System.out.println("Here we ago");
            result.forEach(element -> stringsList.add(element));
            walk.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }
    
    public String getInputDir(){
        return inputDir;
    }

    public String getOutputDir(){
        return outputDir;
    }
    
    public String[] getInputFileContents() {
        
        return (String[])stringsList.toArray();
    }

    public String[] getOutputFileContents() {

        return null;
    }

	public void printList() {  
        for (String temp : stringsList) {
            System.out.println(temp);
        }
    }
    
    public static void savePhotoToDirectory(BufferedImage image, String encryptionType, String fileName, boolean validation) {
        String fileNameL = "validation";
        if (!validation) {
            fileNameL = "training";
        }
        String path = outputDir + "\\" + fileNameL + "\\" + encryptionType + "\\" + fileName;
        
        try {
            ImageIO.write(image, fileName.split("\\.")[1], new File(path));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void encryptImagesRandomly(ArrayList<Encryption> listOfEncryptions, String encryptedText, JProgressBar progressBar) throws IOException {
        pickFiles(listOfEncryptions, 10000, 10000, encryptedText, progressBar);
    }

    public void pickFiles(ArrayList<Encryption> listOfEncryptions, int amountOfTraining, 
            int amountOfValidation, String encryptedText, JProgressBar progressBar) throws IOException {
        makeDirectories(listOfEncryptions);
        int width = 250;
        int height = width;
        progressBar.setVisible(true);
        progressBar.setMaximum(listOfEncryptions.size()*2);
        readAndWriteImageToFile(width, height, listOfEncryptions, amountOfTraining, encryptedText, false, progressBar);
        readAndWriteImageToFile(width, height, listOfEncryptions, amountOfValidation, encryptedText, true, progressBar);
        progressBar.setVisible(false);
    }

    public void readAndWriteImageToFile(int width, int height, ArrayList<Encryption> listOfEncryptions, int amountOf, String encryptedText, boolean validation,
            JProgressBar progressBar) {
        String folderName = validation?"validation":"training";
        for (Encryption encryption : listOfEncryptions) {
            progressBar.setValue(progressBar.getValue()+1);
            for (int i = 0; i < amountOf; i++) {
                Random number = new Random();
                int selectedNumber = number.nextInt(stringsList.size());
                String oldPath = stringsList.remove(selectedNumber);
                FileSaver tempSaver = new FileSaver(i, encryption, oldPath, encryptedText, validation);
                tempSaver.run();

            }
        }
    }

}
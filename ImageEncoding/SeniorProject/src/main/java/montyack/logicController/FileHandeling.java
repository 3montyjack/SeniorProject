package montyack.logiccontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandeling {

    static String inputDir = "";
    static String outputDir = "";
    static String trainingDir = "";
    static String filePathDir = "";

    static ArrayList<String> stringsList;

    static boolean isConfigured = false;

    public FileHandeling() {
        inputDir = "";
        outputDir = "";
        trainingDir = "";
        filePathDir = "";
        stringsList = new ArrayList<>();
        makeDirectories();
    }

    public void setInputDir(String inputDir) {
        FileHandeling.inputDir = inputDir;
        if (outputDir != "") {
            isConfigured = true;
        }
    }

    public void setOutputDir(String outputDir) {
        FileHandeling.outputDir = outputDir;
        if (inputDir != "") {
            isConfigured = true;
        }
    }

    public void makeDirectories() {
        filePathDir = "temp";
        File fileTraingDir = new File(filePathDir + "\\training");
        fileTraingDir.mkdir();
        File fileValidationDir = new File(filePathDir + "\\validation");
        fileValidationDir.mkdir();
    
    }

    public boolean generateList() {
        if (!isConfigured) {
            return false;
        }

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
        
        return null;
    }

    public String[] getOutputFileContents() {

        return null;
    }

	public void printList() {  
        for (String temp : stringsList) {
            System.out.println(temp);
        }
	}

}
package montyack.logiccontroller;


public class FileHandeling {

    static String inputDir = "";
    static String outputDir = "";

    static boolean isConfigured = false;

    public FileHandeling() {
        inputDir = "";
        outputDir = "";
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
        if (outputDir != "") {
            isConfigured = true;
        }
    }
    
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        if (inputDir != "") {
            isConfigured = true;
        }
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

}
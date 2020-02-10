package montyack.logicController;


public class FileHandeling {

    String inputDir;
    String outputDir;

    public FileHandeling() {
        inputDir = "";
        outputDir = "";
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }
    
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
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
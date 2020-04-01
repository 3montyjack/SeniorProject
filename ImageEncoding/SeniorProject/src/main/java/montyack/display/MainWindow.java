package montyack.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.DimensionUIResource;

import montyack.encryption.Encryption;
import montyack.logiccontroller.FileHandeling;

public class MainWindow extends JFrame {

    /**
     * Generated ID
     */
    private static final long serialVersionUID = 3547979074698070258L;

    JTabbedPane mainPane;
    static String fileDirectory;
    FileHandeling files;
    ArrayList<Encryption> encryptions;

    public MainWindow(String name) {
        super(name);
        fileDirectory = "";
        this.setMinimumSize(new DimensionUIResource(500,500));
        mainPane = new JTabbedPane();
        add(mainPane);
        addFileTab();
        files = new FileHandeling();
        encryptions = new ArrayList<Encryption>();
    }

    public void addEncryption(Encryption dataType) {
        encryptions.add(dataType);
        makeTabBasedOnEncryption(dataType);
    }

    private void makeTabBasedOnEncryption(Encryption dataType) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(dataType.getName());
        JButton doImageThings = new JButton("Press Me to make an image");
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GroupLayout(panel));
        panel.add(filler);
        panel.add(doImageThings);
        
        
        doImageThings.setBounds(50, 40, 150, 20);
        mainPane.addTab(dataType.getName(), panel);
        doImageThings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Temporary Testing of the image
                try {
                    String tempPath = "ascii.jpeg";
                    String tempPath2 = "ascii(Final).jpeg";
                    File tempFile2 = new File(tempPath2);
                    File tempFile = new File(tempPath);
                    System.out.println( tempFile.getAbsolutePath());
                    ImageIO.write(dataType.finalImage(ImageIO.read(tempFile), "Hello World"), "png", tempFile2);
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });


    }

    public void displayThings() {
        //Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add content to the window.
        this.add(mainPane);
        
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    private void addFileTab() {
        String tabName = "File Settings";
        JPanel panel = new JPanel(true);
        JLabel label = new JLabel(tabName);
        JFileChooser fileChooser = new JFileChooser();
        JLabel fileDirIn = new JLabel("Please Select a File");
        JLabel fileDirOut = new JLabel("Please Select a File");
        JButton importFileButton = new JButton("Files to Import");
        JButton outputFileButton = new JButton("Files to Output");
        JButton encryptRandom = new JButton("Mass Encrypt Random");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GroupLayout(panel));
        panel.add(label);
        
        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        importFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( e.getActionCommand());
                fileChooser.showDialog(panel, "Pick a File");
                System.out.println(fileChooser.getSelectedFile());
                if (fileChooser.getSelectedFile() != null && fileChooser.getSelectedFile().exists()) {
                    files.setInputDir(fileChooser.getSelectedFile().getAbsolutePath());
                    fileDirIn.setText(files.getInputDir());
                    files.generateInputList();
                }
                
            }
        });
        outputFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showDialog(panel, "Pick a File");
                if (fileChooser.getSelectedFile() != null && fileChooser.getSelectedFile().exists()) {
                    files.setOutputDir(fileChooser.getSelectedFile().getAbsolutePath());
                    fileDirOut.setText(fileChooser.getSelectedFile().getAbsolutePath());
                } 
                
            }
        });

        encryptRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    files.encryptImagesRandomly(encryptions);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        importFileButton.setBounds(50, 20, 150, 20);
        outputFileButton.setBounds(50, 40, 150, 20);
        encryptRandom.setBounds(50, 60, 150, 20);
        fileDirIn.setBounds(50, 120, 500, 20);
        fileDirOut.setBounds(50, 140, 500, 20);
        panel.add(importFileButton);
        panel.add(outputFileButton);
        panel.add(fileChooser);
        panel.add(fileDirIn);
        panel.add(fileDirOut);
        panel.add(encryptRandom);



        mainPane.addTab(tabName, panel);
    }


    public static String getDirectory() {
        return fileDirectory;
    }


}
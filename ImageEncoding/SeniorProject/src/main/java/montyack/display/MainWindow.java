package montyack.display;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Spring;
import javax.swing.SpringLayout;
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
    String encryptedText = "Did you ever hear the tragedy of Darth Plagueis The Wise? I thought not. It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith, so powerful and so wise he could use the Force to influence the midichlorians to create life… He had such a knowledge of the dark side that he could even keep the ones he cared about from dying. The dark side of the Force is a pathway to many abilities some consider to be unnatural. He became so powerful… the only thing he was afraid of was losing his power, which eventually, of course, he did. Unfortunately, he taught his apprentice everything he knew, then his apprentice killed him in his sleep. Ironic. He could save others from death, but not himself.";

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
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel(true);
        JPanel fileSelection = new JPanel(true);
        JFileChooser fileChooser = new JFileChooser();
        JLabel fileDirIn = new JLabel("Please Select a File");
        JLabel fileDirOut = new JLabel("Please Select a File");
        JTextArea ammountPlain = new JTextArea("200");
        JTextArea ammountEncrypted = new JTextArea("200");
        JButton importFileButton = new JButton("Files to Import");
        JButton outputFileButton = new JButton("Files to Output");
        JButton encryptRandom = new JButton("Mass Encrypt Random");
        JProgressBar progressBar = new JProgressBar();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        mainPane.addTab(tabName, panel);
        panel.add(fileSelection, BorderLayout.CENTER);
        panel.setLayout(layout);
        panel.setVisible(true);
        fileSelection.setVisible(true);

        importFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showDialog(panel, "Pick a File");
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
                    files.encryptImagesRandomly(encryptions, encryptedText);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        
        // fileSelection.setLayout(new BoxLayout(fileSelection, BoxLayout.Y_AXIS));
        // fileSelection.setAlignmentX(20);
        // importFileButton.setBounds(50, 20, 150, 20);
        // outputFileButton.setBounds(50, 40, 150, 20);
        // encryptRandom.setBounds(50, 60, 150, 20);
        // fileDirIn.setBounds(50, 120, 500, 20);
        // fileDirOut.setBounds(50, 140, 500, 20);
        // panel.add(importFileButton);
        // panel.add(outputFileButton 
        
        fileSelection.add(importFileButton, BorderLayout.CENTER);
        fileSelection.add(outputFileButton, BorderLayout.CENTER);
        fileSelection.add(ammountPlain, BorderLayout.CENTER);
        fileSelection.add(ammountEncrypted, BorderLayout.CENTER);
        fileSelection.add(fileDirIn, BorderLayout.CENTER);
        fileSelection.add(fileDirOut, BorderLayout.CENTER);
        fileSelection.add(encryptRandom, BorderLayout.CENTER);

        // panel.add(fileChooser);
        importFileButton.setVisible(true);
        
    }


    public static String getDirectory() {
        return fileDirectory;
    }


}
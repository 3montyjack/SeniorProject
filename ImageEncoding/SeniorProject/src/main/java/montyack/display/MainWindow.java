package montyack.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.DimensionUIResource;

import montyack.encryption.Encryption;

public class MainWindow extends JFrame {

    /**
     * Generated ID
     */
    private static final long serialVersionUID = 3547979074698070258L;

    JTabbedPane mainPane;
    static String fileDirectory;

    public MainWindow(String name) {
        super(name);
        fileDirectory = "";
        this.setMinimumSize(new DimensionUIResource(500,500));
        mainPane = new JTabbedPane();
        add(mainPane);
        addFileTab();
    }

    public void addEncryption(Encryption dataType) {
        makeTabBasedOnEncryption(dataType);
    }

    private void makeTabBasedOnEncryption(Encryption dataType) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(dataType.getName());
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GroupLayout(panel));
        panel.add(filler);
        mainPane.addTab(dataType.getName(), panel);
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
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GroupLayout(panel));
        panel.add(label);
        

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        importFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                fileChooser.showDialog(panel, "Pick a File");
                if (fileChooser.getSelectedFile().exists()) {
                    fileDirIn.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
            }
        });
        outputFileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                fileChooser.showDialog(panel, "Pick a File");
                if (fileChooser.getSelectedFile().exists()) {
                    fileDirOut.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
            }
        });
        importFileButton.setBounds(50, 20, 150, 20);
        outputFileButton.setBounds(50, 40, 150, 20);
        fileDirIn.setBounds(50, 120, 300, 20);
        fileDirOut.setBounds(50, 140, 300, 20);
        panel.add(importFileButton);
        panel.add(outputFileButton);
        panel.add(fileChooser);
        panel.add(fileDirIn);



        mainPane.addTab(tabName, panel);
    }


    public static String getDirectory() {
        return fileDirectory;
    }


}
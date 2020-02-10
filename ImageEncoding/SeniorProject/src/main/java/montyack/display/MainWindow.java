package montyack.display;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import montyack.encryption.Encryption;

public class MainWindow extends JFrame {

    /**
     * Generated ID
     */
    private static final long serialVersionUID = 3547979074698070258L;

    JTabbedPane mainPane;

    public MainWindow(String name) {
        super(name);
        mainPane = new JTabbedPane();
        add(mainPane);
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


}
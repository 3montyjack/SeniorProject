package montyack;

import montyack.display.MainWindow;
import montyack.encryption.Encryption;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MainWindow window = new MainWindow("Main Window");

        window.addEncryption(new Encryption("Test"));
        window.addEncryption(new Encryption("Test"));
        window.addEncryption(new Encryption("Test"));
        window.displayThings();
    }
}

package montyack;

import montyack.display.MainWindow;
import montyack.encryption.LeastSignificantBit;
import montyack.encryption.MostSignificantBit;
import montyack.encryption.NoEncryption;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        MainWindow window = new MainWindow("Main Window");
        window.addEncryption(new LeastSignificantBit());
        window.addEncryption(new MostSignificantBit());
        window.addEncryption(new NoEncryption());
        // window.addEncryption(new LeastSignificantBit());
        window.displayThings();
    }
}

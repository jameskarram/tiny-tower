package ui;

import javax.swing.*;
import java.awt.*;


//Represents Splash Screen showing image
public class SplashScreen {
    private final JWindow window;
    private long startTime;
    private int minimumMilliseconds;

    //EFFECTS: creates a splash screen with an image
    public SplashScreen() {
        window = new JWindow();
        var image = new ImageIcon("/Users/jameskarram/Downloads/IMG_6701.jpg");
        window.getContentPane().add(new JLabel("", image, SwingConstants.CENTER));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((int) ((screenSize.getWidth() - image.getIconWidth()) / 2),
                (int) ((screenSize.getHeight() - image.getIconHeight()) / 2), 175, 175);
    }

    //EFFECTS: makes the splash screen visible
    public void show(int minimumMilliseconds) {
        this.minimumMilliseconds = minimumMilliseconds;
        window.setVisible(true);
        startTime = System.currentTimeMillis();
    }

    //EFFECTS: hides the splash screen
    public void hide() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
    }
}

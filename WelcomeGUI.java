import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;

/**
 * This represents the welcome GUI of the farming game, where player gets asked to choose a game mode.
 */
public class WelcomeGUI extends JFrame{

    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Choose your game mode");

    /**
     * This constructor initializes the contents of the Welcome GUI screen and displays it to the screen.
     */
    public WelcomeGUI () {

        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(welcomeLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        JButton easyBtn = new JButton("easy");
        JButton hardBtn = new JButton("hard");
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.add(easyBtn);
        centerPanel.add(hardBtn);
        frame.add(centerPanel, BorderLayout.CENTER);

        easyBtn.addActionListener(e-> {
            String readFromTxt;
            int count;
            try {
                Scanner sc = new Scanner(new File("easy.txt"));
                readFromTxt = sc.next();
                count = Integer.parseInt(readFromTxt);
                MainView mainView = new MainView(count);
                frame.dispose();
            } catch (Exception exc) {
                MainView mainView = new MainView(0);
                frame.dispose();

            }
        });
        hardBtn.addActionListener(e-> {
            String readFromTxt;
            int count;
            try {
                Scanner sc = new Scanner(new File("hard.txt"));
                readFromTxt = sc.next();
                count = Integer.parseInt(readFromTxt);
                MainView mainView = new MainView(count);
                frame.dispose();
            } catch (Exception exc) {
                MainView mainView = new MainView(0);
                frame.dispose();

            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,300);
        frame.setVisible(true);
        
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class MenuGUI extends JFrame{
    private JPanel topMenuPanel = new JPanel();
    private JPanel centerMenuPanel = new JPanel();
    private JLabel instructionLabel = new JLabel("Choose your game mode");
    private JButton easyButton = new JButton("easy");
    private JButton normalButton = new JButton("normal");
    private JButton hardButton = new JButton("hard");

    public MenuGUI () {

        setLayout(new BorderLayout());
        topMenuPanel.add(instructionLabel);
        centerMenuPanel.add(easyButton);
        centerMenuPanel.add(normalButton);
        centerMenuPanel.add(hardButton);
        add(topMenuPanel, BorderLayout.NORTH);
        add(centerMenuPanel, BorderLayout.CENTER);

        easyButton.addActionListener(ae -> {
            System.out.println("You picked easy mode");
            createFarm("easy.txt");
                
        });
        normalButton.addActionListener(ae -> {
            System.out.println("You picked normal mode");
            createFarm("normal.txt");
            
        });
        hardButton.addActionListener(ae -> {
            System.out.println("You picked hard mode");
            createFarm("hard.txt");
        });
        
        setVisible(true);
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void createFarm(String txt) {
        String readFromText;
        Farm myFarm;

        try {
            Scanner textScanner = new Scanner(new File(txt));
            readFromText = textScanner.next();
            myFarm = new Farm(Integer.parseInt(readFromText));
            
            
        } catch (Exception e) {
            System.out.println("File not found");
            myFarm = new Farm(0);
        }
        new FarmGUI(myFarm);
        dispose();
        
        
    }
}

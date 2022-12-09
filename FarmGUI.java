import javax.swing.*;
import java.awt.*;

public class FarmGUI extends JFrame {
    private JButton[] lotButtons = new JButton[50];
    private int i;

    public FarmGUI (Farm farm) {
        setLayout(new BorderLayout());
        
        //set top 
        JPanel topPanel = new JPanel();
        JLabel statsLabel = new JLabel("stats placeholder");
        topPanel.setLayout(new GridLayout(0,3));
        topPanel.add(statsLabel);
        add(topPanel, BorderLayout.NORTH);

        //set center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(10,5));
        
        for (i = 0; i < 50; i++) {
            lotButtons[i] = new JButton();
            centerPanel.add(lotButtons[i]);
            lotButtons[i].setFocusable(false);

            //FIX COLOR OF BUTTONS
            if (farm.getTile(i).getHasRock()) {
                lotButtons[i].setText("ROCK");
            }

            lotButtons[i].addActionListener(ae -> {
                createActionMenu(farm, i);
            });
        }
        add(centerPanel, BorderLayout.CENTER);

        //set bottom
        JPanel bottomPanel = new JPanel();
        JButton sleepButton = new JButton("Sleep");
        bottomPanel.add(sleepButton);
        add(bottomPanel, BorderLayout.SOUTH);

        sleepButton.addActionListener(e -> {
            //proceed to next day
        });



        setVisible(true); 
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createActionMenu (Farm farm, int index) {
        new ActionMenuGUI(farm, index);
        //add command so this isnt responsive as long as actionMenuGUI is open
        
    }
}

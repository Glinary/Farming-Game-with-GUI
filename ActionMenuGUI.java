import javax.swing.*;
import java.awt.*;

public class ActionMenuGUI extends JFrame{
    private int i;
    JButton[] actionButtons = new JButton[7];

    public ActionMenuGUI (Farm farm, int index) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Choose an action");
        topPanel.add(instructionLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        actionButtons[0] = new JButton("Plow Tile");
        actionButtons[1] = new JButton("Plant Seed");
        actionButtons[2] = new JButton("Use Watering Can");
        actionButtons[3] = new JButton("Use Fertilizer");
        actionButtons[4] = new JButton("Use Pickaxe");
        actionButtons[5] = new JButton("Use Shovel");
        actionButtons[6] = new JButton("Harvest Crop");

        for (i = 0; i < 7; i++) {
            centerPanel.add(actionButtons[i]);
            actionButtons[i].setFocusable(false);
            actionButtons[i].addActionListener(e -> {
                switch (i) {
                    //plow tile
                    case 0:
                        farm.getPlayer().plow(farm.getTile(i));
                        break;
                    //plant seed
                    //watering can
                    //fertilizer
                    //shovel
                    //harvest crop
                }
            });
        }
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true); 
        setSize(200,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

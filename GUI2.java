// import java.awt.FlowLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
// import javax.swing.JTextField;

import javax.swing.JPanel;

// import java.awt.BorderLayout;
import java.awt.Color;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

public class GUI2 {
    
    //public GUI2() {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(1500, 1000);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("MCO2");
        window.setBackground(Color.white);

        JPanel tile1 = new JPanel();
        tile1.setBounds(0, 0, 50, 50);
        tile1.setBackground(Color.orange);
        JPanel tile2 = new JPanel();
        tile2.setBounds(100, 100, 50, 50);
        tile2.setBackground(Color.blue);
        JPanel whiteScreen = new JPanel();
        whiteScreen.setBounds(0, 0, 1550, 800);
        whiteScreen.setBackground(Color.white);

        window.add(tile1);
        window.add(tile2);
        window.add(whiteScreen);

        window.setVisible(true);
    }
}
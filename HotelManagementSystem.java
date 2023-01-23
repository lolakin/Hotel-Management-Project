package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class HotelManagementSystem extends JFrame implements ActionListener{
    ImageIcon my_image;

    public HotelManagementSystem() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon main_image = new ImageIcon(path);
        Image img = main_image.getImage().getScaledInstance(1550, 1050, Image.SCALE_DEFAULT);

        ImageIcon new_main_image = new ImageIcon(img);

        JLabel main_image_label = new  JLabel(new_main_image);

        main_image_label.setBounds(0,  0,  1550,  1050);
        add(main_image_label);

        JLabel heading = new JLabel("Hotel Elite");
        heading.setForeground(new Color(0, 0, 204));

        heading.setBounds(560, 30,  1000, 100);
        heading.setFont(new Font("times new roman", Font.ITALIC, 70));

        main_image_label.add(heading);

        JButton button = new JButton("Continue");
        button.setBounds(50, 50, 150, 50);
        button.setBackground(new Color(66, 34, 130));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("times new roman", Font.PLAIN, 20));

        button.addActionListener(this);
        main_image_label.add(button);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelManagementSystem();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new SIgnUp().setVisible(true);
        this.setVisible(false);

    }

}


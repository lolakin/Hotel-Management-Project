package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;


public class HotelManagementSystem extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JButton button, exit, ok;

    public HotelManagementSystem() {
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
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

        heading.setBounds(580, 30,  1000, 100);
        heading.setFont(new Font("times new roman", Font.ITALIC, 80));

        main_image_label.add(heading);

        exit = new JButton("EXIT");
        exit.setBounds(260, 150, 150, 50);
        exit.setBackground(new Color(66, 34, 130));
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false);
        exit.setToolTipText("Exit Program");
        exit.setFont(new Font("serif", Font.BOLD, 20));
        exit.addActionListener(this);
        main_image_label.add(exit);

        button = new JButton("CONTINUE");
        button.setBounds(260, 250, 150, 50);
        button.setBackground(new Color(66, 34, 130));
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setToolTipText("Move To Sign Up Page");
        button.setFont(new Font("serif", Font.BOLD, 20));
        main_image_label.add(button);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            this.setVisible(false);
            UIManager.put("Button.background", Color.black);
            UIManager.put("Button.foreground", Color.white);
            JOptionPane.showMessageDialog(null, "Exit Successful \n" +
                    "Do come again!");
        }
        else if (e.getSource() == button){
            new SIgnUp().setVisible(true);
            this.setVisible(false);
        }


    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Window Opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window Closing");
        dispose();
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.white);
        JOptionPane.showMessageDialog(null, "Exit Successful \n" +
                "Do come again!");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window Closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Window Minimized");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Window Maximized");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Window Activated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Window Deactivated");
    }
}


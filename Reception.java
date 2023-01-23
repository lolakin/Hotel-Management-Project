package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Reception extends JFrame implements ActionListener  {

    ImageIcon my_image;
    JButton new_customer, check_out, search, pickup_service, restaurant;
    public Reception() {

        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(370, 175, 800, 520);

        Color red = new Color(255, 158, 158);
        Color orange = new Color(255, 205, 158);
//		Color yellow = new Color(255, 255, 158);
//		Color green = new Color(183, 255, 158);
        Color blue = new Color(158, 177, 255);
        Color purple = new Color(221, 160, 221);
//		Color violet = new Color(180, 150, 255);

        JLabel heading = new JLabel("RECEPTION");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(290, 15, 250, 35);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\reception.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon employee_image = new ImageIcon(path);

        JLabel employee_image_label = new  JLabel(employee_image);
        employee_image_label.setBounds(305, 110, 425, 285);
        add(employee_image_label);

        new_customer = new JButton("New Customer Form");
        new_customer.setFocusable(false);
        new_customer.setBackground(red);
        new_customer.setForeground(Color.BLACK);
        new_customer.setFont(new Font("serif", Font.BOLD, 20));
        new_customer.addActionListener(this);
        new_customer.setBounds(30, 115, 225, 30);
        add(new_customer);

        search = new JButton("Search Room");
        search.setFocusable(false);
        search.setBackground(orange);
        search.setForeground(Color.BLACK);
        search.setFont(new Font("serif", Font.BOLD, 20));
        search.addActionListener(this);
        search.setBounds(30, 180, 225, 30);
        add(search);

        check_out = new JButton("Check Out");
        check_out.setFocusable(false);
        check_out.setBackground(blue);
        check_out.setForeground(Color.BLACK);
        check_out.setFont(new Font("serif", Font.BOLD, 20));
        check_out.addActionListener(this);
        check_out.setBounds(30, 245, 225, 30);
        add(check_out);

        pickup_service = new JButton("Pickup Service");
        pickup_service.setFocusable(false);
        pickup_service.setBackground(purple);
        pickup_service.setForeground(Color.BLACK);
        pickup_service.setFont(new Font("serif", Font.BOLD, 20));
        pickup_service.addActionListener(this);
        pickup_service.setBounds(30, 310, 225, 30);
        add(pickup_service);

        restaurant = new JButton("Restaurant");
        restaurant.setFocusable(false);
        restaurant.setBackground(purple);
        restaurant.setForeground(Color.BLACK);
        restaurant.setFont(new Font("serif", Font.BOLD, 20));
        restaurant.addActionListener(this);
        restaurant.setBounds(30, 375, 225, 30);
        add(restaurant);


        getContentPane().setBackground(new Color(32, 32, 32));
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Reception();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == new_customer) {
            new NewCustomer();
            this.setVisible(false);
        }
        else if(e.getSource() == search) {
            new SearchRoom();
            this.setVisible(false);
        }
        else if(e.getSource() == check_out) {
            new CheckOut();
            this.setVisible(false);
        }
        else if(e.getSource() == pickup_service) {
            new PickUpService();
            this.setVisible(false);
        }
        else if(e.getSource() == restaurant) {
            new Restaurant();
            this.setVisible(false);
        }
    }

}

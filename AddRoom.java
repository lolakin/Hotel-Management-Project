package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class AddRoom extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JTextField roomF,priceF;
    ButtonGroup availabilityG, cleaning_statusG, bed_typeG;
    JButton submit;
    Connection conn;

    public AddRoom() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(375, 240, 800, 375);

        JLabel heading = new JLabel("ADD ROOMS");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(270, 7, 250, 35);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\room.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon employee_image = new ImageIcon(path);
        Image img = employee_image.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);

        ImageIcon new_employee_image = new ImageIcon(img);

        JLabel employee_image_label = new  JLabel(new_employee_image);

        employee_image_label.setBounds(430,  60,  300,  200);
        add(employee_image_label);

        JLabel roomL = new JLabel("Room No: ");
        roomL.setForeground(Color.WHITE);
        roomL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roomL.setBounds(50, 70, 100, 20);
        add(roomL);

        roomF = new JTextField();
        roomF.setBounds(175,  70,  50,  20);
        add(roomF);

        JLabel availabilityL = new JLabel("Availability: ");
        availabilityL.setForeground(Color.WHITE);
        availabilityL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        availabilityL.setBounds(50, 110, 100, 20);
        add(availabilityL);

        JRadioButton available = new JRadioButton("Available");
        available.setActionCommand("available");
        available.setFont(new Font("Tahoma", Font.PLAIN, 14));
        available.setBackground(new Color(32, 32, 32));
        available.setForeground(Color.WHITE);
        available.setBounds(175, 110, 80, 20);
        add(available);


        JRadioButton occupied = new JRadioButton("Occupied");
        occupied.setActionCommand("occupied");
        occupied.setFont(new Font("Tahoma", Font.PLAIN, 14));
        occupied.setBackground(new Color(32, 32, 32));
        occupied.setForeground(Color.WHITE);
        occupied.setBounds(275, 110, 100, 20);
        add(occupied);

        availabilityG = new ButtonGroup();
        availabilityG.add(available);
        availabilityG.add(occupied);

        JLabel cleaningL = new JLabel("Cleaning Status: ");
        cleaningL.setForeground(Color.WHITE);
        cleaningL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cleaningL.setBounds(50, 150, 150, 20);
        add(cleaningL);

        JRadioButton cleaned = new JRadioButton("Cleaned");
        cleaned.setActionCommand("cleaned");
        cleaned.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cleaned.setBackground(new Color(32, 32, 32));
        cleaned.setForeground(Color.WHITE);
        cleaned.setBounds(175, 150, 100, 20);
        add(cleaned);


        JRadioButton dirty = new JRadioButton("Occupied");
        dirty.setActionCommand("occupied");
        dirty.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dirty.setBackground(new Color(32, 32, 32));
        dirty.setForeground(Color.WHITE);
        dirty.setBounds(275, 150, 100, 20);
        add(dirty);

        cleaning_statusG = new ButtonGroup();
        cleaning_statusG.add(cleaned);
        cleaning_statusG.add(dirty);

        JLabel bedL = new JLabel("Bed Type: ");
        bedL.setForeground(Color.WHITE);
        bedL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        bedL.setBounds(50, 190, 150, 20);
        add(bedL);

        JRadioButton single_bed = new JRadioButton("Single Bed");
        single_bed.setActionCommand("single");
        single_bed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        single_bed.setBackground(new Color(32, 32, 32));
        single_bed.setForeground(Color.WHITE);
        single_bed.setBounds(175, 190, 100, 20);
        add(single_bed);


        JRadioButton double_bed = new JRadioButton("Double Bed");
        double_bed.setActionCommand("double");
        double_bed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        double_bed.setBackground(new Color(32, 32, 32));
        double_bed.setForeground(Color.WHITE);
        double_bed.setBounds(275, 190, 100, 20);
        add(double_bed);

        bed_typeG = new ButtonGroup();
        bed_typeG.add(single_bed);
        bed_typeG.add(double_bed);

        JLabel priceL = new JLabel("Price: ");
        priceL.setForeground(Color.WHITE);
        priceL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        priceL.setBounds(50, 230, 100, 20);
        add(priceL);

        priceF = new JTextField();
        priceF.setBounds(175,  230,  50,  20);
        add(priceF);

        submit = new JButton("SUBMIT");
        submit.setFocusable(false);
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(315, 280, 115, 30);
        add(submit);

        getContentPane().setBackground(new Color(32, 32, 32));setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new AddRoom();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                String room_no = roomF.getText();
                String availability = availabilityG.getSelection().getActionCommand();
                String cleaning_Status = cleaning_statusG.getSelection().getActionCommand();
                String bed_type = bed_typeG.getSelection().getActionCommand();
                String price = priceF.getText();

                String query1 = "SELECT * FROM room WHERE room_no = '" + room_no + "'";
                String query = "INSERT INTO room VALUES ('" + room_no + "', " + "'" + availability + "', " +
                        "'" + cleaning_Status + "', " + "'" + bed_type + "', " + "'" + price + "');";


                if(!(room_no.isBlank() || availability.isBlank() || cleaning_Status.isBlank() || bed_type.isBlank() || price.isBlank())) {
                    ResultSet res = conn.createStatement().executeQuery(query1);
                    if (res.next()) {
                        JOptionPane.showMessageDialog( null, "Room already exists");
                    }
                    else {
                        conn.createStatement().execute(query);

                        String message = "New Room " + room_no + " Added !!!";
                        JOptionPane.showMessageDialog(null, message);
//						this.setVisible(false);
                    }
                }




            }

            catch(Exception ae) {
                JOptionPane.showMessageDialog(null, "Fill in the fields!!!");
//				System.out.println(ae);
            }

        }
    }

    public void loadSql() {
        String uname = "root", password = "02020319";
        String url = "jdbc:mysql://localhost:3306/master";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, password);

        }
        catch (Exception ae) {
            System.out.println(ae);
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
        new Dashboard();
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

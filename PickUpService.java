package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.text.Document;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class PickUpService extends JFrame implements ActionListener {

    Connection conn;
    ImageIcon my_image;
    JButton submit, back;
    JComboBox cars;
    JTextField addressF, timeF;
    private boolean clicked = false;

    public PickUpService() {
        loadSql();
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(460, 100, 600, 675);

        JLabel heading = new JLabel("PICKUP SERVICE");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(125,  5, 500, 50);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\pickup.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon checkout_image = new ImageIcon(path);
        Image img = checkout_image.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);

        ImageIcon new_checkout_image = new ImageIcon(img);

        JLabel checkout_image_label = new  JLabel(new_checkout_image);

        checkout_image_label.setBounds(150,  70,  300,  300);
        add(checkout_image_label);


        JLabel car = new JLabel("CAR");
        car.setForeground(Color.WHITE);
        car.setBounds(225, 400, 150, 20);
        car.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(car);

        ArrayList <String> cars_list = new ArrayList<>();
        try {
            String query = "SELECT * FROM car";
            ResultSet result = conn.createStatement().executeQuery(query);
            while(result.next()) {
                cars_list.add(result.getString("model"));
            }
        }
        catch(Exception e) {
            System.out.println(e);

        }

        String[] available_cars = cars_list.toArray(new String[cars_list.size()]);
        cars = new JComboBox(available_cars);
        String room_no = (String) cars.getSelectedItem();
        cars.setBackground(Color.WHITE);
        cars.setBounds(275, 400, 100, 20);
        add(cars);

        JLabel addressL = new JLabel("Address: ");
        addressL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addressL.setForeground(Color.WHITE);
        addressL.setBounds(195, 440, 150, 20);
        add(addressL);

        addressF = new JTextField();
        addressF.setText("address...");
        addressF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    addressF.setText("");
                }
            }
        });
        addressF.setBounds(275,  440,  150,  20);
        add(addressF);

        JLabel timeL = new JLabel("Time: ");
        timeL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        timeL.setForeground(Color.WHITE);
        timeL.setBounds(215, 480, 150, 20);
        add(timeL);

        timeF = new JTextField();
        timeF.setText("hh:mm");
        timeF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    timeF.setText("");
                }
            }
        });

        timeF.setBounds(275,  480,  100,  20);
        add(timeF);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setFocusable(false);
        submit.setBounds(250, 525, 115, 30);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(250, 575, 115, 30);
        add(back);


        getContentPane().setBackground(new Color(32, 32, 32));

        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PickUpService();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String car = (String)cars.getSelectedItem();
            String address = addressF.getText();
            String time = timeF.getText();

            try {
                String query = "INSERT INTO pickup VALUES ('" + car +"', " + "'" + address + "', " + "'" + time + "')";
                conn.createStatement().execute(query);
                conn.createStatement().executeUpdate("DELETE FROM pickup WHERE time = 'hh:mm'");
                String message = "New pickup added !!!";
                JOptionPane.showMessageDialog(null, message);
                this.setVisible(false);

            }

            catch(Exception ae) {
                System.out.println(ae);
            }
        }

        else if(e.getSource() == back) {
            this.setVisible(false);
            new Reception().setVisible(true);
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


}

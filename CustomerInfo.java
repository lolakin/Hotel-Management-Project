package HotelManagementJavaProject;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.util.Random;

import net.proteanit.sql.DbUtils;

public class CustomerInfo extends JFrame implements ActionListener, WindowListener {

    ImageIcon my_image;
    JButton check, back;
    JTable table;
    JCheckBox foreigner;
    Connection conn;

    public CustomerInfo() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(160, 65, 1250, 700);

        JLabel heading = new JLabel("CUSTOMER INFO");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(450, 15, 350, 35);
        add(heading);

        foreigner = new JCheckBox("only foreigner");
        foreigner.setBackground(new Color(32, 32, 32, 32));
        foreigner.setForeground(Color.WHITE);
        foreigner.setFont(new Font("arial", Font.PLAIN, 15));
        foreigner.setBounds(550,  75,  125,  20);
        add(foreigner);

        JLabel name, id, gender, phone, country, room, deposit, check_status;

        name = new JLabel("NAME");
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        name.setBounds(50,  115,  70,  30);
        add(name);

        phone = new JLabel("PHONE NO");
        phone.setFont(new Font("Tahoma", Font.BOLD, 15));
        phone.setForeground(Color.WHITE);
        phone.setBounds(180,  115,  100,  30);
        add(phone);

        gender = new JLabel("GENDER");
        gender.setFont(new Font("Tahoma", Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        gender.setBounds(360,  115,  70,  30);
        add(gender);


        id = new JLabel("ID");
        id.setFont(new Font("Tahoma", Font.BOLD, 15));
        id.setForeground(Color.WHITE);
        id.setBounds(525,  115,  70,  30);
        add(id);

        country = new JLabel("COUNTRY");
        country.setFont(new Font("Tahoma", Font.BOLD, 15));
        country.setForeground(Color.WHITE);
        country.setBounds(650,  115,  100,  30);
        add(country);

        room = new JLabel("ROOM NO");
        room.setFont(new Font("Tahoma", Font.BOLD, 15));
        room.setForeground(Color.WHITE);
        room.setBounds(815,  115,  100,  30);
        add(room);

        deposit = new JLabel("DEPOSIT");
        deposit.setFont(new Font("Tahoma", Font.BOLD, 15));
        deposit.setForeground(Color.WHITE);
        deposit.setBounds(975,  115,  70,  30);
        add(deposit);

        check_status = new JLabel("CHECK STATUS");
        check_status.setFont(new Font("Tahoma", Font.BOLD, 15));
        check_status.setForeground(Color.WHITE);
        check_status.setBounds(1100,  115,  125,  30);
        add(check_status);

        table = new JTable();
        table.setBackground(new Color(32, 32, 32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0, 160, 1250, 400);
        add(table);

        check = new JButton("CHECK");
        check.setForeground(Color.WHITE);
        check.setBackground(new Color(66, 34, 130));
        check.setFont(new Font("times new roman", Font.PLAIN, 20));
        check.addActionListener(this);
        back.setToolTipText("Check customer info...");
        check.setBounds(475, 600, 115, 30);
        check.setFocusable(false);
        add(check);


        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(625, 600, 100, 30);
        back.setFocusable(false);
        add(back);

        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new CustomerInfo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== check) {
            try {

                if(foreigner.isSelected()) {
                    String query = "SELECT * FROM customer WHERE country != 'Nigeria'";
                    ResultSet result = conn.createStatement().executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(result));
                }
                else {
                    String query = "SELECT * FROM customer";
                    ResultSet result = conn.createStatement().executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(result));
                }
            }
            catch(Exception ae) {
                JOptionPane.showMessageDialog(null, "Error Occurred." +
                        " Will be resolved in the next update." +
                        " Thanks.");
                System.out.println(ae);
            }
        }

        else if (e.getSource()== back) {
            this.setVisible(false);
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
            JOptionPane.showMessageDialog(null, "Error Occurred." +
                    " Will be resolved in the next update." +
                    " Thanks.");
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

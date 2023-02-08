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

public class FeedbackInfo extends JFrame implements ActionListener, WindowListener {

    ImageIcon my_image;
    JButton check, back;
    JTable table;
    JCheckBox foreigner;
    Connection conn;

    public FeedbackInfo() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(160, 65, 1250, 700);
//        setBounds(425, 85, 700, 700);
        JLabel heading = new JLabel("FEEDBACK INFO");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(450, 15, 350, 35);
        add(heading);


        JLabel name, email, agegr, rate, feedb;

        name = new JLabel("NAME");
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        name.setBounds(70,  85,  70,  30);
        add(name);

        email = new JLabel("EMAIL");
        email.setFont(new Font("Tahoma", Font.BOLD, 15));
        email.setForeground(Color.WHITE);
        email.setBounds(340,  85,  100,  30);
        add(email);

        agegr = new JLabel("AGE GROUP");
        agegr.setFont(new Font("Tahoma", Font.BOLD, 15));
        agegr.setForeground(Color.WHITE);
        agegr.setBounds(550,  85,  130,  30);
        add(agegr);


        rate = new JLabel("RATING");
        rate.setFont(new Font("Tahoma", Font.BOLD, 15));
        rate.setForeground(Color.WHITE);
        rate.setBounds(800,  85,  70,  30);
        add(rate);

        feedb = new JLabel("FEEDBACK");
        feedb.setFont(new Font("Tahoma", Font.BOLD, 15));
        feedb.setForeground(Color.WHITE);
        feedb.setBounds(1000,  85,  100,  30);
        add(feedb);



        table = new JTable();
        table.setBackground(new Color(32, 32, 32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(10, 200, 1250, 400);
        add(table);

        check = new JButton("CHECK");
        check.setForeground(Color.WHITE);
        check.setBackground(new Color(66, 34, 130));
        check.setFont(new Font("times new roman", Font.PLAIN, 20));
        check.addActionListener(this);
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
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new FeedbackInfo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check){
            try {
                String query = "SELECT * FROM feedback";
                ResultSet result = conn.createStatement().executeQuery(query);
                table.setModel (DbUtils.resultSetToTableModel(result));
            }
            catch(Exception ae) {
                System.out.println(ae);
            }
        }

        else if (e.getSource()== back) {
            this.setVisible(false);
            new Dashboard();
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

package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;

public class PickUpInfo extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JButton check, back;
    JTable table;
    Connection conn;

    public PickUpInfo() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(425, 85, 700, 700);

        JLabel heading = new JLabel("PICKUP INFO");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(215,  15, 350, 35);
        add(heading);

        JLabel car, address, time;

        car = new JLabel("CAR");
        car.setForeground(Color.WHITE);
        car.setBounds(90, 115, 70, 30);
        car.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(car);

        address = new JLabel("ADDRESS");
        address.setForeground(Color.WHITE);
        address.setBounds(300, 115, 100, 30);
        address.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(address);

        time = new JLabel("TIME");
        time.setForeground(Color.WHITE);
        time.setBounds(550, 115, 70, 30);
        time.setFont(new Font("Tahoma", Font.BOLD, 15));
//		time.addKeyListener(null);
        add(time);

        table = new JTable();
        table.setBackground(new Color(32, 32, 32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0, 160, 700, 400);
        add(table);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(300, 600, 100, 30);
        back.setFocusable(false);
        add(back);

        load();
        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new PickUpInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== back) {
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
            System.out.println(ae);
        }
    }

    public void load() {
        try {
            String query = "SELECT * FROM pickup";
            ResultSet result = conn.createStatement().executeQuery(query);
            table.setModel (DbUtils.resultSetToTableModel(result));
        }
        catch(Exception ae) {
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

package HotelManagementJavaProject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Random;

import net.proteanit.sql.DbUtils;

public class EmployeeInfo extends JFrame implements ActionListener, WindowListener {

    Connection conn;
    JButton back;
    ImageIcon my_image;
    JTable table;
    public EmployeeInfo() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JLabel heading = new JLabel("EMPLOYEE INFO");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(450,  15, 350, 35);
        add(heading);

        JLabel name, age, gender, job, salary, phone, id, email;

        name = new JLabel("NAME");
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        name.setBounds(50,  85, 70, 30);
        add(name);

        age = new JLabel("AGE");
        age.setFont(new Font("Tahoma", Font.BOLD, 15));
        age.setForeground(Color.WHITE);
        age.setBounds(220,  85, 70, 30);
        add(age);

        gender = new JLabel("GENDER");
        gender.setFont(new Font("Tahoma", Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        gender.setBounds(360,  85, 70, 30);
        add(gender);

        job = new JLabel("JOB");
        job.setFont(new Font("Tahoma", Font.BOLD, 15));
        job.setForeground(Color.WHITE);
        job.setBounds(525,  85, 70, 30);
        add(job);

        salary = new JLabel("SALARY");
        salary.setFont(new Font("Tahoma", Font.BOLD, 15));
        salary.setForeground(Color.WHITE);
        salary.setBounds(675,  85, 70, 30);
        add(salary);

        phone = new JLabel("PHONE NO");
        phone.setFont(new Font("Tahoma", Font.BOLD, 15));
        phone.setForeground(Color.WHITE);
        phone.setBounds(815,  85, 100, 30);
        add(phone);

        id = new JLabel("ID");
        id.setFont(new Font("Tahoma", Font.BOLD, 15));
        id.setForeground(Color.WHITE);
        id.setBounds(975,  85, 70, 30);
        add(id);

        email = new JLabel("EMAIL ID");
        email.setFont(new Font("Tahoma", Font.BOLD, 15));
        email.setForeground(Color.WHITE);
        email.setBounds(1125,  85, 70, 30);
        add(email);

        table = new JTable();
        table.setBackground(new Color(32, 32, 32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0, 130, 1300, 400);
        add(table);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(550, 600, 100, 30);
        back.setFocusable(false);
        back.setToolTipText("Move Back");
        add(back);

        load();

        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);

    }

    public static void main(String[] args) {
        new EmployeeInfo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
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
            String query = "SELECT * FROM employee";
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

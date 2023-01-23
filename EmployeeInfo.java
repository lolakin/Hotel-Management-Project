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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;

public class EmployeeInfo extends JFrame implements ActionListener {

    Connection conn;
    JButton back;
    ImageIcon my_image;
    JTable table;
    public EmployeeInfo() {
        loadSql();
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());
        setBounds(150, 100, 1250, 700);

        JLabel heading = new JLabel("EMPLOYEE INFO");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
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
//		table.setBackground(Color.BLUE);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0, 130, 1250, 400);
        add(table);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(550, 600, 100, 30);
        back.setFocusable(false);
        add(back);

        load();

        getContentPane().setBackground(new Color(32, 32, 32));
        setLayout(null);
        setVisible(true);

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

}

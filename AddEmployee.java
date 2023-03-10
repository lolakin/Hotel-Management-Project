package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmployee extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JTextField nameF, genderF, ageF, salaryF, phoneF, IdF, emailF;
    ButtonGroup genderG;
    JComboBox<String> job;
    JButton submit, back;
    Connection conn;
    Statement st;
    JRadioButton male, female, other;

    public AddEmployee() {
        loadSql();

        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);

        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(375, 140, 800, 550);

        JLabel heading = new JLabel("ADD EMPLOYEE");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(250,  10, 370, 35);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\employee.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon employee_image = new ImageIcon(path);
        Image img = employee_image.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);

        ImageIcon new_employee_image = new ImageIcon(img);

        JLabel employee_image_label = new  JLabel(new_employee_image);

        employee_image_label.setBounds(355,  100,  400,  300);
        add(employee_image_label);

        JLabel name = new JLabel("NAME: ");
        name.setForeground(Color.WHITE);
        name.setBounds(50, 70, 60, 20);
        name.setFont(new Font("serif", Font.PLAIN, 15));
        add(name);

        nameF = new JTextField();
        nameF.setBounds(125, 70, 200, 20);
        nameF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    nameF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 )) {
                    nameF.setEditable(true);
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }

            }
        });
        add(nameF);

        JLabel age = new JLabel("AGE: ");
        age.setForeground(Color.WHITE);
        age.setBounds(50, 110, 60, 20);
        age.setFont(new Font("serif", Font.PLAIN, 15));
        add(age);

        ageF = new JTextField();
        ageF.setBounds(125, 110, 200, 20);
        ageF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ageF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    ageF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    ageF.setText("");
                }
            }
        });
        add(ageF);

        JLabel gender = new JLabel("GENDER: ");
        gender.setForeground(Color.WHITE);
        gender.setBounds(50, 150, 80, 20);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(gender);

        male = new JRadioButton("Male");
        male.setActionCommand("male");
        male.setFont(new Font("Tahoma", Font.PLAIN, 14));
        male.setBackground(new Color(32, 32, 32));
        male.setForeground(Color.WHITE);
        male.setBounds(125, 150, 70, 20);
        add(male);

        female = new JRadioButton("Female");
        female.setActionCommand("female");
        female.setFont(new Font("Tahoma", Font.PLAIN, 14));
        female.setBackground(new Color(32, 32, 32));
        female.setForeground(Color.WHITE);
        female.setBounds(125, 180, 80, 20);
        add(female);

        other = new JRadioButton("Other");
        other.setActionCommand("other");
        other.setFont(new Font("Tahoma", Font.PLAIN, 14));
        other.setBackground(new Color(32, 32, 32));
        other.setForeground(Color.WHITE);
        other.setBounds(125, 210, 80, 20);
        add(other);

        genderG = new ButtonGroup();
        genderG.add(male);
        genderG.add(female);
        genderG.add(other);

        JLabel jobL = new JLabel("JOB: ");
        jobL.setForeground(Color.WHITE);
        jobL.setBounds(50, 250, 70, 20);
        jobL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(jobL);

        String[] job_list = {"Restaurant Manager", "Kitchen Staff", "Head Chef", "Room Service",
                "Hotel Porter", "Front Desk Employee", "Waiter/Waitress", "Driver", "Housekeeping"};
        job = new JComboBox<>(job_list);
        job.setBackground(Color.WHITE);
        job.setBounds(125, 250, 200, 20);
        add(job);


        JLabel salary = new JLabel("SALARY: ");
        salary.setForeground(Color.WHITE);
        salary.setBounds(50, 290, 70, 20);
        salary.setFont(new Font("serif", Font.PLAIN, 15));
        add(salary);

        salaryF = new JTextField();
        salaryF.setBounds(125, 290, 200, 20);
        salaryF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    salaryF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    salaryF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    salaryF.setText("");
                }
            }
        });
        add(salaryF);

        JLabel phoneL = new JLabel("Phone: ");
        phoneL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phoneL.setForeground(Color.WHITE);
        phoneL.setBounds(50, 330, 70, 20);
        add(phoneL);

        phoneF = new JTextField();
        phoneF.setBounds(125,  330,  200,  20);
        phoneF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    phoneF.setEditable(true);
                }

                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 ){
                    phoneF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    phoneF.setText("");
                }
            }
        });
        add(phoneF);

        JLabel idL = new JLabel("ID: ");
        idL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idL.setForeground(Color.WHITE);
        idL.setBounds(50, 370, 70, 20);
        add(idL);

        IdF = new JTextField();
        IdF.setBounds(125, 370, 200, 20);
        add(IdF);

        JLabel emailL = new JLabel("EMAIL: ");
        emailL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailL.setForeground(Color.WHITE);
        emailL.setBounds(50, 410, 70, 20);
        add(emailL);

        emailF = new JTextField();
        emailF.setBounds(125,  410,  200,  20);


        emailF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    emailF.setVisible(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 ){
                    emailF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '@' || ke.getKeyChar() == '.' || ke.getKeyCode() == KeyEvent.VK_SHIFT)) {
                    emailF.setEditable(true);
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets, numbers, @ and . !");
                }
            }
        });
        add(emailF);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setToolTipText("Add New Employee...");
        submit.setBounds(325, 455, 115, 30);
        submit.setFocusable(false);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setToolTipText("Back to dashboard...");
        back.setBounds(460, 455, 115, 30);
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
        new AddEmployee();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String name = nameF.getText();
            String age = ageF.getText();
            String phone = phoneF.getText();
            String gender = genderG.getSelection().getActionCommand();
            String jobb = (String) job.getSelectedItem();
            String email = emailF.getText();
            String id = IdF.getText();
            String salary = salaryF.getText();

            Pattern pt = Pattern.compile("(0|\\+2340?)[789][01][0-9]{8}");
            Pattern tt = Pattern.compile("[^0-9@.][a-zA-Z0-9_\\-.]{8,}@[a-z]+(\\.[a-z]+)+$");
            Matcher mt = pt.matcher(phone);
            Matcher mt2 = tt.matcher(email);
            if(mt.matches()){
                if (mt2.matches()){
                    try {
                        String q = "CREATE TABLE IF NOT EXISTS employee(name char(40), age int, gender char(6), job char(30), " +
                                "salary DECIMAL(10, 2), phone varchar(50), id bigint, email varchar(30))";
                        String qq = "SELECT * FROM employee WHERE id = '" + id + "'";
                        String qqq = "SELECT * FROM employee WHERE email = '" + email + "'";
                        String query1 = "SELECT * FROM employee WHERE name = '" + name + "'";
                        String query = "INSERT INTO employee VALUES('" + name + "', " + "'" + age + "', " + "'" + gender + "', " + "'" + jobb+ "', "
                                + "'" + salary + "', " + "'" + phone + "', " + "'" + id + "', " + "'" + email + "')";

                        if(!(name.isBlank() || phone.isBlank() || gender.isBlank() || id.isBlank() || age.isBlank() || email.isBlank() || salary.isBlank() )) {
                            st = conn.createStatement();
                            st.executeUpdate(q);
                            ResultSet res = conn.createStatement().executeQuery(query1);
                            ResultSet rs = conn.createStatement().executeQuery(qq);
                            ResultSet rss = conn.createStatement().executeQuery(qqq);
                            if(res.next()){
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                JOptionPane.showMessageDialog(null, "Name already exists!");
                            }
                            else if(rs.next()){
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                JOptionPane.showMessageDialog(null, "ID already exists!");
                            }
                            else if(rss.next()){
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                JOptionPane.showMessageDialog(null, "Email already exists!");
                            }
                            else {
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                conn.createStatement().execute(query);
                                String message = "New Employee " + name + " Added !!!";
                                JOptionPane.showMessageDialog(null, message);
                                nameF.setText("");
                                emailF.setText("");
                                ageF.setText("");
                                phoneF.setText("");
                                IdF.setText("");
                                salaryF.setText("");
                                male.setSelected(true);
                                female.setSelected(false);
                                other.setSelected(false);
                            }
                        }
                        else {
                            UIManager.put("Button.background", Color.BLACK);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog(null, "Fill in the fields!");
                        }

                    }
                    catch(Exception ae) {
                        UIManager.put("Button.background", Color.BLACK);
                        UIManager.put("Button.foreground", Color.white);
                        JOptionPane.showMessageDialog(null, "Error Occurred." +
                                " Will be resolved in the next update." +
                                " Thanks.");
                        System.out.println(ae);
                    }
                }
                else{
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Email Not Valid!");
                }
            }
            else{
                UIManager.put("Button.background", Color.BLACK);
                UIManager.put("Button.foreground", Color.white);
                JOptionPane.showMessageDialog(null, "Phone Number Not Valid!");
            }
        }
        else if (e.getSource() == back){
            new Dashboard().setVisible(true);
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
            UIManager.put("Button.background", Color.BLACK);
            UIManager.put("Button.foreground", Color.white);
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
package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import GUI101.GUI_2;


public class SIgnUp extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JButton signup, cancel;
    JTextField unfield, emailF;
    JPasswordField psd, psd2;
    JLabel login, question;
    Statement st;
    Pattern p;
    Matcher m;
    Connection conn;
    private boolean clicked = false;
    Font f = new Font("serif", Font.BOLD, 20);

    public SIgnUp() {
        loadSql();
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLUE));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        setResizable(false);
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\login.jpeg";

        // IMAGE PATH AND LABEL
        ImageIcon login_image = new ImageIcon(path);
        Image img = login_image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        ImageIcon new_login_image = new ImageIcon(img);

        JLabel login_image_label = new  JLabel(new_login_image);

        login_image_label.setBounds(690,  50,  200,  200);
        add(login_image_label);


        JLabel userName = new JLabel("Username: ");
        userName.setForeground(Color.WHITE);
        userName.setBounds(660, 300, 150, 30);
        userName.setFont(new Font("serif", Font.PLAIN, 25));
        add(userName);

        JLabel emailL = new JLabel("Email: ");
        emailL.setForeground(Color.WHITE);
        emailL.setBounds(660, 350, 150, 30);
        emailL.setFont(new Font("serif", Font.PLAIN, 25));
        add(emailL);

        JLabel psd_label = new JLabel("Password: ");
        psd_label.setForeground(Color.WHITE);
        psd_label.setBounds(660, 400, 150, 30);
        psd_label.setFont(new Font("serif", Font.PLAIN, 25));
        add(psd_label);

        JLabel psd_label2 = new JLabel("Confirm Password: ");
        psd_label2.setForeground(Color.WHITE);
        psd_label2.setBounds(570, 450, 200, 30);
        psd_label2.setFont(new Font("serif", Font.PLAIN, 25));
        add(psd_label2);


        unfield = new JTextField();
        unfield.setBounds(775, 300, 190, 30);
        unfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    unfield.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '_' ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4)) {
                    unfield.setEditable(true);
                    UIManager.put("Button.background", Color.BLUE);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        unfield.setFont(f);
        add(unfield);


        emailF = new JTextField();
        emailF.setBounds(775, 350, 190, 30);
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
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    emailF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '@' || ke.getKeyChar() == '.')) {
                    emailF.setEditable(true);
                    UIManager.put("Button.background", Color.BLUE);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets, numbers, @ and . !");
                }

            }

        });
        emailF.setFont(f);
        add(emailF);


        psd = new JPasswordField();
        psd.setBounds(775, 400, 190, 30);
        psd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    psd.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    psd.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLUE);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    psd.setText("");
                }
            }
        });
        psd.setFont(f);
        add(psd);

        psd2 = new JPasswordField();
        psd2.setBounds(775, 450, 190, 30);
        psd2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    psd2.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    psd2.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLUE);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    psd2.setText("");
                }
            }
        });
        psd2.setFont(f);
        add(psd2);


        signup = new JButton("SIGN UP");
        signup.setBounds(660, 500, 140, 30);
        signup.setBackground(Color.GREEN);
        signup.setForeground(Color.BLACK);
        signup.setFont(new Font("serif", Font.BOLD, 25));
        signup.setToolTipText("Sign up for hotel elite...");
        signup.setFocusable(false);
        signup.addActionListener(this);
        add(signup);

        cancel = new JButton("CANCEL");
        cancel.setBounds(820, 500, 140, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setToolTipText("Cancel signup...");
        cancel.setFont(new Font("serif", Font.BOLD, 25));
        cancel.addActionListener(this);
        cancel.setFocusable(false);
        add(cancel);

        login = new JLabel("Already Have an Account? "
                + "LOG IN!");
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    setVisible(false);
                    new Login();

                }
            }
        });
        login.setForeground(Color.ORANGE);
        login.setBounds(600, 550, 400, 100);
        login.setFont(new Font("serif", Font.PLAIN, 25));
        add(login);

        getContentPane().setBackground(new Color(11, 9, 10));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new SIgnUp();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            String username = unfield.getText().toUpperCase();
            String Password = psd.getText();
            String Pass = psd2.getText();
            String mail = emailF.getText();

            String q = "CREATE TABLE IF NOT EXISTS login(username varchar(15), " +
                    "email varchar(50), password varchar(8), Pass varchar(15))";

            String query1 = "SELECT * FROM LOGIN WHERE username = '" + username + "' AND email = '" + mail + "'";
            String query = "INSERT INTO LOGIN VALUES ('" + username + "', " + "'" + mail + "', " + "'" + Password +"', " + "'" + Pass +"');";
            Pattern pt = Pattern.compile("[^0-9@.][a-zA-Z0-9_\\-.]{8,}@[a-z]+(\\.[a-z]+)+$");
            Matcher mt = pt.matcher(mail);

            if (mt.matches()){
                try {
                    if (!(username.isBlank() ||mail.isBlank() || Password.isBlank()  || Pass.isBlank())) {
                        st = conn.createStatement();
                        st.executeUpdate(q);
                        ResultSet result = conn.createStatement().executeQuery(query1);
                        if (result.next()) {
                            UIManager.put("Button.background", Color.BLUE);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog( null, "Username/Email already taken");
                        }
                        else if (Pass.equals(Password)){
                            conn.createStatement().execute(query);
                            UIManager.put("Button.background", Color.BLUE);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog(null, "Successfully registered...");
                            new Dashboard();
                            this.setVisible(false);
                        }


                        else{
                            UIManager.put("Button.background", Color.BLUE);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog(null, "Confirm Password failed...");}
                    }
                    else{
                        UIManager.put("Button.background", Color.BLUE);
                        UIManager.put("Button.foreground", Color.white);
                        JOptionPane.showMessageDialog(null, "Registration Failed!!");}
                }
                catch(Exception ae) {
                    UIManager.put("Button.background", Color.BLUE);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Error Occurred." +
                            " Will be resolved in the next update." +
                            " Thanks.");
                    System.out.println(ae);
                }
            }
            else{
                UIManager.put("Button.background", Color.BLUE);
                UIManager.put("Button.foreground", Color.white);
                JOptionPane.showMessageDialog(null, "Email format isn't valid!");
            }
        }

        else if(e.getSource() == cancel) {
            setVisible(false);
            new HotelManagementSystem().setVisible(true);
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
            UIManager.put("Button.background", Color.BLUE);
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
        new HotelManagementSystem();
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

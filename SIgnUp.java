package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import GUI101.GUI_2;

import javax.swing.JOptionPane;

public class SIgnUp extends JFrame implements ActionListener {
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

    public SIgnUp() {
        loadSql();
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

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
        userName.setBounds(660, 300, 100, 30);
        userName.setFont(new Font("serif", Font.PLAIN, 15));
        add(userName);

        JLabel emailL = new JLabel("Email: ");
        emailL.setForeground(Color.WHITE);
        emailL.setBounds(660, 350, 100, 30);
        emailL.setFont(new Font("serif", Font.PLAIN, 15));
        add(emailL);

        JLabel psd_label = new JLabel("Password: ");
        psd_label.setForeground(Color.WHITE);
        psd_label.setBounds(660, 400, 100, 30);
        psd_label.setFont(new Font("serif", Font.PLAIN, 15));
        add(psd_label);

        JLabel psd_label2 = new JLabel("Confirm Password: ");
        psd_label2.setForeground(Color.WHITE);
        psd_label2.setBounds(660, 450, 160, 30);
        add(psd_label2);


        unfield = new JTextField();
        unfield.setBounds(775, 300, 150, 30);
        unfield.addKeyListener(new KeyAdapter() {
            String value = unfield.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    unfield.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '_' || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    unfield.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        add(unfield);


        emailF = new JTextField();
        emailF.setBounds(775, 350, 150, 30);
        emailF.addKeyListener(new KeyAdapter() {
            String email = emailF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                confirmEmail(email);

            }

        });
        add(emailF);


        psd = new JPasswordField();
        psd.setBounds(775, 400, 150, 30);
        psd.addKeyListener(new KeyAdapter() {
            String value = psd.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    psd.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    psd.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    psd.setText("");
                }
            }
        });
        add(psd);

        psd2 = new JPasswordField();
        psd2.setBounds(775, 450, 150, 30);
        psd2.addKeyListener(new KeyAdapter() {
            String value = psd.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    psd2.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    psd2.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    psd2.setText("");
                }
            }
        });
        add(psd2);


        signup = new JButton("Sign Up");
        signup.setBounds(660, 500, 100, 30);
        signup.setBackground(Color.GREEN);
        signup.setForeground(Color.BLACK);
        signup.setFont(new Font("serif", Font.BOLD, 20));
        signup.setFocusable(false);
        signup.addActionListener(this);
        add(signup);

        cancel = new JButton("Cancel");
        cancel.setBounds(780, 500, 100, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif", Font.BOLD, 20));
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
        login.setBounds(664, 550, 250, 30);
        add(login);

        getContentPane().setBackground(new Color(11, 9, 10));
        setLayout(null);
        setVisible(true);


    }

    public static void main(String[] args) {
        new SIgnUp();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            try {
                String username = unfield.getText();
                String Password = psd.getText();
                String Pass = psd2.getText();
                String mail = emailF.getText();

                String q = "CREATE TABLE IF NOT EXISTS login(username varchar(15), " +
                        "email varchar(50), password varchar(8), Pass varchar(15))";


                String query1 = "SELECT * FROM LOGIN WHERE username = '" + username + "' AND email = '" + mail + "'";
                String query = "INSERT INTO LOGIN VALUES ('" + username + "', " + "'" + mail + "', " + "'" + Password +"', " + "'" + Pass +"');";

                if (!(username.isBlank() ||mail.isBlank() || Password.isBlank()  || Pass.isBlank())) {
                    st = conn.createStatement();
                    st.executeUpdate(q);
                    ResultSet result = conn.createStatement().executeQuery(query1);
                    if (result.next()) {
                        JOptionPane.showMessageDialog( null, "Username/Email already taken");
                    }
                    else if (Pass.equals(Password)){
                        conn.createStatement().execute(query);
                        JOptionPane.showMessageDialog(null, "Successfully registered...");
//						unfield.setText("");
//						psd.setText("");
//						psd2.setText("");
                        new Dashboard();
                        this.setVisible(false);
                    }


                    else JOptionPane.showMessageDialog(null, "Confirm Password failed...");
                }


                else JOptionPane.showMessageDialog(null, "Registration Failed!!");
//				load();
            }



            catch(Exception ae) {
                System.out.println(ae);
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
            System.out.println(ae);
        }
    }
    public boolean confirmEmail(String email){
        String Cemail ="[^0-9@.][a-zA-Z0-9_\\-.]{8,}@[a-z]+(\\.[a-z]+)+$"; // minimum of 8 characters
        p = Pattern.compile(Cemail);
        m = p.matcher(email);
        return m.matches();
    }

}

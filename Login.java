package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JButton Login, cancel;
    JTextField unfield, mail;
    JPasswordField psd;
    JLabel signup, changePass, question;
    Statement st;
    String username;
    String Password = "";
    Connection conn;
    Font f = new Font("serif", Font.BOLD, 20);
    private boolean clicked = false;

    public Login() {
        loadSql();
        setResizable(false);
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

        login_image_label.setBounds(680,  150,  200,  200);
        login_image_label.setBackground(new Color(63, 10, 10));
        add(login_image_label);

        question = new JLabel("Set a security question?");
        question.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    setVisible(false);
                    new Question();

                }
            }
        });
        question.setFont(new Font("serif", Font.PLAIN, 25));
        question.setForeground(Color.ORANGE);
        question.setBounds(664, 400, 270, 30);
        add(question);

        JLabel userName = new JLabel("Username: ");
        userName.setForeground(Color.WHITE);
        userName.setBounds(660, 450, 150, 30);
        userName.setFont(new Font("serif", Font.PLAIN, 25));
        add(userName);

        JLabel psd_label = new JLabel("Password: ");
        psd_label.setForeground(Color.WHITE);
        psd_label.setBounds(660, 500, 150, 30);
        psd_label.setFont(new Font("serif", Font.PLAIN, 25));
        add(psd_label);

        unfield = new JTextField();
        unfield.setBounds(775, 450, 190, 30);
        unfield.setFont(f);
        add(unfield);

        psd = new JPasswordField();
        psd.setBounds(775, 500, 190, 30);
        psd.setFont(f);
        add(psd);


        Login = new JButton("LOGIN");
        Login.setBounds(660, 550, 140, 30);
        Login.setBackground(Color.GREEN);
        Login.setForeground(Color.BLACK);
        Login.setFont(new Font("serif", Font.BOLD, 25));
        Login.addActionListener(this);
        add(Login);

        cancel = new JButton("CANCEL");
        cancel.setBounds(820, 550, 140, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif", Font.BOLD, 25));
        cancel.addActionListener(this);
        add(cancel);

        signup = new JLabel("Don't Have An Account? "
                + "SIGN UP!");
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    setVisible(false);
                    new SIgnUp();

                }
            }
        });
        signup.setForeground(Color.ORANGE);
        signup.setBounds(664, 600, 400, 30);
        signup.setFont(new Font("serif", Font.PLAIN, 25));
        add(signup);


        JLabel changePass = new JLabel("Change Password");
        changePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!clicked) {
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to change your password?", "Changing",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION) {
                        clicked = true;
                        setVisible(false);
                        new FinalChangePassword();
                    }
                }
            }
        });
        changePass.setForeground(Color.orange);
        changePass.setBounds(664, 650, 190, 30);
        changePass.setFont(new Font("serif", Font.PLAIN, 20));
        add(changePass);

        JLabel forgot = new JLabel("Forgot Password?");
        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try{
                   if (!clicked ) {
                       mail = new JTextField();

                       int response = JOptionPane.showConfirmDialog(null, mail, "Enter your Email", JOptionPane.OK_CANCEL_OPTION,
                               JOptionPane.PLAIN_MESSAGE);
                       if (response == JOptionPane.OK_OPTION) {
                           mail.setBackground(Color.black);
                           mail.setForeground(Color.WHITE);
                           String ans = mail.getText();
                           System.out.println(ans);
                           String query = "SELECT Email FROM LOGIN WHERE email = '" + ans + "'";
                           Statement st = conn.createStatement();
                           ResultSet result = st.executeQuery(query);

                           while ( result.next()) {
                               JOptionPane.showMessageDialog(null, result.getString("Email"));
                               if (ans.equals(result.getString(1))) {
                                   JOptionPane.showMessageDialog(null, "Success");
                                   clicked = true;
                                   setVisible(false);
                                   new ForgotPassword();
                               }
                               else {
                                   JOptionPane.showMessageDialog(null, "Email does not exist!");
                               }
                           }
                       }
                   }
               }
               catch(Exception ae) {
                   ae.printStackTrace();
                   JOptionPane.showMessageDialog(null, "Error Occurred." +
                           " Will be resolved in the next update." +
                           " Thanks.");
                   System.out.println(ae);
               }

            }
        });
        forgot.setForeground(Color.orange);
        forgot.setBounds(840, 650, 230, 30);
        forgot.setFont(new Font("serif", Font.PLAIN, 20));
        add(forgot);

        getContentPane().setBackground(new Color(11, 9, 10));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);


    }

    public static void main(String[] args) {
        new Login();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Login) {
            try {
                username = unfield.getText();
                Password = psd.getText();

                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '"
                        + Password + "'";

                if (!(username.isBlank() || Password.isBlank())) {
                    ResultSet result = conn.createStatement().executeQuery(query);
                    if (result.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful...");
                        new Dashboard();
                        this.setVisible(false);

                    }
                    else
                        JOptionPane.showMessageDialog(null, "invalid username/password!", "WARNING",
                                JOptionPane.WARNING_MESSAGE);

                }
                else JOptionPane.showMessageDialog(null, "Login Failed!!!");

            }
            catch(Exception ae) {
                JOptionPane.showMessageDialog(null, "Error Occurred." +
                        " Will be resolved in the next update." +
                        " Thanks.");
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
        new SIgnUp();
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


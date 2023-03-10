package HotelManagementJavaProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends JFrame implements ActionListener, WindowListener {
    Connection conn;
    ImageIcon my_image;
    JLabel email, question, pass, Cpass;
    JTextField emailF, questionF;
    JPasswordField passF, CpassF;
    Font font;
    JButton submit;
    Statement st;
    Pattern p;
    public static String IMAGE_URL = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\Morgan Freeman.jpeg";
    Matcher m;

    public ForgotPassword(){
        loadSql();

        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);

        font = new Font("serif", Font.PLAIN, 20);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Forgot Password");

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\fp.png";

        // IMAGE PATH AND LABEL
        ImageIcon pass_image = new ImageIcon(path);
        Image img = pass_image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        ImageIcon new_pass_image = new ImageIcon(img);

        JLabel pass_image_label = new  JLabel(new_pass_image);

        pass_image_label.setBounds(670,  20,  200,  200);
        pass_image_label.setBackground(new Color(63, 10, 10));
        add(pass_image_label);

        email = new JLabel("Email: ");
        email.setForeground(Color.WHITE);
        email.setBounds(625, 260, 100, 30);
        email.setFont(font);
        add(email);

        question = new JLabel("Your favourite food?");
        question.setForeground(Color.WHITE);
        question.setBounds(625, 310, 200, 30);
        question.setFont(font);
        add(question);

        pass = new JLabel("New Password: ");
        pass.setForeground(Color.WHITE);
        pass.setBounds(625, 360, 150, 30);
        pass.setFont(font);
        add(pass);

        Cpass = new JLabel("Confirm Password: ");
        Cpass.setForeground(Color.WHITE);
        Cpass.setBounds(625, 410, 160, 30);
        Cpass.setFont(font);
        add(Cpass);

        emailF = new JTextField();
        emailF.setBounds(810, 260, 150, 30);
        add(emailF);

        questionF = new JTextField();
        questionF.setBounds(810, 310, 150, 30);
        questionF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    questionF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)){
                    questionF.setEditable(true);
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only");
                }

            }
        });
        add(questionF);

        passF = new JPasswordField();
        passF.setBounds(810, 360, 150, 30);
        passF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    passF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    passF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    passF.setText("");
                }
            }
        });
        add(passF);

        CpassF = new JPasswordField();
        CpassF.setBounds(810, 410, 150, 30);
        CpassF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    CpassF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    CpassF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    CpassF.setText("");
                }
            }
        });
        add(CpassF);

        submit = new JButton("SUBMIT");
        submit.setBounds(720, 470, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setToolTipText("Change password?");
        submit.setFont(new Font("serif", Font.BOLD, 18));
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);


        getContentPane().setBackground(new Color(102, 7, 8));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);

    }

//    private static void createAndShowGUI()
//    {
//        SwingUtilities.invokeLater(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    JDialog dialog = new JDialog();
//                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//                    dialog.setTitle("Image Loading Demo");
//
//                    ImageIcon new_login_image = new ImageIcon(IMAGE_URL);
//
//                    JLabel login_image_label = new  JLabel(new_login_image);
//                    dialog.add(login_image_label);
//
////                    dialog.add(new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(IMAGE_URL))))));
//                    JLabel error = new JLabel("Error occurred!");
//                    error.setFont(new Font("Serif", Font.BOLD, 30));
//                    error.setForeground(Color.WHITE);
//                    login_image_label.add(error);
//
//                    dialog.pack();
//                    dialog.setLocationByPlatform(true);
//                    dialog.setVisible(true);
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public static void main(String[] args)throws NullPointerException{
        new ForgotPassword();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            try{
                String email = emailF.getText();
                String ques = questionF.getText();
                String pass = passF.getText();
                String confirm = CpassF.getText();

                String query1 = "SELECT * FROM QUESTION WHERE email ='" + email + "' AND food = '" + ques + "'";
                String query2 =  "UPDATE LOGIN SET password ='" + pass + "', Pass = '" + confirm + "' WHERE email = '" + email + "'";
                String query3 = "SELECT * FROM LOGIN WHERE password ='" + pass + "'";

                if (!(email.isBlank() ||ques.isBlank() || pass.isBlank()  || confirm.isBlank())) {
                    ResultSet result = conn.createStatement().executeQuery(query1);
                    if (result.next()) {
                        ResultSet res = conn.createStatement().executeQuery(query3);
                        if(res.next()){
                            UIManager.put("Button.background", Color.BLACK);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog(null, "Duplicate Password!");
                        }
                        else{
                            if (pass.equals(confirm)){
                                st = conn.createStatement();
                                st.executeUpdate(query2);
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                JOptionPane.showMessageDialog(null, "Successful!");
                                emailF.setText("");
                                questionF.setText("");
                                passF.setText("");
                                CpassF.setText("");

                            }
                            else{
                                UIManager.put("Button.background", Color.BLACK);
                                UIManager.put("Button.foreground", Color.white);
                                JOptionPane.showMessageDialog(null, "Password mismatch!");
                            }
                        }

                    }
                    else{
                        UIManager.put("Button.background", Color.BLACK);
                        UIManager.put("Button.foreground", Color.white);
                        JOptionPane.showMessageDialog(null, "Error! Email/Question not correct!");
                    }

                }
                else{
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
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
        new Login();
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
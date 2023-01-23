package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends JFrame implements ActionListener{
    Connection conn;
    ImageIcon my_image;
    JLabel email, question, pass, Cpass;
    JTextField emailF, questionF;
    JPasswordField passF, CpassF;
    Font font;
    JButton submit;
    Statement st;
    Pattern p;
    Matcher m;

    public ForgotPassword(){
        loadSql();
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
        emailF.addKeyListener(new KeyAdapter() {
            String email = emailF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                confirmEmail(email);

            }

        });
        add(emailF);

        questionF = new JTextField();
        questionF.setBounds(810, 310, 150, 30);
        questionF.addKeyListener(new KeyAdapter() {
            String value = questionF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    questionF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)){
                    questionF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only");
                }

            }
        });
        add(questionF);

        passF = new JPasswordField();
        passF.setBounds(810, 360, 150, 30);
        passF.addKeyListener(new KeyAdapter() {
            String value = passF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    passF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    passF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    passF.setText("");
                }
            }
        });
        add(passF);

        CpassF = new JPasswordField();
        CpassF.setBounds(810, 410, 150, 30);
        CpassF.addKeyListener(new KeyAdapter() {
            String value = CpassF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    CpassF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    CpassF.setEditable(true);
                }
                else {
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
        submit.setFont(new Font("serif", Font.BOLD, 18));
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);




        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[])throws NullPointerException{
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
                            JOptionPane.showMessageDialog(null, "Duplicate Password!");
                        }
                        else{
                            System.out.println("hiiiii");
                           if (pass.equals(confirm)){
                               st = conn.createStatement();
                               st.executeUpdate(query2);
                               JOptionPane.showMessageDialog(null, "Successful!");
                           }
                           else{
                               JOptionPane.showMessageDialog(null, "Password mismatch!");
                           }
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error! Email/Question not correct!");
                    }

                }

//                UPDATE Customers
//                SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//                WHERE CustomerID = 1;

            }
            catch(Exception ae) {
                System.out.println(ae);
            }
        }
//        else if(e.getSource() == ){
//
//        }
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

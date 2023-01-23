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

public class FinalChangePassword extends JFrame implements ActionListener{
    JLabel currentPwLabel, newPwLabel, confirmPwLabel, usernameL;
    JPasswordField currentPasswordField, newPasswordField, confirmPasswordField, uField;
    JButton okButton, cancelButton;
    String currentPassword, newPassword, confirmPassword, uname;
    ImageIcon my_image;
    Connection conn;
    Statement st;

    public FinalChangePassword(){
        loadSql();
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Change Password");

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\eyes.png";

        // IMAGE PATH AND LABEL
        ImageIcon login_image = new ImageIcon(path);
        Image img = login_image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        ImageIcon new_login_image = new ImageIcon(img);

        JLabel login_image_label = new  JLabel(new_login_image);

        login_image_label.setBounds(700,  150,  200,  200);
        add(login_image_label);

        usernameL = new JLabel("Username: ");
        usernameL.setForeground(Color.WHITE);
        usernameL.setFont(new Font("serif", Font.PLAIN, 15));
        usernameL.setBounds(660, 400, 190, 30);
        add(usernameL);

        currentPwLabel = new JLabel("Current Password: ");
        currentPwLabel.setForeground(Color.white);
        currentPwLabel.setBounds(660, 450, 190, 30);
        currentPwLabel.setFont(new Font("serif", Font.PLAIN, 15));
        add(currentPwLabel);

        newPwLabel = new JLabel("New Password: ");
        newPwLabel.setForeground(Color.WHITE);
        newPwLabel.setBounds(660, 500, 100, 30);
        newPwLabel.setFont(new Font("serif", Font.PLAIN, 15));
        add(newPwLabel);

        confirmPwLabel = new JLabel("Confirm Password: ");
        confirmPwLabel.setForeground(Color.WHITE);
        confirmPwLabel.setBounds(660, 550, 200, 30);
        confirmPwLabel.setFont(new Font("serif", Font.PLAIN, 15));
        add(confirmPwLabel);

        uField = new JPasswordField();
        uField.setBackground(new Color(11, 9, 10));
        uField.setForeground(Color.WHITE);
        uField.setBounds(800, 400, 150, 30);
        uField.addKeyListener(new KeyAdapter() {
            String value = uField.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    uField.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '_' || ke.getKeyCode() == KeyEvent.VK_SHIFT)) {
                    uField.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        add(uField);

        currentPasswordField = new JPasswordField();
        currentPasswordField.setBackground(new Color(11, 9, 10));
        currentPasswordField.setForeground(Color.WHITE);
        currentPasswordField.setBounds(800, 450, 150, 30);
        currentPasswordField.addKeyListener(new KeyAdapter() {
           String value = currentPasswordField.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    currentPasswordField.setEditable(true);
                } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    currentPasswordField.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    currentPasswordField.setText("");
                }
            }
        });
        add(currentPasswordField);

        newPasswordField = new JPasswordField();
        newPasswordField.setBackground(new Color(11, 9, 10));
        newPasswordField.setForeground(Color.WHITE);
        newPasswordField.setBounds(800, 500, 150, 30);
        newPasswordField.addKeyListener(new KeyAdapter() {
            String value = newPasswordField.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    newPasswordField.setEditable(true);
                } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    newPasswordField.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    newPasswordField.setText("");
                }
            }
        });
        add(newPasswordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBackground(new Color(11, 9, 10));
        confirmPasswordField.setForeground(Color.WHITE);
        confirmPasswordField.setBounds(800, 550, 150, 30);
        confirmPasswordField.addKeyListener(new KeyAdapter() {
            String value = confirmPasswordField.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    confirmPasswordField.setEditable(true);
                } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    confirmPasswordField.setEditable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    confirmPasswordField.setText("");
                }
            }
        });
        add(confirmPasswordField);

        okButton = new JButton("OK");
        okButton.setBounds(660, 620, 100, 30);
        okButton.setBackground(new Color(177, 167, 166));
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("serif", Font.BOLD, 20));
        okButton.addActionListener(this);
        okButton.setFocusable(false);
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(780, 620, 100, 30);
        cancelButton.setBackground(new Color(177, 167, 166));
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setFont(new Font("serif", Font.BOLD, 20));
        cancelButton.addActionListener(this);
        cancelButton.setFocusable(false);
        add(cancelButton);

        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String args[])throws NullPointerException{
        new FinalChangePassword();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==okButton){
            try{
                uname = uField.getText();
                String cuPassword = currentPasswordField.getText();
                String newPassword = newPasswordField.getText();
                String conPassword = confirmPasswordField.getText();

                String query = "SELECT * FROM LOGIN WHERE username = '" + uname + "' AND password ='" + cuPassword + "'";
                String query1 = "UPDATE LOGIN SET password ='" + newPassword + "', Pass = '" + conPassword + "' WHERE username = '" + uname + "'";
                String query2 = "SELECT * FROM LOGIN WHERE password ='" + newPassword + "'";
                if(!(cuPassword.isBlank() || newPassword.isBlank() || conPassword.isBlank() || uname.isBlank())){
                    ResultSet result = conn.createStatement().executeQuery(query);
                    if (result.next()) {
                        ResultSet res = conn.createStatement().executeQuery(query2);
                        if(res.next()){
                            JOptionPane.showMessageDialog(null, "Duplicate Password!");
                        }
                        else{
                            if(newPassword.length()>3){
                                if(newPassword.equals(conPassword)){
                                    st = conn.createStatement();
                                    st.executeUpdate(query1);
                                    JOptionPane.showMessageDialog(null, "Successful!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Password mismatch!");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Password cannot be less than 4 digits!");
                            }
                        }
                    }

                    else{
                        JOptionPane.showMessageDialog(null, "Error! Username/Current Password not correct!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fill in the fields!");
                }
            }
            catch(Exception ae) {
				System.out.println(ae);
            }
        }
        else if(e.getSource() == cancelButton){
            new Login();
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

}





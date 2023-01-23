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

public class Question extends JFrame implements ActionListener {
    ImageIcon my_image;
    Connection conn;
    JLabel color, age, Oname, food, email;
    JTextField colorF, ageF, ONameF, foodF, emailF;
    Font font;
    JButton chk;
    Statement st;
    public Question(){
        loadSql();
        font = new Font("serif", Font.PLAIN, 15);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\any.png";

        // IMAGE PATH AND LABEL
        ImageIcon question_image = new ImageIcon(path);
        Image img = question_image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        ImageIcon new_question_image = new ImageIcon(img);

        JLabel question_image_label = new  JLabel(new_question_image);

        question_image_label.setBounds(650,  70,  200,  200);
        question_image_label.setBackground(new Color(63, 10, 10));
        add(question_image_label);

        email = new JLabel("Email: ");
        email.setForeground(Color.WHITE);
        email.setBounds(615, 300, 300, 30);
        email.setFont(font);
        add(email);

        color = new JLabel("What's your favourite color?");
        color.setForeground(Color.WHITE);
        color.setBounds(615, 350, 300, 30);
        color.setFont(font);
        add(color);

        food = new JLabel("What's your favorite food?");
        food.setForeground(Color.WHITE);
        food.setBounds(615, 400, 300, 30);
        food.setFont(font);
        add(food);

        age = new JLabel("What's your age?");
        age.setForeground(Color.WHITE);
        age.setBounds(615, 450, 250, 30);
        age.setFont(font);
        add(age);

        Oname =  new JLabel("What's your other name?");
        Oname.setForeground(Color.WHITE);
        Oname.setBounds(615, 500, 300, 30);
        Oname.setFont(font);
        add(Oname);

        emailF = new JTextField();
        emailF.setBounds(790, 300, 150, 30);
        emailF.addKeyListener(new KeyAdapter() {
            String mail = emailF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                String email ="[^0-9@.][a-zA-Z0-9_\\-.]{8,}@[a-z]+(\\.[a-z]+)+$"; // minimum of 8 characters
                Pattern p = Pattern.compile(email);
                Matcher m = p.matcher(mail);
                if(m.matches()){
                    emailF.setEditable(true);
                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Doesn't match regex format", "Error!", JOptionPane.ERROR_MESSAGE);
//                }

            }
        });
        add(emailF);

        colorF = new JTextField();
        colorF.setBounds(790, 350, 150, 30);
        colorF.addKeyListener(new KeyAdapter() {
            String value = colorF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    colorF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)){
                    colorF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only");
                }

            }
        });
        add(colorF);

        foodF = new JTextField();
        foodF.setBounds(790, 400, 150, 30);
        foodF.addKeyListener(new KeyAdapter() {
            String value = foodF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    foodF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)){
                    foodF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only");
                }
            }
        });
        add(foodF);

        ageF = new JTextField();
        ageF.setBounds(790, 450, 150, 30);
        ageF.addKeyListener(new KeyAdapter() {
            String value = ageF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ageF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ageF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    ageF.setText("");
                }
            }
        });
        add(ageF);

        ONameF = new JTextField();
        ONameF.setBounds(790, 500, 150, 30);
        ONameF.addKeyListener(new KeyAdapter() {
            String value = ONameF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ONameF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)){
                    ONameF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only");
                }
            }
        });
        add(ONameF);

        chk = new JButton("SUBMIT");
        chk.setBounds(700, 555, 100, 30);
        chk.setBackground(Color.BLACK);
        chk.setForeground(Color.WHITE);
        chk.setFocusable(false);
        font = new Font("serif", Font.PLAIN, 15);
        chk.setFont(font);
        chk.addActionListener(this);
        add(chk);

        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setVisible(true);


    }
    public static void main(String[] args) {
        new Question();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chk){
            try{
                String food = foodF.getText();
                String color = colorF.getText();
                String age = ageF.getText();
                String name = ONameF.getText();
                String email = emailF.getText();

                String query1 = "SELECT * FROM LOGIN WHERE email = '" + email + "'";
                String query = "INSERT INTO QUESTION VALUES ('" +email + "', " + "'" + food + "', " + "'" + color + "', " + "'" + age +"', " + "'" + name +"');";
                if (!(food.isBlank() ||color.isBlank() || age.isBlank()  || name.isBlank())) {
                    ResultSet result = conn.createStatement().executeQuery(query1);
                    if (result.next()) {
                        conn.createStatement().execute(query);
                        JOptionPane.showMessageDialog(null,"Successfully inputted" );
                        new Question().setVisible(false);
                        new Login();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error! Email doesn't exist!");
                    }
                }
            }
            catch(Exception ae) {
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
            System.out.println(ae);
        }
    }
}

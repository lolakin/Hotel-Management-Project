package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Feedback extends JFrame implements ActionListener {
    JButton submit, reset;
    JTextField nameF, mailF, feedbF;
    Container cp;
    JCheckBox check;
    JComboBox<String> agegrp;
    JFrame f;
    JRadioButton one, two, three, four, five;
    ButtonGroup ratingBG;
    Statement st;
    ImageIcon my_image;
    Connection conn;
    String[] ages = {"Below 18", "18-44", "45 -60", "Above 60"};
    Color p = new Color(102, 7, 8);
    Font fontF = new Font("Calibri", Font.PLAIN, 16);
    Font font = new Font("Calibri", Font.PLAIN, 18);
    Font font1 = new Font("Arial", Font.BOLD, 36);

    public Feedback(){
        loadSql();
        setTitle("Feedback Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(540, 550);
        cp=getContentPane();
        cp.setBackground(p);

        my_image = new ImageIcon("hotel2.png");
        setIconImage(my_image.getImage());

        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        JLabel heading = new JLabel("FEEDBACK SURVEY");
        heading.setFont(font1);
        heading.setBounds(80, 0, 440, 45);

        JLabel name= new JLabel();
        name.setText("Name: ");
        name.setForeground(Color.WHITE);
        name.setFont(font);
        name.setBounds(50,60,80,28);
        cp.add(name);

        nameF = new JTextField();
        nameF.setFont(fontF);
        nameF.setForeground(Color.BLACK);
        nameF.setBounds(230, 60, 240, 28);
        cp.add(nameF);

        JLabel mail= new JLabel();
        mail.setText("Email Address: ");
        mail.setFont(font);
        mail.setForeground(Color.WHITE);
        mail.setBounds(50,110, 150, 28);
        cp.add(mail);

        mailF = new JTextField();
        mailF.setFont(fontF);
        mailF.setForeground(Color.BLACK);
        mailF.setBounds(230, 105, 240, 28);
        cp.add(mailF);

        check = new JCheckBox("Receive promotional emails");
        check.setFont(new Font("Calibri", Font.PLAIN, 15));
        check.setBounds(230, 135, 240, 20);
        check.setSelected(true);
        cp.add(check);

        JLabel age= new JLabel();
        age.setText("Age Group: ");
        age.setForeground(Color.WHITE);
        age.setFont(font);
        age.setBounds(50,180,150,28);
        cp.add(age);

        agegrp= new JComboBox<>(ages);
        agegrp.setBounds(230,180,120,28);
        cp.add(agegrp);


        JLabel rating= new JLabel();
        rating.setText("Rating: ");
        rating.setForeground(Color.WHITE);
        rating.setFont(font);
        rating.setBounds(50,230,100,28);
        cp.add(rating);


        one=new JRadioButton("1");
        two=new JRadioButton("2");
        three=new JRadioButton("3");
        four=new JRadioButton("4");
        five=new JRadioButton("5");

        one.setBounds(230, 230, 40, 28);
        two.setBounds(280, 230, 40, 28);
        three.setBounds(330, 230, 40, 28);
        four.setBounds(380, 230, 40, 28);
        five.setBounds(430, 230, 40, 28);
        five.setSelected(true);

        add(one);
        add(two);
        add(three);
        add(four);
        add(five);

        ratingBG= new ButtonGroup();  //A buttongroup ensures only one of the 5 radio buttons are selected at a time
        ratingBG.add(one);
        ratingBG.add(two);
        ratingBG.add(three);
        ratingBG.add(four);
        ratingBG.add(five);

        JLabel feedback= new JLabel();
        feedback.setText("Feedback (optional): ");
        feedback.setFont(font);
        feedback.setForeground(Color.WHITE);
        feedback.setBounds(50,280,170,28);
        cp.add(feedback);

        feedbF = new JTextField();
        feedbF.setFont(fontF);
        feedbF.setForeground(Color.BLACK);
        feedbF.setBounds(230, 280, 240, 84);
        cp.add(feedbF);

        submit= new JButton("Submit");
        submit.setFont(font);
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setBounds(165, 410, 100, 28);
        submit.addActionListener(this);
        cp.add(submit);


        reset= new JButton("Reset");
        reset.setFont(font);
        reset.setForeground(Color.WHITE);
        reset.setBackground(new Color(66, 34, 130));
        reset.setBounds(275, 410, 100, 28);
        reset.addActionListener(this);
        cp.add(reset);

        setLayout(null);
        setVisible(true);

    }

    public  static void main(String[] args){
        new Feedback();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag=false;
        String mail = mailF.getText();
        String name = nameF.getText();
        String age = (String) agegrp.getSelectedItem();
        String rating = ratingBG.getSelection().getActionCommand();
        String feedb = feedbF.getText();
        f = new JFrame();
        String query2 = "SELECT * FROM CUSTOMER WHERE name = '" + name + "'" ;
        String query = "CREATE TABLE IF NOT EXISTS feedback(name varchar(20), email varchar(30), ageGroup varchar(15), rating char(1), feedback varchar(200))";
        String query1 = "INSERT INTO feedback VALUES ('" + name + "', " + "'" + mail + "', " + "'" +
                age + "', " + "'" + rating + "', " + "'" + feedb + "')";
        String query3 = "SELECT * FROM LOGIN WHERE email = '" + mail + "'";

        if(e.getSource() == submit){
            try{
                if(!(mail.isBlank() || name.isBlank())){
                    st = conn.createStatement();
                    st.executeUpdate(query);

                    ResultSet res = conn.createStatement().executeQuery(query2);
                    if (res.next()){
                        ResultSet rs = conn.createStatement().executeQuery(query3);
                        if(rs.next()){
                            conn.createStatement().execute(query1);
                            JOptionPane.showMessageDialog(null, "Successfully inputted");
                            this.setVisible(false);
                            new Reception();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Email doesn't exist in our database");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Name doesn't exist");
                    }
                }
                else{
                    flag = true;
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                }

                if(flag)
                {
                    String r;
                    if(one.isSelected())
                        r="One star";
                    else if(two.isSelected())
                        r="Two stars";
                    else if(three.isSelected())
                        r="Three stars";
                    else if(four.isSelected())
                        r="Four stars";
                    else
                        r="Five stars";
                    String s1= "Thank you for your valuable Feedback!\n\nYour Responses:-\n";
                    String s2= "Name: "+name +"\nEmail: "+mail +"\nAge group: "+ age +"\nRating: "+r+"\nFeedback: "+feedb;
                    String disp =s1+s2;
                    JOptionPane.showMessageDialog(f, disp);
                }
            }
            catch (Exception ae){
                JOptionPane.showMessageDialog(null, ae);
            }
        }

        else if(e.getSource() == reset){
            nameF.setText(null);
            mailF.setText(null);
            feedbF.setText(null);
            agegrp.setSelectedIndex(0);
            one.setSelected(false);
            two.setSelected(false);
            three.setSelected(false);
            four.setSelected(false);
            five.setSelected(true);
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

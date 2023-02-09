package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback extends JFrame implements ActionListener, WindowListener {
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
        setResizable(false);
        setSize(540, 550);
        cp=getContentPane();
        cp.setBackground(p);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());


        JLabel heading = new JLabel("FEEDBACK SURVEY");
        heading.setFont(font1);
        heading.setForeground(Color.WHITE);
        heading.setBounds(80, 10, 440, 45);
        cp.add(heading);

        JLabel name= new JLabel();
        name.setText("Name: ");
        name.setForeground(Color.WHITE);
        name.setFont(font);
        name.setBounds(50,70,80,28);
        cp.add(name);

        nameF = new JTextField();
        nameF.setFont(fontF);
        nameF.setForeground(Color.BLACK);
        nameF.setBounds(230, 70, 240, 28);
        cp.add(nameF);

        JLabel mail= new JLabel();
        mail.setText("Email Address: ");
        mail.setFont(font);
        mail.setForeground(Color.WHITE);
        mail.setBounds(50,120, 150, 28);
        cp.add(mail);

        mailF = new JTextField();
        mailF.setFont(fontF);
        mailF.setForeground(Color.BLACK);
        mailF.setBounds(230, 115, 240, 28);
        cp.add(mailF);

        check = new JCheckBox("Receive promotional emails");
        check.setFont(new Font("Calibri", Font.PLAIN, 15));
        check.setBounds(230, 145, 240, 20);
        check.setSelected(true);
        cp.add(check);

        JLabel age= new JLabel();
        age.setText("Age Group: ");
        age.setForeground(Color.WHITE);
        age.setFont(font);
        age.setBounds(50,190,150,28);
        cp.add(age);

        agegrp= new JComboBox<>(ages);
        agegrp.setBounds(230,190,120,28);
        cp.add(agegrp);

        JLabel rating= new JLabel();
        rating.setText("Rating: ");
        rating.setForeground(Color.WHITE);
        rating.setFont(font);
        rating.setBounds(50,240,100,28);
        cp.add(rating);


        one=new JRadioButton("1");
        two=new JRadioButton("2");
        three=new JRadioButton("3");
        four=new JRadioButton("4");
        five=new JRadioButton("5");

        one.setBounds(230, 240, 40, 28);
        two.setBounds(280, 240, 40, 28);
        three.setBounds(330, 240, 40, 28);
        four.setBounds(380, 240, 40, 28);
        five.setBounds(430, 240, 40, 28);
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
        feedback.setBounds(50,290,170,28);
        cp.add(feedback);

        feedbF = new JTextField();
        feedbF.setFont(fontF);
        feedbF.setForeground(Color.BLACK);
        feedbF.setBounds(230, 290, 240, 84);
        cp.add(feedbF);

        submit= new JButton("Submit");
        submit.setFont(font);
        submit.setToolTipText("Submit your feedback...");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setBounds(165, 420, 100, 28);
        submit.addActionListener(this);
        cp.add(submit);


        reset= new JButton("Reset");
        reset.setFont(font);
        reset.setToolTipText("Reset Form...");
        reset.setForeground(Color.WHITE);
        reset.setBackground(new Color(66, 34, 130));
        reset.setBounds(275, 420, 100, 28);
        reset.addActionListener(this);
        cp.add(reset);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);

    }

    public  static void main(String[] args){
        new Feedback();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag=false;
        String mail = mailF.getText();
        String name = nameF.getText().toUpperCase();
        String age = (String) agegrp.getSelectedItem();
        String r;
        String feedb = feedbF.getText().replace("'", "\"").toUpperCase();
        f = new JFrame();
        String query2 = "SELECT * FROM CUSTOMER WHERE name = '" + name + "'" ;
        String query = "CREATE TABLE IF NOT EXISTS feedback(name varchar(20), email varchar(30), ageGroup varchar(15), rating char(15), feedback TEXT)";
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
                            flag = true;
                            if (one.isSelected())
                                r = "One star";
                            else if (two.isSelected())
                                r = "Two stars";
                            else if (three.isSelected())
                                r = "Three stars";
                            else if (four.isSelected())
                                r = "Four Stars";
                            else
                                r = "Five stars";


                            String query1 = "INSERT INTO feedback VALUES ('" + name + "', " + "'" + mail + "', " + "'" +
                                    age + "', " + "'" + r + "', " + "'" + feedb + "')";
                            conn.createStatement().execute(query1);
                            String s1= "Thank you for your valuable Feedback!\n\nYour Responses:-\n";
                            String s2= "Name: "+name +"\nEmail: "+mail +"\nAge group: "+ age +"\nRating: "+r+"\nFeedback: "+feedb;
                            String disp =s1+s2;
                            JOptionPane.showMessageDialog(f, disp);

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

                    JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                }


            }
            catch (Exception ae){
                JOptionPane.showMessageDialog(null, "Error Occurred." +
                        " Will be resolved in the next update." +
                        " Thanks.");
//                JOptionPane.showMessageDialog(null, ae);
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
        new Reception();
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

package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PickUpService extends JFrame implements ActionListener, WindowListener {

    Connection conn;
    ImageIcon my_image;
    JButton submit, back;
    JComboBox<String> cars;
    JTextField addressF, timeF, priceF;
    private boolean clicked = false;
    Statement st;

    public PickUpService() {
        loadSql();

        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);

        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        setResizable(false);
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(460, 100, 650, 700);

        JLabel heading = new JLabel("PICKUP SERVICE");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(125,  5, 500, 50);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\pickup.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon checkout_image = new ImageIcon(path);
        Image img = checkout_image.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);

        ImageIcon new_checkout_image = new ImageIcon(img);

        JLabel checkout_image_label = new  JLabel(new_checkout_image);

        checkout_image_label.setBounds(150,  70,  300,  300);
        add(checkout_image_label);


        JLabel car = new JLabel("CAR");
        car.setForeground(Color.WHITE);
        car.setBounds(225, 400, 150, 20);
        car.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(car);

        ArrayList <String> cars_list = new ArrayList<>();
        try {
            String query = "SELECT * FROM car";
            ResultSet result = conn.createStatement().executeQuery(query);
            while(result.next()) {
                cars_list.add(result.getString("model"));
            }
        }
        catch(Exception e) {
            System.out.println(e);

        }

        String[] available_cars = cars_list.toArray(new String[cars_list.size()]);
        cars = new JComboBox<>(available_cars);
        String room_no = (String) cars.getSelectedItem();
        cars.setBackground(Color.WHITE);
        cars.setBounds(275, 400, 100, 20);
        add(cars);

        JLabel addressL = new JLabel("ADDRESS: ");
        addressL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addressL.setForeground(Color.WHITE);
        addressL.setBounds(195, 440, 150, 20);
        add(addressL);

        addressF = new JTextField();
        addressF.setText("address...");
        addressF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    addressF.setText("");
                }
            }
        });
        addressF.setBounds(275,  440,  150,  20);
        add(addressF);

        JLabel timeL = new JLabel("TIME: ");
        timeL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        timeL.setForeground(Color.WHITE);
        timeL.setBounds(215, 480, 150, 20);
        add(timeL);

        timeF = new JTextField();
        timeF.setText("hh:mm:ss");
        timeF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!clicked) {
                    clicked = true;
                    timeF.setText("");
                }
            }
        });

        timeF.setBounds(275,  480,  100,  20);
        add(timeF);

        JLabel priceL = new JLabel("PRICE: ");
        priceL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        priceL.setForeground(Color.WHITE);
        priceL.setBounds(215, 520, 150, 20);
        add(priceL);

        priceF = new JTextField();
        priceF.setBounds(275,  520,  100,  20);
        priceF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    priceF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    priceF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    priceF.setText("");
                }
            }
        });
        add(priceF);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setToolTipText("Book a ride!");
        submit.setFocusable(false);
        submit.setBounds(180, 565, 115, 30);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setToolTipText("Back to reception...");
        back.setBounds(350, 565, 115, 30);
        add(back);


        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(this);

    }

    public static void main(String[] args) {
        new PickUpService();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String car = (String)cars.getSelectedItem();
            String address = addressF.getText();
            String time = timeF.getText();
            String price = priceF.getText();

            Pattern pt = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
            Matcher mt = pt.matcher(time);

            if (mt.matches()){
                try {
                    String depA = "SELECT price FROM car WHERE model = '" + car + "'";
                    String dep = "SELECT * FROM car WHERE price = '" + price + "' AND model = '" + car + "'";
                    String q = "CREATE TABLE IF NOT EXISTS pickup(car varchar(15), address varchar(30), time time)";
                    String query = "INSERT INTO pickup VALUES ('" + car +"', " + "'" + address + "', " + "'" + time + "')";
                    String qq = "DELETE FROM car WHERE  model = '" + car + "'";
                    assert car != null;
                    if(!(car.isBlank() || address.isBlank() || time.isBlank())){
                        st = conn.createStatement();
                        st.executeUpdate(q);
                        ResultSet rr = conn.createStatement().executeQuery(dep);

                        if (rr.next()){
                            UIManager.put("Button.background", Color.BLACK);
                            UIManager.put("Button.foreground", Color.white);
                            conn.createStatement().execute(query);
                            st.executeUpdate(qq);
                            String message = "New pickup added !!!";
                            JOptionPane.showMessageDialog(null, message);
                            this.setVisible(false);
                        }
                        else{
                            ResultSet dA = conn.createStatement().executeQuery(depA);
                            dA.next();
                            String ma = dA.getString(1);
                            String m = "The price for car model: " + car + " is: " + ma;
                            UIManager.put("Button.background", Color.BLACK);
                            UIManager.put("Button.foreground", Color.white);
                            JOptionPane.showMessageDialog(null, "Wrong price for car model: " + car);
                            JOptionPane.showMessageDialog(null, m);
                        }
                    }
                    else{
                        UIManager.put("Button.background", Color.BLACK);
                        UIManager.put("Button.foreground", Color.white);
                        JOptionPane.showMessageDialog(null, "Fill in the fields!!!");
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
                JOptionPane.showMessageDialog(null, "Time "+ time +" is invalid 24Hours Format");
            }
        }

        else if(e.getSource() == back) {
            this.setVisible(false);
            new Reception().setVisible(true);
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

package HotelManagementJavaProject;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ImageIcon;

public class AddCar extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JTextField companyF, modelF, car_noF, colorF, priceF;
    ButtonGroup fuel_typeG;
    JButton submit;
    Connection conn;
    Statement st;

    public AddCar() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(375, 175, 800, 500);

        JLabel heading = new JLabel("ADD CAR");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(300,  10, 300, 35);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\car.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon car_image = new ImageIcon(path);
        Image img = car_image.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);

        ImageIcon new_car_image = new ImageIcon(img);

        JLabel car_image_label = new  JLabel(new_car_image);

        car_image_label.setBounds(300,  70,  400,  300);
        add(car_image_label);

        JLabel companyL = new JLabel("Company: ");
        companyL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        companyL.setForeground(Color.WHITE);
        companyL.setBounds(50, 70, 100, 20);
        add(companyL);

        companyF = new JTextField();
        companyF.setBounds(140,  70,  100,  20);
        companyF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
	            	 companyF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    companyF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4  || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                )) {
                    companyF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets and Numerics Only !");
                }

            }
        });
        add(companyF);

        JLabel modelL = new JLabel("Model: ");
        modelL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        modelL.setForeground(Color.WHITE);
        modelL.setBounds(50, 110, 100, 20);
        add(modelL);

        modelF = new JTextField();
        modelF.setBounds(140,  110,  100,  20);
        modelF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    modelF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    modelF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT
                        || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_ALT
                        || ke.getKeyCode() == KeyEvent.VK_F4 )) {
                    modelF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets and Numerics only !");
                }

            }
        });
        add(modelF);

        JLabel car_noL = new JLabel("Number: ");
        car_noL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        car_noL.setForeground(Color.WHITE);
        car_noL.setBounds(50, 150, 100, 20);
        add(car_noL);

        car_noF = new JTextField();
        car_noF.setBounds(140,  150,  100,  20);
        car_noF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    car_noF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    car_noF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    car_noF.setText("");
                }
            }
        });
        add(car_noF);

        JLabel fuelL = new JLabel("Fuel: ");
        fuelL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fuelL.setForeground(Color.WHITE);
        fuelL.setBounds(50, 190, 100, 20);
        add(fuelL);

        JRadioButton petrol = new JRadioButton("Petrol");
        petrol.setActionCommand("petrol");
        petrol.setFont(new Font("Tahoma", Font.PLAIN, 14));
        petrol.setBackground(new Color(32, 32, 32));
        petrol.setForeground(Color.WHITE);
        petrol.setBounds(140, 190, 80, 20);
        add(petrol);


        JRadioButton diesel = new JRadioButton("Diesel");
        diesel.setActionCommand("diesel");
        diesel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        diesel.setBackground(new Color(32, 32, 32));
        diesel.setForeground(Color.WHITE);
        diesel.setBounds(140, 220, 80, 20);
        add(diesel);

        JRadioButton electric = new JRadioButton("Electric");
        electric.setActionCommand("electric");
        electric.setFont(new Font("Tahoma", Font.PLAIN, 14));
        electric.setBackground(new Color(32, 32, 32));
        electric.setForeground(Color.WHITE);
        electric.setBounds(140, 250, 80, 20);
        add(electric);

        fuel_typeG = new ButtonGroup();
        fuel_typeG.add(petrol);
        fuel_typeG.add(diesel);
        fuel_typeG.add(electric);


        JLabel colorL = new JLabel("Color: ");
        colorL.setForeground(Color.WHITE);
        colorL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        colorL.setBounds(50, 290, 100, 20);
        add(colorL);

        colorF = new JTextField();
        colorF.setBounds(140,  290,  100,  20);
        colorF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    colorF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4)) {
                    colorF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }

            }
        });

        add(colorF);

        JLabel priceL = new JLabel("Price: ");
        priceL.setForeground(Color.WHITE);
        priceL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        priceL.setBounds(50, 330, 100, 20);
        add(priceL);

        priceF = new JTextField();
        priceF.setBounds(140,  330,  100,  20);
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
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    priceF.setText("");
                }
            }
        });
        add(priceF);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setToolTipText("Add New Car");
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(325, 400, 115, 30);
        add(submit);

        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new AddCar();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String company = companyF.getText();
        String model = modelF.getText();
        String number = car_noF.getText();
        String fuel = fuel_typeG.getSelection().getActionCommand();
        String color = colorF.getText().toUpperCase();
        String price = priceF.getText();

        try {
            String q = "CREATE TABLE IF NOT EXISTS car(company varchar(30), model varchar(15), " +
                    "number int, fuel char(10), color char(10), price DECIMAL(10,2))";
            String query1 = "SELECT * FROM car WHERE number = '" + number + "'";
            String query = "INSERT INTO car VALUES('" + company + "', " + "'" + model + "', " + "'" + number + "', " + "'" + fuel + "', " + "'" + color + "', " + "'" + price + "')";
            if (!(number.isBlank())) {
                st = conn.createStatement();
                st.executeUpdate(q);
                ResultSet res = conn.createStatement().executeQuery(query1);
                if (res.next()) {
                    JOptionPane.showMessageDialog(null, "Car already exists");
                }
                else {
                    conn.createStatement().execute(query);
                    String message = "New Car " + company + "-" + model + " Added !!!";
                    JOptionPane.showMessageDialog(null, message);
                    this.setVisible(false);
                }
            }

            else JOptionPane.showMessageDialog(null, "Addition failed!!!");
        }
        catch(Exception ae) {
            JOptionPane.showMessageDialog(null, "Error Occurred." +
                    " Will be resolved in the next update." +
                    " Thanks.");
            System.out.println(ae);
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
        new Dashboard();
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

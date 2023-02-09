package HotelManagementJavaProject;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Restaurant extends JFrame implements ActionListener, WindowListener {
    JButton bookfood, Home, addItem, rItem, back, logout;
    JTable table;
    ImageIcon my_image;
    Connection conn;
    Font Tfont,font;
    JPasswordField pf;

    public Restaurant(){
        font = new Font("Tahoma", Font.BOLD, 15);
        Tfont = new Font("arial", Font.PLAIN, 15);
        loadSql();
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        Home = new JButton("HOME");
        Home.setBounds(1, 1, 100, 30);
        Home.setBackground(Color.WHITE);
        Home.setForeground(Color.BLACK);
        Home.setFont(font);
        Home.setFocusable(false);
        Home.addActionListener(this);
        add(Home);

        back = new JButton("BACK");
        back.setBounds(1155, 1, 100, 30);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(font);
        back.setFocusable(false);
        back.addActionListener(this);
        add(back);

        logout = new JButton("LOG OUT");
        logout.setBounds(1255, 1, 110, 30);
        logout.setBackground(Color.WHITE);
        logout.setForeground(Color.BLACK);
        logout.setFont(font);
        logout.setFocusable(false);
        logout.addActionListener(this);
        add(logout);

        table = new JTable();
        table.setBackground(new Color(255, 204, 204));
        table.setBounds(350, 100, 700, 450);
        table.setRowHeight(20);
        table.setForeground(Color.BLACK);
        add(table);

        bookfood = new JButton("BOOK FOOD");
        bookfood.addActionListener(this);
        bookfood.setBounds(650, 600, 130, 30);
        bookfood.setBackground(Color.WHITE);
        bookfood.setForeground(Color.BLACK);
        bookfood.setFont(font);
        bookfood.setFocusable(false);
        add(bookfood);

        load();
        getContentPane().setBackground(new Color(102, 7, 8));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args){
       new Restaurant();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Home){
            this.setVisible(false);
            new Dashboard();
        }
        else if(e.getSource() == back){
            this.setVisible(false);
            new Reception();
        }
        else if(e.getSource() == logout){
            this.setVisible(false);
            new Login();
        }
        else if(e.getSource() == bookfood){
            this.setVisible(false);
            new BookFood();
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
    public void load() {
        try {
            String query = "SELECT * FROM RestItem";
            ResultSet result = conn.createStatement().executeQuery(query);
            table.setModel (DbUtils.resultSetToTableModel(result));
        }
        catch(Exception ae) {
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
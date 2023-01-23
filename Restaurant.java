package HotelManagementJavaProject;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Restaurant extends JFrame implements ActionListener {
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
//        bookfood.setIcon(new ImageIcon(getClass().getResource("C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\login.jpeg")));
        bookfood.addActionListener(this);
        bookfood.setBounds(200, 600, 130, 30);
        bookfood.setBackground(Color.WHITE);
        bookfood.setForeground(Color.BLACK);
        bookfood.setFont(font);
        bookfood.setFocusable(false);
        add(bookfood);

        addItem = new JButton("ADD ITEM");
//        addItem.setIcon(new ImageIcon(getClass().getResource("C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\login.jpeg")));
        addItem.addActionListener(this);
        addItem.setBounds(650, 600, 120, 30);
        addItem.setBackground(Color.WHITE);
        addItem.setForeground(Color.BLACK);
        addItem.setFont(font);
        addItem.setFocusable(false);
        add(addItem);

        rItem = new JButton("REMOVE ITEM");
//        rItem.setIcon(new ImageIcon(getClass().getResource("C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\login.jpeg")));
        rItem.addActionListener(this);
        rItem.setBounds(1050, 600, 150, 30);
        rItem.setBackground(Color.WHITE);
        rItem.setForeground(Color.BLACK);
        rItem.setFont(font);
        rItem.setFocusable(false);
        add(rItem);

        load();
        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setVisible(true);
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
        else if(e.getSource() == addItem){
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    this.setVisible(false);
//                    new Restaurant().setVisible(false);
                    new AddItem();

                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }

        }
        else if(e.getSource() == rItem){
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new Restaurant().setVisible(false);
                    new RemoveItem();

                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
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
    public void load() {
        try {
            String query = "SELECT * FROM RestItem";
            ResultSet result = conn.createStatement().executeQuery(query);
            table.setModel (DbUtils.resultSetToTableModel(result));
        }
        catch(Exception ae) {
            System.out.println(ae);
        }
    }
}
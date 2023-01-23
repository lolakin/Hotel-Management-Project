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

public class AddItem extends JFrame implements ActionListener {
    Font font;
    Connection conn;
    ImageIcon my_image;
    JButton add, back;
    JLabel ItemName, ItemP, ItemNo;
    JTextField ItemNameF, ItemPF, ItemNoF;
     ImageIcon icon, new_icon;
    String p;
    Image img;

    public AddItem(){
        loadSQL();
        font = new Font("serif", Font.BOLD, 25);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        p = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\left.png";

        icon = new ImageIcon(p);
        img = icon.getImage().getScaledInstance(20, 40, Image.SCALE_DEFAULT);
        new_icon = new ImageIcon(img);

        back = new JButton(new_icon);
        back.setText("BACK");
        back.setToolTipText("Move Back");
        back.setBounds(1255, 1, 100, 30);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("Tahoma", Font.BOLD, 12));
        back.setFocusable(false);
        back.addActionListener(this);
        add(back);

        ItemNo = new JLabel("Item No: ");
        ItemNo.setForeground(Color.WHITE);
        ItemNo.setBounds(500, 200, 170, 30);
        ItemNo.setFont(font);
        add(ItemNo);

        ItemNoF = new JTextField();
        ItemNoF.setBounds(650,  200,  300,  30);
        ItemNoF.addKeyListener(new KeyAdapter() {
            String value = ItemNoF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ItemNoF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    ItemNoF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    ItemNoF.setText("");
                }
            }
        });
        add(ItemNoF);

        ItemName = new JLabel("Item Name: ");
        ItemName.setForeground(Color.WHITE);
        ItemName.setBounds(500, 290, 170, 30);
        ItemName.setFont(font);
        add(ItemName);

        ItemNameF = new JTextField();
        ItemNameF.setBounds(650, 290, 300, 30);
        ItemNameF.addKeyListener(new KeyAdapter() {
            String value = ItemNameF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ItemNameF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '_' || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    ItemNameF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        add(ItemNameF);

        ItemP = new JLabel("Item Price: ");
        ItemP.setForeground(Color.WHITE);
        ItemP.setBounds(500, 380, 170, 30);
        ItemP.setFont(font);
        add(ItemP);

        ItemPF = new JTextField();
        ItemPF.setBounds(650,  380,  300,  30);
        ItemPF.addKeyListener(new KeyAdapter() {
            String value = ItemPF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ItemPF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    ItemPF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    ItemPF.setText("");
                }
            }
        });
        add(ItemPF);

        p = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\basket.png";
        icon = new ImageIcon(p);
        img = icon.getImage().getScaledInstance(80, 70, Image.SCALE_DEFAULT);
        new_icon = new ImageIcon(img);
        add = new JButton(new_icon);
        add.setText("ADD ITEM");
        add.setToolTipText("Go Back");
        add.setBounds(590, 450, 200, 100);
        add.setBackground(Color.WHITE);
        add.setForeground(Color.BLACK);
        add.setFont(new Font("Tahoma", Font.BOLD, 15));
        add.setFocusable(false);
        add.addActionListener(this);
        add(add);

        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args){
        new AddItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
            try{
                String name = ItemNameF.getText();
                String price = ItemPF.getText();
                String ItemNo = ItemNoF.getText();

                String query1 = "SELECT * FROM RestItem WHERE itemNo = '" + ItemNo + "'";
//                String query2 = "SELECT * FROM RestItem WHERE itemNo = '" + ItemNo + "' AND itemName = '" + name + "'";
                String query = "INSERT INTO RestItem VALUES ('" + ItemNo + "', " + "'" + name + "', " + "'" + price + "');";

                if (!(name.isBlank() ||price.isBlank() || ItemNo.isBlank())) {
                    ResultSet result = conn.createStatement().executeQuery(query1);
                    if (result.next()) {
                        JOptionPane.showMessageDialog( null, "Item/Item No already exists");
                    }
                    else{
                        conn.createStatement().execute(query);
                        JOptionPane.showMessageDialog(null, "Successfully inputted...");
                        this.setVisible(false);
                        new Restaurant();
                    }


                }
                else JOptionPane.showMessageDialog(null, "Fill in the fields!");

            }
            catch(Exception ae) {
                System.out.println(ae);
            }

        }
        else if(e.getSource() == back){
            new Dashboard();
            this.setVisible(false);
        }
    }
    public void loadSQL() {
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

package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddItem extends JFrame implements ActionListener, WindowListener {
    Font font;
    Connection conn;
    ImageIcon my_image;
    JButton add, back;
    JLabel ItemName, ItemP, ItemNo;
    JTextField ItemNameF, ItemPF, ItemNoF;
     ImageIcon icon, new_icon;
     Statement st;
    String p;
    Image img;

    public AddItem(){
        loadSQL();
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);

        setResizable(false);
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
        back.setToolTipText("Back to dashboard...");
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
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ItemNoF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 ){
                    ItemNoF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
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
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ItemNameF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 )) {
                    ItemNameF.setEditable(true);
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
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
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    ItemPF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyCode() == KeyEvent.VK_UP
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 ){
                    ItemPF.setEditable(true);
                }
                else {
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
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
        add.setToolTipText("Add a new item into the restaurant...");
        add.setBounds(590, 450, 200, 100);
        add.setBackground(Color.WHITE);
        add.setForeground(Color.BLACK);
        add.setFont(new Font("Tahoma", Font.BOLD, 15));
        add.setFocusable(false);
        add.addActionListener(this);
        add(add);

        getContentPane().setBackground(new Color(102, 7, 8));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
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

                String q = "CREATE TABLE IF NOT EXISTS RestItem(itemNo int, itemName char(30), itemPrice DECIMAL(10,2))";
                String query1 = "SELECT * FROM RestItem WHERE itemNo = '" + ItemNo + "'";
                String query2 = "SELECT * FROM RestItem WHERE itemName = '" + name + "'";
                String query = "INSERT INTO RestItem VALUES ('" + ItemNo + "', " + "'" + name + "', " + "'" + price + "');";

                if (!(name.isBlank() ||price.isBlank() || ItemNo.isBlank())) {
                    st = conn.createStatement();
                    st.executeUpdate(q);

                    ResultSet result = conn.createStatement().executeQuery(query1);
                    ResultSet res = conn.createStatement().executeQuery(query2);
                    if (result.next()) {
                        UIManager.put("Button.background", Color.BLACK);
                        UIManager.put("Button.foreground", Color.white);
                        JOptionPane.showMessageDialog( null, "Item No already exists");
                    }
                    else{
                       if (res.next()){
                           UIManager.put("Button.background", Color.BLACK);
                           UIManager.put("Button.foreground", Color.white);
                           JOptionPane.showMessageDialog(null, "Item already exists");
                       }
                       else {
                           UIManager.put("Button.background", Color.BLACK);
                           UIManager.put("Button.foreground", Color.white);
                           conn.createStatement().execute(query);
                           JOptionPane.showMessageDialog(null, "Successfully inputted...");
                           this.setVisible(false);
                           new Restaurant();
                       }
                    }


                }
                else{
                    UIManager.put("Button.background", Color.BLACK);
                    UIManager.put("Button.foreground", Color.white);
                    JOptionPane.showMessageDialog(null, "Fill in the fields!");
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

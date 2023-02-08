package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookFood extends JFrame implements ActionListener, WindowListener {
    Connection conn;
    JButton back, order;
    JLabel room, item;
    JTextField roomF, itemF, priceF;
    Font font;
    ImageIcon icon, new_icon, my_image;
    String p;
    Statement st;
    Image img;
    public BookFood(){
        loadSql();
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
        back.setToolTipText("Move Back");
        back.setBounds(1255, 1, 100, 30);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setFont(new Font("Tahoma", Font.BOLD, 12));
        back.setFocusable(false);
        back.addActionListener(this);
        add(back);

        room = new JLabel("Room No: ");
        room.setForeground(Color.WHITE);
        room.setBounds(500, 200, 170, 30);
        room.setFont(font);
        add(room);

        roomF = new JTextField();
        roomF.setBounds(650,  200,  300,  30);
        roomF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    roomF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    roomF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    roomF.setText("");
                }
            }
        });
        add(roomF);

        item = new JLabel("Item Name: ");
        item.setForeground(Color.WHITE);
        item.setBounds(500, 290, 170, 30);
        item.setFont(font);
        add(item);

        itemF = new JTextField();
        itemF.setBounds(650, 290, 300, 30);
        itemF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    itemF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch)  || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4 )) {
                    itemF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        add(itemF);

        JLabel priceL = new JLabel("PRICE: ");
        priceL.setForeground(Color.WHITE);
        priceL.setBounds(500, 380, 150, 20);
        priceL.setFont(font);
        add(priceL);

        priceF = new JTextField();
        priceF.setBounds(650,  380,  300,  30);
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

        p = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\bell.png";
        icon = new ImageIcon(p);
        img = icon.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT);
        new_icon = new ImageIcon(img);


        order = new JButton(new_icon);
        order.setText("ORDER FOOD");
        order.setToolTipText("Order items from the restaurant");
        order.setBounds(590, 470, 200, 70);
        order.setBackground(Color.WHITE);
        order.setForeground(Color.BLACK);
        order.setFont(new Font("Tahoma", Font.BOLD, 15));
        order.setFocusable(false);
        order.addActionListener(this);
        order.setBackground(Color.pink);
        add(order);


        getContentPane().setBackground(new Color(102, 7, 8));
        setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args){
        new BookFood();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == order) {
            try {
                String room = roomF.getText();
                String item = itemF.getText();
                String price = priceF.getText();


                String dep = "SELECT * FROM RestItem WHERE itemPrice = '" + price + "' AND itemName = '" + item + "'";
                String q = "CREATE TABLE IF NOT EXISTS BookFood(roomNo int, itemName char(30))";
                String query3 = "SELECT * FROM room WHERE room_no = '" + room + "' AND availability = 'occupied'";
                String query1 = "SELECT * FROM room WHERE room_no ='" + room + "'";
                String query2 = "SELECT * FROM RestItem WHERE itemName ='" + item + "'";
                String query = "INSERT INTO BookFood VALUES ('" + room + "', " + "'" + item + "');";
                if (!(room.isBlank() || item.isBlank())) {

                    st = conn.createStatement();
                    st.executeUpdate(q);
                    ResultSet result = conn.createStatement().executeQuery(query1);
                    if (result.next()) {
                        ResultSet rs = conn.createStatement().executeQuery(query3);
                        if(rs.next()){
                            ResultSet rss = conn.createStatement().executeQuery(query2);
                            if(rss.next()){
                                ResultSet rr = conn.createStatement().executeQuery(dep);
                                if (rr.next()){
                                    conn.createStatement().execute(query);
                                    JOptionPane.showMessageDialog(null, "Food Booked...");
                                    this.setVisible(false);
                                    new Restaurant();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Wrong price for item: " + item);
                                }


                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Item does not exist!");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Room isn't available!");
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Room doesn't exist!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fill in the Fields");
                }
            }
            catch (Exception ae) {
                System.out.println(ae);
            }
        }

        else if(e.getSource()==back){
            new Restaurant();
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

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Window Opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window Closing");
        dispose();
        new Restaurant();
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

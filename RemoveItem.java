package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RemoveItem extends JFrame implements ActionListener, WindowListener {
    Connection conn;
    Font font;
    ImageIcon my_image, icon, new_icon;
    Statement st;
    JButton back, remB;
    JLabel item;
    JTextField itemF;
    String p;
    Image img;

    public RemoveItem(){
        loadSql();
        setResizable(false);
        font = new Font("Tahoma", Font.BOLD, 20);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

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

        item = new JLabel("Item Name: ");
        item.setForeground(Color.WHITE);
        item.setBounds(500, 290, 170, 30);
        item.setFont(font);
        add(item);

        itemF = new JTextField();
        itemF.setBounds(650, 290, 300, 30);
        itemF.addKeyListener(new KeyAdapter() {
            String value = itemF.getText();
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    itemF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4)) {
                    itemF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only and _ !");
                }

            }
        });
        add(itemF);

        p = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\bin.png";
        icon = new ImageIcon(p);
        img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        new_icon = new ImageIcon(img);
        remB = new JButton(new_icon);
        remB.setText("REMOVE ITEM");
        remB.setToolTipText("Go Back");
        remB.setBounds(590, 390, 200, 70);
        remB.setBackground(Color.WHITE);
        remB.setForeground(Color.BLACK);
        remB.setFont(new Font("Tahoma", Font.BOLD, 15));
        remB.setFocusable(false);
        remB.addActionListener(this);
        remB.setBackground(Color.pink);
        add(remB);


        getContentPane().setBackground(new Color(102, 7, 8));
        setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args){
        new RemoveItem();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==remB){
            try{
              String item = itemF.getText();
              String query = "SELECT * FROM RestItem WHERE itemName ='" + item + "'";
              String query1 = "DELETE FROM RestItem WHERE itemName ='" + item+ "'";

              if (!item.isBlank()) {
                  ResultSet result = conn.createStatement().executeQuery(query);
                  if(result.next()){
                      st = conn.createStatement();
                      st.executeUpdate(query1);
                      JOptionPane.showMessageDialog(null, "Item Removed");
                      this.setVisible(false);
                      new Restaurant();
                  }
                  else{
                      JOptionPane.showMessageDialog(null, "Item doesn't exist!");
                  }
              }
              else{
                  JOptionPane.showMessageDialog(null, "Fill in the fields!");
              }
            }
            catch (Exception ae) {
                System.out.println(ae);
            }
        }
        else if(e.getSource()==back){
            new Dashboard();
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

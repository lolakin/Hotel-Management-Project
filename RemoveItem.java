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
import java.sql.Statement;

public class RemoveItem extends JFrame implements ActionListener {
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
                else if(!(Character.isAlphabetic(ch) || ke.getKeyChar() == '_' || ke.getKeyCode() == KeyEvent.VK_SHIFT || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT)) {
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
        setLayout(null);
        setVisible(true);
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
}

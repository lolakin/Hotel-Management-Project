package HotelManagementJavaProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckOut extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JComboBox<String> rooms;
    JButton submit, back;
    Connection conn;

    public CheckOut() {
        loadSql();
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.BOLD, 25));
        UIManager.put("OptionPane.background",new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.background", Color.white);

        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(520, 250, 500, 300);

        JLabel heading = new JLabel("CHECK OUT");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(125, 5, 500, 50);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\checkout.png";

        // IMAGE PATH AND LABEL
        ImageIcon checkout_image = new ImageIcon(path);

        JLabel checkout_image_label = new  JLabel(checkout_image);

        checkout_image_label.setBounds(150,  10,  400,  300);
        add(checkout_image_label);

        JLabel roomL = new JLabel("Occupied Room: ");
        roomL.setForeground(Color.WHITE);
        roomL.setBounds(20, 75, 150, 20);
        roomL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(roomL);

        ArrayList <String> rooms_list = new ArrayList<>();
        try {
            String query = "SELECT * FROM room WHERE availability = 'occupied'";
            ResultSet result = conn.createStatement().executeQuery(query);
            while (result.next()) {
                rooms_list.add(result.getString("room_no"));
            }
        }
        catch(Exception e) {
            System.out.println(e);

        }
        String[] available_rooms = rooms_list.toArray(new String[rooms_list.size()]);
        rooms = new JComboBox<>(available_rooms);
        String room_no = (String) rooms.getSelectedItem();
        rooms.setBackground(Color.WHITE);
        rooms.setBounds(150, 75, 100, 20);
        add(rooms);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setToolTipText("Check Out...");
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(70, 125, 115, 30);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setToolTipText("Back to dashboard...");
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(70, 175, 115, 30);
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
        new CheckOut();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String room_no = (String) rooms.getSelectedItem();

            if (room_no == null) {
                String message = "No Room Selected !!!";
                JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
            }

            else {
                try {
                    String query1 = "UPDATE room SET availability = 'available' WHERE room_no = '" + room_no + "'";
                    String query2 = "UPDATE customer SET check_status = 'check_out' WHERE room_no = '" + room_no + "' AND check_status = 'check_in'";
                    String query3 = "UPDATE room SET cleaning_status = 'occupied' WHERE room_no = '" + room_no + "'";
                    String q = "DELETE FROM customer WHERE room_no = '" + room_no + "'";
                    conn.createStatement().execute(query1);
                    conn.createStatement().execute(query2);
                    conn.createStatement().execute(query3);

                    String message = "Room no " + room_no + " is now available !!!";
                    JOptionPane.showMessageDialog(null, message);
                    Statement stmt = conn.createStatement();
                    stmt.execute(q);
                    JOptionPane.showMessageDialog(null, "You have checked out...");
                    this.setVisible(false);
                    new Reception();
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

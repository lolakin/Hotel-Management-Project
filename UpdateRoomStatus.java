package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class UpdateRoomStatus extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JTextField cleaning_statusF;
    JComboBox rooms;
    JButton check, back, submit;
    Connection conn;

    public UpdateRoomStatus() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";

        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(425, 160, 700, 500);

        JLabel heading = new JLabel("Update Room Status");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(125, 5, 500, 50);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\updateroom.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon update_room_image = new ImageIcon(path);
        Image img = update_room_image.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);

        ImageIcon new_update_room_image = new ImageIcon(img);

        JLabel update_room_image_label = new  JLabel(new_update_room_image);

        update_room_image_label.setBounds(260,  75,  400,  300);
        add(update_room_image_label);

        JLabel roomL = new JLabel("Dirty Room: ");
        roomL.setForeground(Color.WHITE);
        roomL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roomL.setBounds(20, 150, 150, 20);
        add(roomL);

        ArrayList <String> rooms_list = new ArrayList<>();
        try {
            String query = "SELECT * FROM room WHERE availability = 'available' AND cleaning_status = 'occupied'";
            ResultSet result = conn.createStatement().executeQuery(query);
            while (result.next()) {
                rooms_list.add(result.getString("room_no"));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        //converting available rooms array list to an array
        String[] available_rooms = rooms_list.toArray(new String[rooms_list.size()]);
        rooms = new JComboBox<>(available_rooms);
        String room_no = (String) rooms.getSelectedItem();
        rooms.setBackground(Color.WHITE);
        rooms.setBounds(140, 150, 100, 20);
        add(rooms);

        // cleaning status label
        JLabel cleaning_status = new JLabel("Cleaning Status: ");
        cleaning_status.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cleaning_status.setForeground(Color.WHITE);
        cleaning_status.setBounds(20, 190, 120, 20);
        add(cleaning_status);

        // cleaning status field
        cleaning_statusF = new JTextField();
        cleaning_statusF.setBounds(140,  190,  100,  20);
        add(cleaning_statusF);

        check = new JButton("CHECK");
        check.setForeground(Color.WHITE);
        check.setBackground(new Color(66, 34, 130));
        check.setFont(new Font("times new roman", Font.PLAIN, 20));
        check.addActionListener(this);
        check.setBounds(75, 250, 115, 30);
        check.setFocusable(false);
        add(check);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setFocusable(false);
        submit.setBounds(210, 400, 115, 30);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setFocusable(false);
        back.setBounds(340, 400, 115, 30);
        add(back);


        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new UpdateRoomStatus();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == check) {
            try {
                String room_no = (String) rooms.getSelectedItem();
                String query = "SELECT * FROM room WHERE room_no = '" + room_no + "'";
                ResultSet result = conn.createStatement().executeQuery(query);
                while(result.next()) {
                    cleaning_statusF.setText(result.getString("cleaning_status"));
                }
            }
            catch(Exception ae) {
                System.out.println(ae);
            }
        }

        else if(e.getSource() == submit) {
            String room_no = (String) rooms.getSelectedItem();
            String cleaning_status = cleaning_statusF.getText();

            if (room_no == null) {
                String message = "No Room Selected !!!";
                JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    String query = "UPDATE room SET cleaning_status = '" + cleaning_status + "' WHERE room_no = '" + room_no + "'";
                    conn.createStatement().execute(query);

                    String message = "Changed clean status of room " + room_no + " to " + cleaning_status+ "!!!";
                    JOptionPane.showMessageDialog(null, message);
                    this.setVisible(false);
                }

                catch(Exception ae) {
                    System.out.println(ae);
                }
            }
        }

        else if(e.getSource() == back) {
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

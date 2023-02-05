package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class SearchRoom extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JButton check, back;
    ButtonGroup bed_typeG;
    JTable rooms_table;
    JCheckBox available;
    JRadioButton double_bed, single_bed;
    Connection conn;

    public SearchRoom() {
        loadSql();
        setResizable(false);
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(275, 125, 1000, 600);

        JLabel heading = new JLabel("SEARCH ROOM");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("monospaced", Font.BOLD, 40));
        heading.setBounds(345, 15, 300, 35);
        add(heading);

        JLabel bedTypeL = new JLabel("Bed Type: ");
        bedTypeL.setForeground(Color.WHITE);
        bedTypeL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        bedTypeL.setBounds(275, 75, 150, 20);
        add(bedTypeL);

        single_bed = new JRadioButton("Single Bed");
        single_bed.setActionCommand("single");
        single_bed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        single_bed.setBackground(new Color(32, 32, 32));
        single_bed.setForeground(Color.WHITE);
        single_bed.setBounds(360, 75,  100, 20);
        add(single_bed);

        double_bed = new JRadioButton("Double Bed");
        double_bed.setActionCommand("double");
        double_bed.setFont(new Font("Tahoma", Font.PLAIN, 14));
        double_bed.setBackground(new Color(32, 32, 32));
        double_bed.setForeground(Color.WHITE);
        double_bed.setBounds(360, 100,  100, 20);
        add(double_bed);

        bed_typeG = new ButtonGroup();
        bed_typeG.add(single_bed);
        bed_typeG.add(double_bed);

        available = new JCheckBox("only available");
        available.setBackground(new Color(32, 32, 32));
        available.setForeground(Color.WHITE);
        available.setFont(new Font("arial", Font.PLAIN, 15));
        available.setBounds(550, 75, 125, 20);
        add(available);

        JLabel room_no = new JLabel("ROOM NO");
        room_no.setForeground(Color.WHITE);
        room_no.setFont(new Font("Tahoma", Font.BOLD, 15));
        room_no.setBounds(65, 140, 150, 20);
        add(room_no);

        JLabel availability_status = new JLabel("AVAILABILITY");
        availability_status.setForeground(Color.WHITE);
        availability_status.setFont(new Font("Tahoma", Font.BOLD, 15));
        availability_status.setBounds(235, 140, 150, 20);
        add(availability_status);

        JLabel cleaning_status = new JLabel("CLEANING");
        cleaning_status.setForeground(Color.WHITE);
        cleaning_status.setFont(new Font("Tahoma", Font.BOLD, 15));
        cleaning_status.setBounds(450, 140, 150, 20);
        add(cleaning_status);

        JLabel bed_type = new JLabel("BED TYPE");
        bed_type.setForeground(Color.WHITE);
        bed_type.setFont(new Font("Tahoma", Font.BOLD, 15));
        bed_type.setBounds(660, 140, 150, 20);
        add(bed_type);

        JLabel price = new JLabel("PRICE");
        price.setForeground(Color.WHITE);
        price.setFont(new Font("Tahoma", Font.BOLD, 15));
        price.setBounds(860, 140, 150, 20);
        add(price);

        rooms_table = new JTable();
        rooms_table.setBackground(new Color(32, 32, 32));
        rooms_table.setForeground(Color.WHITE);
        rooms_table.setFont(new Font("arial", Font.PLAIN, 15));
        rooms_table.setRowHeight(20);
        rooms_table.setBounds(0, 175, 1000, 300);
        add(rooms_table);

        check = new JButton("CHECK");
        check.setForeground(Color.WHITE);
        check.setFocusable(false);
        check.setBackground(new Color(66, 34, 130));
        check.setFont(new Font("times new roman", Font.PLAIN, 20));
        check.addActionListener(this);
        check.setBounds(360, 500, 115, 30);
        add(check);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setFocusable(false);
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(490, 500, 115, 30);
        add(back);

        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== check) {
            try {
                String bed_type = bed_typeG.getSelection().getActionCommand();
                if (available.isSelected()) {
                    String query = "SELECT * FROM room WHERE availability = 'available' AND bed_type = '" + bed_type + "'";
                    ResultSet result = conn.createStatement().executeQuery(query);
                    rooms_table.setModel (DbUtils.resultSetToTableModel(result));
                }

                else if(single_bed.isSelected()){
                    String query = "SELECT * FROM room WHERE bed_type = '" + bed_type + "'";
                    ResultSet result = conn.createStatement().executeQuery(query);
                    rooms_table.setModel(DbUtils.resultSetToTableModel(result));
                }
                else if(double_bed.isSelected()){
                    String query = "SELECT * FROM room WHERE bed_type = '" + bed_type + "'";
                    ResultSet result = conn.createStatement().executeQuery(query);
                    rooms_table.setModel(DbUtils.resultSetToTableModel(result));
                }
            }
            catch(Exception ae) {
                System.out.println(ae);
            }
        }

        else if (e.getSource()== back) {
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

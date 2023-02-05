package HotelManagementJavaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.*;

import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Dashboard extends JFrame implements ActionListener, WindowListener {

    ImageIcon my_image;
    JPasswordField pf;

    public Dashboard() {
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        setResizable(false);
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar menu_bar = new JMenuBar();
        menu_bar.setBackground(new Color(23, 32, 42));

        UIManager.put("Menu.font", new Font("Verdana", Font.BOLD, 15));
//		menu_bar.setFont(new Font("Verdana", Font.BOLD, 15));
        setJMenuBar(menu_bar);


        JMenu menu1 = new JMenu("Hotel Management");
        menu1.setForeground(new Color(171, 235, 198));
        menu_bar.add(menu1);

        JMenuItem item1 = new JMenuItem("Reception");
        item1.addActionListener(this);
        menu1.add(item1);

        JMenu menu2 = new JMenu("Admin");
        menu2.setForeground(new Color(246, 221, 204));
        menu_bar.add(menu2);

        JMenuItem item2 = new JMenuItem("Add Employee");
        item2.addActionListener(this);
        menu2.add(item2);

        JMenuItem item3 = new JMenuItem("Add Room");
        item3.addActionListener(this);
        menu2.add(item3);

        JMenuItem item4 = new JMenuItem("Add Car");
        item4.addActionListener(this);
        menu2.add(item4);

        JMenuItem item5 = new JMenuItem("Customer Info");
        item5.addActionListener(this);
        menu2.add(item5);

        JMenuItem item6 = new JMenuItem("Employee Info");
        item6.addActionListener(this);
        menu2.add(item6);

        JMenuItem item7 = new JMenuItem("Pickup Info");
        item7.addActionListener(this);
        menu2.add(item7);

        JMenuItem item8 = new JMenuItem("Update Room Status");
        item8.addActionListener(this);
        menu2.add(item8);

        JMenuItem item9 = new JMenuItem("Add Item");
        item9.addActionListener(this);
        menu2.add(item9);

        JMenuItem item10 = new JMenuItem("Remove Item");
        item10.addActionListener(this);
        menu2.add(item10);

        JButton logout = new JButton("Log Out");
        logout.setForeground(Color.BLACK);
        logout.setBackground(Color.RED);
//        logout.setBackground(new Color(63, 10, 10));
        logout.addActionListener(this);
        logout.setFocusable(false);
        logout.setFont(new Font("verdana", Font.BOLD, 15));
        menu_bar.add(logout);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\dashboard.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon dashboard_image = new ImageIcon(path);
        Image img = dashboard_image.getImage().getScaledInstance(1550, 820, Image.SCALE_DEFAULT);

        ImageIcon new_dashboard_image = new ImageIcon(img);

        JLabel dashboard_image_label = new  JLabel(new_dashboard_image);

        dashboard_image_label.setBounds(0,  0,  1550,  820);
        add(dashboard_image_label);


        JLabel title = new JLabel("HOTEL ELITE");
        title.setBounds(420, 50, 1500, 200);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("times new roman", Font.BOLD, 70));
        dashboard_image_label.add(title);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(23, 32, 42));
        setDefaultCloseOperation(2);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new Dashboard();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Reception")) {
            new Reception();
            this.setVisible(false);

        }

        else if(e.getActionCommand().equals("Add Employee")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new AddEmployee();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }

        }
        else if(e.getActionCommand().equals("Add Item")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new AddItem();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }
        else if(e.getActionCommand().equals("Remove Item")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new RemoveItem();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }
        else if(e.getActionCommand().equals("Add Room")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new AddRoom();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }

        }
        else if(e.getActionCommand().equals("Add Car")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new AddCar();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }

        else if(e.getActionCommand().equals("Customer Info")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new CustomerInfo();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }

        else if(e.getActionCommand().equals("Employee Info")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new EmployeeInfo();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }

        else if(e.getActionCommand().equals("Pickup Info")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new PickUpInfo();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }

        else if(e.getActionCommand().equals("Update Room Status")) {
            pf = new JPasswordField();
            int psd = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if(psd == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if (password.equals("1234")) {
                    new UpdateRoomStatus();
                    this.setVisible(false);
                }
                else if(!password.equals("1234"))   {
                    JOptionPane.showMessageDialog(null, "Wrong password!!");
                }
            }
        }

        else if(e.getActionCommand().equals("Log Out")) {
            new Login();
            this.setVisible(false);
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
        new Login();
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

package HotelManagementJavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.util.ArrayList;

public class NewCustomer extends JFrame implements ActionListener, WindowListener {
    ImageIcon my_image;
    JTextField nameF, phoneF, countryF, depositF, checkStatus;
    JComboBox<String> id_C;
    JComboBox<String> room_allocated;
    JComboBox<String> countryC;
    JButton submit, back;
    ButtonGroup genderG;
    Connection conn;
    Statement st;
    String test;

    public NewCustomer() {
        loadSql();
        String path2 = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\hotel2.png";
        setResizable(false);
        my_image = new ImageIcon(path2);
        setIconImage(my_image.getImage());

        setBounds(425, 150, 700, 500);

        JLabel heading = new JLabel("New Customer");
        heading.setForeground(new Color(204, 246, 221));
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setBounds(190, 10, 290, 40);
        add(heading);

        String path = "C:\\Users\\lois7\\OneDrive\\Pictures\\Pins\\customer.jpg";

        // IMAGE PATH AND LABEL
        ImageIcon customer_image = new ImageIcon(path);
        Image img = customer_image.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT);

        ImageIcon new_customer_image = new ImageIcon(img);

        JLabel customer_image_label = new  JLabel(new_customer_image);
        customer_image_label.setBounds(400, 75, 250, 300);
        add(customer_image_label);

        JLabel nameL = new JLabel("Name: ");
        nameL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameL.setForeground(Color.WHITE);
        nameL.setBounds(50, 70, 60, 20);
        add(nameL);

        nameF = new JTextField();
        nameF.setBounds(160,  70,  200,  20);
        nameF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if(Character.isDigit(ch)) {
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    nameF.setEditable(true);
                }
                else if(!(Character.isAlphabetic(ch) ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4)) {
                    nameF.setEditable(true);
                    JOptionPane.showMessageDialog(null, "Enter Alphabets Only !");
                }
            }
        });
        add(nameF);

        JLabel phoneL = new JLabel("Phone: ");
        phoneL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phoneL.setForeground(Color.WHITE);
        phoneL.setBounds(50, 110, 70, 20);
        add(phoneL);

        phoneF = new JTextField();
        phoneF.setBounds(160,  110,  200,  20);
        phoneF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                String value = phoneF.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    phoneF.setEditable(true);
                }

                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    phoneF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    phoneF.setText("");
                }
            }
        });
        add(phoneF);

        JLabel genderL = new JLabel("Gender: ");
        genderL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        genderL.setForeground(Color.WHITE);
        genderL.setBounds(50, 150, 80, 20);
        add(genderL);

        JRadioButton male = new JRadioButton("Male");
        male.setActionCommand("male");
        male.setFont(new Font("Tahoma", Font.PLAIN, 14));
        male.setBackground(new Color(32, 32, 32));
        male.setForeground(Color.WHITE);
        male.setBounds(160, 150, 70, 20);
        add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setActionCommand("female");
        female.setFont(new Font("Tahoma", Font.PLAIN, 14));
        female.setBackground(new Color(32, 32, 32));
        female.setForeground(Color.WHITE);
        female.setBounds(160, 180, 80, 20);
        add(female);

        JRadioButton other = new JRadioButton("Other");
        other.setActionCommand("other");
        other.setFont(new Font("Tahoma", Font.PLAIN, 14));
        other.setBackground(new Color(32, 32, 32));
        other.setForeground(Color.WHITE);
        other.setBounds(160, 210, 80, 20);
        add(other);

        genderG = new ButtonGroup();
        genderG.add(male);
        genderG.add(female);
        genderG.add(other);


        JLabel idL = new JLabel("ID: ");
        idL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idL.setForeground(Color.WHITE);
        idL.setBounds(50, 250, 70, 20);
        add(idL);


        String[] id_list = {"NIN", "Passport", "Driving License", "Voter ID"};
        id_C = new JComboBox<>(id_list);

        String id = (String)id_C.getSelectedItem();
        id_C.setBackground(Color.WHITE);
        id_C.setBounds(160, 250, 200, 20);
        add(id_C);


        JLabel countryL = new JLabel("Country: ");
        countryL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        countryL.setForeground(Color.WHITE);
        countryL.setBounds(50, 290, 70, 20);
        add(countryL);

        String[] country_list = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
                "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Côte d'Ivoire", "Cabo Verde", "Cambodia",
                "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus",
                "Czechia (Czech Republic)", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
                "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica","Japan", "Jordan", "Kazakhstan", "Kenya",
                "Kiribati", "Kuwait", "Kyrgyzstan", "Latos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "	Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives",
                "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru",
                "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "	Palestine State", "Panama", "Papua New Guinea",
                "Paraguay", "Peru", "Phillipines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "	Saint Kitts and Nevis", "Saint Lucia", "aint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan",
                "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu",
                "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};
        countryC = new JComboBox<>(country_list);
        countryC.setEditable(true);
        countryC.setBackground(Color.WHITE);
        countryC.setBounds(160, 290, 200, 20);
        add(countryC);

        JLabel room_allocatedL = new JLabel("Allocate Room: ");
        room_allocatedL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        room_allocatedL.setForeground(Color.WHITE);
        room_allocatedL.setBounds(50, 330, 110, 20);
        add(room_allocatedL);

        ArrayList <String> available_rooms_list = new ArrayList<>();
        try {
            String query = "SELECT * FROM room WHERE availability = 'available' ORDER BY room_no ASC";
            ResultSet result = conn.createStatement().executeQuery(query);

            while (result.next()) {
                available_rooms_list.add(result.getString("room_no"));

            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        String[] available_rooms = available_rooms_list.toArray(new String[available_rooms_list.size()]);
        room_allocated = new JComboBox<>(available_rooms);
        String room_no = (String) room_allocated.getSelectedItem();
        room_allocated.setBackground(Color.WHITE);
        room_allocated.setBounds(160, 330, 200, 20);
        add(room_allocated);

        JLabel depositL = new JLabel("Deposit: ");
        depositL.setFont(new Font("Tahoma", Font.PLAIN, 15));
        depositL.setForeground(Color.WHITE);
        depositL.setBounds(50, 370, 70, 20);
        add(depositL);

        depositF = new JTextField();
        depositF.setBounds(160, 370, 200, 20);
        depositF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    depositF.setEditable(true);
                }
                else if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE ||  ke.getKeyCode() == KeyEvent.VK_SPACE
                        || ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK || ke.getKeyCode() == KeyEvent.VK_DOWN
                        || ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_LEFT
                        || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_SHIFT
                        || ke.getKeyCode() == KeyEvent.VK_ALT || ke.getKeyCode() == KeyEvent.VK_F4){
                    depositF.setEditable(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)");
                    depositF.setText("");
                }
            }
        });
        add(depositF);

        submit = new JButton("SUBMIT");
        submit.setForeground(Color.WHITE);
        submit.setToolTipText("Submit form");
        submit.setBackground(new Color(66, 34, 130));
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(210, 410, 115, 30);
        add(submit);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setToolTipText("Move Back");
        back.setBackground(new Color(66, 34, 130));
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(340, 410, 115, 30);
        add(back);

        getContentPane().setBackground(new Color(32, 32, 32));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        addWindowListener(this);

    }

    public static void main(String[] args) {new NewCustomer();}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String name = nameF.getText().toUpperCase();
            String phone = phoneF.getText().toUpperCase();
            String gender = genderG.getSelection().getActionCommand();
            String id = (String) id_C.getSelectedItem();
            String country = (String)countryC.getSelectedItem();
            String room_no = (String) room_allocated.getSelectedItem();
            String deposit = depositF.getText().toUpperCase();

            Pattern pt = Pattern.compile("(0|\\+2340?)[789][01][0-9]{8}");
            Matcher mt = pt.matcher(phone);

            if (mt.matches()){
                try {
                    String q = "CREATE TABLE IF NOT EXISTS customer(name varchar(20), phone varchar(11), gender char(6), id varchar(20), country char(30), room_no varchar(3), deposit decimal, check_status char(15))";
                    String query1 = "SELECT * FROM CUSTOMER WHERE phone = '" + phone + "'" ;
                    String query = "INSERT INTO CUSTOMER VALUES ('" + name + "', " + "'" + phone + "', " + "'" +
                            gender + "', " + "'" + id + "', " + "'" + country + "', " + "'" + room_no + "', " + "'" + deposit + "','check_in')";
                    String query3 = "UPDATE room SET availability = 'occupied' WHERE room_no = '" + room_no + "'";
                    conn.createStatement().execute(query3);
                    String queryy = "SELECT * FROM CUSTOMER WHERE room_no = '" + room_no + "'" ;
                    String dep = "SELECT * FROM Room WHERE price = '" + deposit + "' AND room_no = '" + room_no + "'";

                    if(!(name.isBlank() || phone.isBlank() || gender.isBlank() || Objects.requireNonNull(id).isBlank() || Objects.requireNonNull(country).isBlank()
                            || Objects.requireNonNull(room_no).isBlank() || deposit.isBlank() )) {
                        st = conn.createStatement();
                        st.executeUpdate(q);
                        ResultSet rr = conn.createStatement().executeQuery(dep);
                        ResultSet res = conn.createStatement().executeQuery(query1);
                        ResultSet r = conn.createStatement().executeQuery(queryy);

                        if(rr.next()){
                            conn.createStatement().execute(query);
                            String message = "New Customer Added in Room no " + room_no + " !!!";
                            JOptionPane.showMessageDialog(null, message);
                            this.setVisible(false);
                            new Reception();
                        }
                        else if (res.next()) {
                            JOptionPane.showMessageDialog( null, "Phone Number or Id is already taken");
                        }
                        else if (r.next()){
                            JOptionPane.showMessageDialog(null, "Room is booked!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Wrong price for room " + room_no);
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Registration failed!!!");
                    }
                }

                catch(Exception ae) {
//                    JOptionPane.showMessageDialog(null, "Fill in the fields!!!");
//                    JDialog d = new JDialog((Dialog) null, "Error");
                    System.out.println(ae);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Phone Number is not valid!");
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

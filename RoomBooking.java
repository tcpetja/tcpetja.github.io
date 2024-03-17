package com.resortbookingapp;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

public class RoomBooking extends JFrame {

	private JPanel contentPane;
	private JTextField txtFullName;
	private JTextField txtSurname;
	private JTextField txtContactNo;
	private JTextField txtRoomNo;
	private JSpinner spnAdults;
	private JSpinner spnKids;
	private Connection con;
	private Statement statement;
	private JDateChooser dtCheckIn;
	private JRadioButton rbtBachelor;
	private JRadioButton rbtChalet;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private int mouseX, mouseY;
	private JTextField txtBookingList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomBooking frame = new RoomBooking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getRoomNo() throws ClassNotFoundException, SQLException {
		String roomOption = null;
		
		if (this.rbtBachelor.isSelected()) {
            roomOption = this.rbtBachelor.getText();
        } else if (rbtChalet.isSelected()) {
            roomOption = rbtChalet.getText();
        }
		
		CheckIn checkIn = new CheckIn();
		int availRoomNo = checkIn.searchAvailableRoom(roomOption);
		txtRoomNo.setText(Integer.toString(availRoomNo));
	}
	
	public void addRecord() throws ClassNotFoundException, SQLException {
		String fullName,surname,cellNumber,adultsStringValue,kidsStringValue;
		int visitorCount,roomNo = 0,adultsValue,kidsValue;
		java.util.Date checkIn;
		
		roomNo= Integer.parseInt(txtRoomNo.getText());
	 	fullName = txtFullName.getText();
		surname = txtSurname.getText();
		cellNumber = txtContactNo.getText();
		adultsStringValue = spnAdults.getValue().toString();
		adultsValue = Integer.parseInt(adultsStringValue);
		kidsStringValue = spnKids.getValue().toString();
		kidsValue = Integer.parseInt(kidsStringValue);
		visitorCount= adultsValue+kidsValue;
		checkIn = dtCheckIn.getDate();
		
		
		String url = "jdbc:mysql://localhost:3306/sedimadatabase";
		String username = "root";
		String password = "Clement02";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url, username, password);
		
		
		// Format the date to your desired format, e.g., "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDatechkIn = dateFormat.format(checkIn);
        
        
        // Add the data to the database (use an INSERT statement)
        try {
            String insertQuery = "INSERT INTO tblbookings (room_No, full_Name, surname,cellNumber, visitor_Count,expCheck_In) " +
                               "VALUES ('" + roomNo + "', '" + fullName + "', '" + surname + "',  '" + cellNumber + "', '" + visitorCount + "','" + formattedDatechkIn + "')";
           
            
            
            //boolean isAvailable =  false;
           // String chngAvailQuery = "UPDATE tblrooms SET is_Available = " + isAvailable + " WHERE room_No = " + roomNo;
           // String updateRoomsDates = "UPDATE tblrooms SET check_In ='"+formattedDatechkIn+"',check_out ='"+formattedDatechkOut+ "' WHERE room_No = " + roomNo;
            
            statement = con.createStatement();
            statement.executeUpdate(insertQuery);
            //statement.executeUpdate(chngAvailQuery);
            //statement.executeUpdate(updateRoomsDates);
            JOptionPane.showMessageDialog(this,"Customer Booked in successfully", "Book In Status", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        
	}

	/**
	 * Create the frame.
	 */
	public RoomBooking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(205, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create a panel as a draggable area
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(0, 0, 0, 0));
        titleBar.setBounds(0, 0, 1145, 30); // Adjust according to your design
        contentPane.add(titleBar);
        
        // Add MouseListener for handling dragging
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        titleBar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = getLocation().x + e.getX() - mouseX;
                int newY = getLocation().y + e.getY() - mouseY;
                setLocation(newX, newY);
            }
        });
		
		JLabel lblKids = new JLabel("Kids");
		lblKids.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblKids.setBounds(315, 457, 81, 29);
		contentPane.add(lblKids);
		
		JLabel lblNewLabel_1_5 = new JLabel("Adults");
		lblNewLabel_1_5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(315, 413, 81, 29);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblCellno = new JLabel("Contact Number");
		lblCellno.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCellno.setBounds(315, 350, 116, 29);
		contentPane.add(lblCellno);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSurname.setBounds(315, 311, 81, 29);
		contentPane.add(lblSurname);
		
		JLabel lblNames = new JLabel("Full Name(s)");
		lblNames.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNames.setBounds(315, 272, 92, 29);
		contentPane.add(lblNames);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX.setBounds(1145, 0, 55, 48);
		contentPane.add(lblX);
		
		JLabel resortLogo = new JLabel("");
		resortLogo.setHorizontalAlignment(SwingConstants.CENTER);

		// Load the original image
		ImageIcon originalIcon = new ImageIcon(ResortLogin.class.getResource("/SedimaLogo.png"));
		Image originalImageL = originalIcon.getImage();

		// Resize the image to fit the JLabel dimensions
		Image resizedImageL = originalImageL.getScaledInstance(225, 225, Image.SCALE_SMOOTH);

		// Create a new ImageIcon with the resized image
		ImageIcon resizedIconp = new ImageIcon(resizedImageL);

		// Set the resized icon to the JLabel
		resortLogo.setIcon(resizedIconp);

		resortLogo.setBounds(34, 0, 174, 143);
		contentPane.add(resortLogo);
		
		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtFullName.setColumns(10);
		txtFullName.setBounds(460, 275, 191, 22);
		contentPane.add(txtFullName);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(460, 314, 191, 22);
		contentPane.add(txtSurname);
		
		txtContactNo = new JTextField();
		txtContactNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtContactNo.setColumns(10);
		txtContactNo.setBounds(460, 353, 191, 22);
		contentPane.add(txtContactNo);
		
		JSpinner spnKids = new JSpinner();
		spnKids.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		spnKids.setBounds(460, 457, 40, 29);
		contentPane.add(spnKids);
		this.spnKids=spnKids;
		
		JSpinner spnAdults = new JSpinner();
		spnAdults.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		spnAdults.setBounds(460, 410, 40, 29);
		contentPane.add(spnAdults);
		this.spnAdults=spnAdults;
		
		JLabel lblSuitetype = new JLabel("Suite type");
		lblSuitetype.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSuitetype.setBounds(750, 283, 102, 39);
		contentPane.add(lblSuitetype);
		
		JLabel lblCheckInDate = new JLabel("Check In Date");
		lblCheckInDate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCheckInDate.setBounds(749, 342, 116, 29);
		contentPane.add(lblCheckInDate);
		
		JLabel lblRoomId = new JLabel("Room No:");
		lblRoomId.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblRoomId.setBounds(749, 413, 92, 29);
		contentPane.add(lblRoomId);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRoomNo.setEditable(false);
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(848, 414, 38, 27);
		contentPane.add(txtRoomNo);
		
		textField_8 = new JTextField();
		textField_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					addRecord();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textField_8.setText("Submit");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setForeground(Color.WHITE);
		textField_8.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_8.setBackground(new Color(100, 149, 237));
		textField_8.setBounds(762, 620, 125, 31);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("Clear");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setForeground(Color.WHITE);
		textField_9.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_9.setBackground(new Color(240, 128, 128));
		textField_9.setBounds(913, 620, 125, 31);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getRoomNo();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textField_10.setText("Search");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setForeground(Color.WHITE);
		textField_10.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_10.setBackground(new Color(100, 149, 237));
		textField_10.setBounds(896, 410, 125, 31);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("Personal info");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setForeground(Color.BLACK);
		textField_11.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		textField_11.setBackground(new Color(100, 149, 237));
		textField_11.setBounds(394, 187, 191, 71);
		contentPane.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn checkIn = new CheckIn();
				checkIn.setVisible(true);
				dispose();
			}
		});
		textField_12.setText("Check In");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setForeground(Color.WHITE);
		textField_12.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_12.setBackground(new Color(100, 149, 237));
		textField_12.setBounds(64, 219, 125, 31);
		contentPane.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout checkOut = new checkout();
				checkOut.setVisible(true);
				dispose();
			}
		});
		textField_13.setText("Check Out");
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setForeground(Color.WHITE);
		textField_13.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_13.setBackground(new Color(100, 149, 237));
		textField_13.setBounds(64, 294, 125, 31);
		contentPane.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setVisible(true);
				dispose();
			}
		});
		textField_14.setText("Room Booking");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setForeground(Color.WHITE);
		textField_14.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_14.setBackground(new Color(100, 149, 237));
		textField_14.setBounds(64, 361, 125, 31);
		contentPane.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelBooking cancelBooking = new CancelBooking();
				cancelBooking.setVisible(true);
				dispose();
			}
		});
		textField_15.setText("Cancel Booking");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setForeground(Color.WHITE);
		textField_15.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_15.setBackground(new Color(100, 149, 237));
		textField_15.setBounds(64, 432, 125, 31);
		contentPane.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rooms rooms = new Rooms();
				try {
					try {
						rooms.main(new String[] {});
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		textField_16.setText("Rooms ");
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setForeground(Color.WHITE);
		textField_16.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_16.setBackground(new Color(100, 149, 237));
		textField_16.setBounds(64, 504, 125, 31);
		contentPane.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Guests guests= new Guests();
				try {
					guests.main(new String[] {});
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		textField_17.setText("Guests");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setForeground(Color.WHITE);
		textField_17.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_17.setBackground(new Color(100, 149, 237));
		textField_17.setBounds(64, 564, 125, 31);
		contentPane.add(textField_17);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(248, 162, 16, 538);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(0, 146, 1200, 16);
		contentPane.add(textPane_1);
		
		textField_18 = new JTextField();
		textField_18.setText("Room Data");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setForeground(Color.BLACK);
		textField_18.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		textField_18.setBackground(new Color(100, 149, 237));
		textField_18.setBounds(778, 187, 191, 71);
		contentPane.add(textField_18);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.WHITE);
		textPane_2.setBounds(581, 219, 102, 10);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.WHITE);
		textPane_3.setBounds(296, 219, 100, 10);
		contentPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.WHITE);
		textPane_4.setBounds(296, 219, 7, 350);
		contentPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBackground(Color.WHITE);
		textPane_5.setBounds(676, 219, 7, 350);
		contentPane.add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setEditable(false);
		textPane_6.setBackground(Color.WHITE);
		textPane_6.setBounds(296, 563, 387, 7);
		contentPane.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setEditable(false);
		textPane_7.setBounds(706, 219, 75, 10);
		contentPane.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setEditable(false);
		textPane_8.setBounds(968, 219, 100, 10);
		contentPane.add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setEditable(false);
		textPane_9.setBounds(706, 219, 7, 350);
		contentPane.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setEditable(false);
		textPane_10.setBounds(1061, 219, 7, 350);
		contentPane.add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setEditable(false);
		textPane_11.setBounds(706, 563, 362, 7);
		contentPane.add(textPane_11);
		
		JLabel lblNewLabel = new JLabel("Booking Info");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		lblNewLabel.setBounds(467, 30, 488, 71);
		contentPane.add(lblNewLabel);
		
		JDateChooser dtCheckIn = new JDateChooser();
		dtCheckIn.setBounds(881, 345, 140, 22);
		contentPane.add(dtCheckIn);
		this.dtCheckIn = dtCheckIn;
		
		JRadioButton rbtChalet = new JRadioButton("Chalet");
		rbtChalet.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		rbtChalet.setBounds(881, 300, 140, 22);
		contentPane.add(rbtChalet);
		this.rbtChalet = rbtChalet;
		
		JRadioButton rbtBachelor = new JRadioButton("Bachelor");
		rbtBachelor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		rbtBachelor.setBounds(881, 278, 140, 22);
		contentPane.add(rbtBachelor);
		this.rbtBachelor = rbtBachelor;
		
		txtBookingList = new JTextField();
		txtBookingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				RoomBookings roomBookings = new RoomBookings();
				try {
					roomBookings.main(new String[] {});
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
			}
		});
		txtBookingList.setText("Booking list");
		txtBookingList.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookingList.setForeground(Color.WHITE);
		txtBookingList.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtBookingList.setEditable(false);
		txtBookingList.setColumns(10);
		txtBookingList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtBookingList.setBackground(new Color(100, 149, 237));
		txtBookingList.setBounds(64, 620, 125, 31);
		contentPane.add(txtBookingList);
		
		setUndecorated(true);
	}
}

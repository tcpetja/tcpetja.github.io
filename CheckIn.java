package com.resortbookingapp;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.ScrollPane;
import com.toedter.calendar.JDateChooser;

public class CheckIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtFullName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtCarReg;
	private JTextField txtContactNo;
	private JTextField txtRoomNo;
	private JTextField btnCheckIn;
	private JTextField btnClear;
	private JTextField btnSearch;
	private JTextField txtPersonalInfo;
	private JTextField txtCheckIn;
	private JTextField txtCheckOut;
	private JTextField txtRoomBooking;
	private JTextField txtCancelBooking;
	private JTextField txtRooms;
	private JTextField txtGuests;
	private JSpinner spnAdults;
	private JSpinner spnKids;
	private JDateChooser dtCheckOut;
	private JDateChooser dtCheckIn;
	private JRadioButton rbtBachelor;
	private JRadioButton rbtChalet;
	private Connection con;
	private Statement statement;
	private JTextPane textPane_1;
	private JTextField txtRoomData;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JTextPane textPane_4;
	private JTextPane textPane_5;
	private JTextPane textPane_6;
	private JTextPane textPane_7;
	private JTextPane textPane_8;
	private JTextPane textPane_9;
	private JTextPane textPane_10;
	private JTextPane textPane_11;
	private JLabel lblNewLabel;
	private int mouseX, mouseY;
	private JTextField txtBookingList;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIn frame = new CheckIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		//Guests guests = new Guests();
		//Database connection
		//String url = "jdbc:mysql://localhost:3306/sedimadatabase";
		//String username = "root";
		//String password = "Clement02";
		
		//Class.forName("com.mysql.cj.jdbc.Driver");
		
		//guests.con = DriverManager.getConnection(url, username, password);
		//System.out.println("Connection successful");
		
		
	}
	
	
	public int searchAvailableRoom(String selectedSuiteType) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://localhost:3306/sedimadatabase";
		String username = "root";
		String password = "Clement02";
		int roomNumber = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url, username, password);
		
		

		
		String searchQuery = "SELECT room_No FROM tblrooms WHERE suite_Type = '" + selectedSuiteType + "' AND is_Available = 1 LIMIT 1";
        // Add the data to the database (use an INSERT statement)
		try {
			statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(searchQuery);
            if (resultSet.next()) {
                roomNumber = resultSet.getInt("room_No");
            } else {
            	JOptionPane.showMessageDialog(this," No available rooms for the selected suite type", "Rooms not found", JOptionPane.WARNING_MESSAGE); // No available rooms for the selected suite type
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("The is an error with the database"); // Handle the error as needed
        }
		
		
		return roomNumber;
	}
	
	public boolean validateInput() {
		boolean isValid=true;
		
	    if (txtFullName.getText().isEmpty() ||
	        txtSurname.getText().isEmpty() ||
	        txtEmail.getText().isEmpty() ||
	        txtCarReg.getText().isEmpty() ||
	        txtContactNo.getText().isEmpty() ||
	        txtContactNo.getText().length() != 10 ||
	        (int)spnAdults.getValue() <0 ||
	        (!rbtBachelor.isSelected() && !rbtChalet.isSelected())) {

	        // Display error messages or handle validation failure here
	        if (txtFullName.getText().isEmpty()) {
	        	JOptionPane.showMessageDialog(this, "Please enter the name(s).", "Input Error", JOptionPane.ERROR_MESSAGE);
	            isValid = false;
	        }
	        if (txtSurname.getText().isEmpty()) {
	        	JOptionPane.showMessageDialog(this,"Please enter the surname.", "Input Error", JOptionPane.ERROR_MESSAGE);
	            isValid = false;

	        }
	        

	        if (txtContactNo.getText().isEmpty() || txtContactNo.getText().length() != 10) {
	            // Handle invalid ID number length or empty ID number field
	        	JOptionPane.showMessageDialog(this,"Ensure you have entered a Valid cell number which has only 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
	            isValid = false;

	            
	        }

	        if ((int)spnAdults.getValue() <= 0) {
	            // Handle zero or negative number of adults
	        	JOptionPane.showMessageDialog(this,"There should be atleast one adult present to proceed with booking.", "Input Error", JOptionPane.ERROR_MESSAGE);
	            isValid = false;

	        }

	        if (!rbtBachelor.isSelected() && !rbtChalet.isSelected()) {
	            // Handle no radio button selected
	        	JOptionPane.showMessageDialog(this,"Choose the type of room to be booked.", "Input Error", JOptionPane.ERROR_MESSAGE);
	            isValid = false;

	        }

	        return isValid; // Validation failed
	    }

	    return isValid; // Validation passed
	}

	
	
	public void addRecord() throws ClassNotFoundException, SQLException {
		String fullName,surname,emailAddress,carRegistration,cellNumber,adultsStringValue,kidsStringValue;
		int visitorCount,roomNo = 0,adultsValue,kidsValue;
		java.util.Date checkIn, checkOut;
		
		roomNo= Integer.parseInt(txtRoomNo.getText());
	 	fullName = txtFullName.getText();
		surname = txtSurname.getText();
		emailAddress = txtEmail.getText();
		carRegistration =txtCarReg.getText();
		cellNumber = txtContactNo.getText();
		adultsStringValue = spnAdults.getValue().toString();
		adultsValue = Integer.parseInt(adultsStringValue);
		kidsStringValue = spnKids.getValue().toString();
		kidsValue = Integer.parseInt(kidsStringValue);
		visitorCount= adultsValue+kidsValue;
		checkIn = dtCheckIn.getDate();
		checkOut = dtCheckOut.getDate();
		
		String url = "jdbc:mysql://127.0.0.1:3306/sedimadatabase";
		String username = "root";
		String password = "Sedim@01";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url, username, password);
		
		
		// Format the date to your desired format, e.g., "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDatechkIn = dateFormat.format(checkIn);
        String formattedDatechkOut = dateFormat.format(checkOut);
        
        // Add the data to the database (use an INSERT statement)
        try {
            String insertQuery = "INSERT INTO tblguests (room_No, full_Name, surname,email, Car_Registration,cellNumber, visitor_Count,check_In,check_out) " +
                               "VALUES ('" + roomNo + "', '" + fullName + "', '" + surname + "', '" + emailAddress + "', '"+ carRegistration + "', '" + cellNumber + "', '" + visitorCount + "','" + formattedDatechkIn + "','" + formattedDatechkOut + "')";
           
            
            
            boolean isAvailable =  false;
            String chngAvailQuery = "UPDATE tblrooms SET is_Available = " + isAvailable + " WHERE room_No = " + roomNo;
            String updateRoomsDates = "UPDATE tblrooms SET check_In ='"+formattedDatechkIn+"',check_out ='"+formattedDatechkOut+ "' WHERE room_No = " + roomNo;
            
            statement = con.createStatement();
            statement.executeUpdate(insertQuery);
            statement.executeUpdate(chngAvailQuery);
            statement.executeUpdate(updateRoomsDates);
            JOptionPane.showMessageDialog(this,"Customer cheked in successfully", "Check In Status", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        
	}
	public void clearFields() {
	    // Clear text fields that do not have mouseClicked event handlers
	        txtFullName.setText("");
	        txtSurname.setText("");
	        txtEmail.setText("");	   	
	        txtCarReg.setText("");
	        txtContactNo.setText("");
	        txtRoomNo.setText("");
	    
	    // Clear spinners
	        spnAdults.setValue(0);
	        spnKids.setValue(0);
	    
	    // Clear radio buttons
	        rbtBachelor.setSelected(false);	    
	        rbtChalet.setSelected(false);

	    // Clear date choosers
	        dtCheckIn.setDate(null);    
	        dtCheckOut.setDate(null);
	    
	}


	/**
	 * Create the frame.
	 */
	public CheckIn() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(205, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
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
		lblKids.setBounds(315, 487, 81, 29);
		contentPane.add(lblKids);
		
		JLabel lblNewLabel_1_5 = new JLabel("Adults");
		lblNewLabel_1_5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(315, 449, 81, 29);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblContactNo = new JLabel("Contact Number");
		lblContactNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblContactNo.setBounds(315, 407, 116, 29);
		contentPane.add(lblContactNo);
		
		JLabel lblCarRegistration = new JLabel("Car Registration");
		lblCarRegistration.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCarRegistration.setBounds(312, 378, 116, 29);
		contentPane.add(lblCarRegistration);
		
		JLabel lblEmail = new JLabel("Email Address");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(312, 345, 116, 29);
		contentPane.add(lblEmail);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSurname.setBounds(315, 311, 81, 29);
		contentPane.add(lblSurname);
		
		JLabel lblNames = new JLabel("Full Name(s)");
		lblNames.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNames.setBounds(315, 272, 92, 29);
		contentPane.add(lblNames);
		lblX.setBounds(1145, 0, 55, 48);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
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
		txtFullName.setBounds(460, 275, 191, 22);
		contentPane.add(txtFullName);
		txtFullName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(460, 314, 191, 22);
		contentPane.add(txtSurname);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(460, 353, 191, 22);
		contentPane.add(txtEmail);
		
		txtCarReg = new JTextField();
		txtCarReg.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCarReg.setColumns(10);
		txtCarReg.setBounds(460, 381, 191, 22);
		contentPane.add(txtCarReg);
		
		txtContactNo = new JTextField();
		txtContactNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtContactNo.setColumns(10);
		txtContactNo.setBounds(460, 410, 191, 22);
		contentPane.add(txtContactNo);
		
		JSpinner spnKids = new JSpinner();
		spnKids.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		spnKids.setBounds(460, 484, 40, 29);
		contentPane.add(spnKids);
		this.spnKids=spnKids;
		
		JSpinner spnAdults = new JSpinner();
		spnAdults.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		spnAdults.setBounds(460, 446, 40, 29);
		contentPane.add(spnAdults);
		this.spnAdults=spnAdults;
		
		JLabel lblSuitetype = new JLabel("Suite type");
		lblSuitetype.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSuitetype.setBounds(749, 272, 102, 29);
		contentPane.add(lblSuitetype);
		
		JLabel lblCheckInDate = new JLabel("Check In Date");
		lblCheckInDate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCheckInDate.setBounds(749, 342, 116, 29);
		contentPane.add(lblCheckInDate);
		
		JLabel lblCheckOutDate = new JLabel("Check Out Date");
		lblCheckOutDate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCheckOutDate.setBounds(749, 376, 125, 29);
		contentPane.add(lblCheckOutDate);
		
		JLabel lblRoomId = new JLabel("Room No:");
		lblRoomId.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblRoomId.setBounds(749, 413, 92, 29);
		contentPane.add(lblRoomId);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setEditable(false);
		txtRoomNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(848, 414, 38, 27);
		contentPane.add(txtRoomNo);
		
		JRadioButton rbtBachelor = new JRadioButton("Bachelor");
		rbtBachelor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		rbtBachelor.setBounds(881, 268, 140, 22);
		contentPane.add(rbtBachelor);
		this.rbtBachelor = rbtBachelor;
	    
		
		JRadioButton rbtChalet = new JRadioButton("Chalet");
		rbtChalet.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		rbtChalet.setBounds(881, 290, 140, 22);
		contentPane.add(rbtChalet);
		this.rbtChalet = rbtChalet;
		
		ButtonGroup group = new ButtonGroup();


        // Add radio buttons to the button group
        group.add(rbtBachelor);
        group.add(rbtChalet);
		
		btnCheckIn = new JTextField();
		btnCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean inputValidation=validateInput();
				
				if (inputValidation = false) {
				}
				else
				
				try {
					addRecord();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearFields();
			}
		});
		btnCheckIn.setText("Check In");
		btnCheckIn.setHorizontalAlignment(SwingConstants.CENTER);
		btnCheckIn.setForeground(Color.WHITE);
		btnCheckIn.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnCheckIn.setEditable(false);
		btnCheckIn.setColumns(10);
		btnCheckIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCheckIn.setBackground(new Color(100, 149, 237));
		btnCheckIn.setBounds(762, 620, 125, 31);
		contentPane.add(btnCheckIn);
		
		btnClear = new JTextField();
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}
		});
		btnClear.setText("Clear");
		btnClear.setHorizontalAlignment(SwingConstants.CENTER);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnClear.setEditable(false);
		btnClear.setColumns(10);
		btnClear.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setBackground(new Color(240, 128, 128));
		btnClear.setBounds(913, 620, 125, 31);
		contentPane.add(btnClear);
		
		btnSearch = new JTextField();
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String roomOption = null;
				
				if (rbtBachelor.isSelected()) {
		            roomOption = rbtBachelor.getText();
		        } else if (rbtChalet.isSelected()) {
		            roomOption = rbtChalet.getText();
		        }
				
				try {
					int roomNo = searchAvailableRoom(roomOption);
					txtRoomNo.setText(Integer.toString(roomNo));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSearch.setText("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.CENTER);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSearch.setEditable(false);
		btnSearch.setColumns(10);
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.setBackground(new Color(100, 149, 237));
		btnSearch.setBounds(896, 410, 125, 31);
		contentPane.add(btnSearch);
		
		txtPersonalInfo = new JTextField();
		txtPersonalInfo.setText("Personal info");
		txtPersonalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPersonalInfo.setForeground(new Color(0, 0, 0));
		txtPersonalInfo.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		txtPersonalInfo.setEditable(false);
		txtPersonalInfo.setColumns(10);
		txtPersonalInfo.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		txtPersonalInfo.setBackground(new Color(100, 149, 237));
		txtPersonalInfo.setBounds(394, 187, 191, 71);
		contentPane.add(txtPersonalInfo);
		
		txtCheckIn = new JTextField();
		txtCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn checkIn = new CheckIn();
				checkIn.setVisible(true);
				dispose();
			}
		});
		txtCheckIn.setText("Check In");
		txtCheckIn.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckIn.setForeground(Color.WHITE);
		txtCheckIn.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCheckIn.setEditable(false);
		txtCheckIn.setColumns(10);
		txtCheckIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCheckIn.setBackground(new Color(100, 149, 237));
		txtCheckIn.setBounds(64, 219, 125, 31);
		contentPane.add(txtCheckIn);
		
		txtCheckOut = new JTextField();
		txtCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout checkOut = new checkout();
				checkOut.setVisible(true);
				dispose();
			}
		});
		txtCheckOut.setText("Check Out");
		txtCheckOut.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckOut.setForeground(Color.WHITE);
		txtCheckOut.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCheckOut.setEditable(false);
		txtCheckOut.setColumns(10);
		txtCheckOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCheckOut.setBackground(new Color(100, 149, 237));
		txtCheckOut.setBounds(64, 294, 125, 31);
		contentPane.add(txtCheckOut);
		
		txtRoomBooking = new JTextField();
		txtRoomBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setVisible(true);
				dispose();
				
			}
		});
		txtRoomBooking.setText("Room Booking");
		txtRoomBooking.setHorizontalAlignment(SwingConstants.CENTER);
		txtRoomBooking.setForeground(Color.WHITE);
		txtRoomBooking.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRoomBooking.setEditable(false);
		txtRoomBooking.setColumns(10);
		txtRoomBooking.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRoomBooking.setBackground(new Color(100, 149, 237));
		txtRoomBooking.setBounds(64, 361, 125, 31);
		contentPane.add(txtRoomBooking);
		
		txtCancelBooking = new JTextField();
		txtCancelBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelBooking cancelBooking = new CancelBooking();
				cancelBooking.setVisible(true);
				dispose();
			}
		});
		txtCancelBooking.setText("Cancel Booking");
		txtCancelBooking.setHorizontalAlignment(SwingConstants.CENTER);
		txtCancelBooking.setForeground(Color.WHITE);
		txtCancelBooking.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCancelBooking.setEditable(false);
		txtCancelBooking.setColumns(10);
		txtCancelBooking.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCancelBooking.setBackground(new Color(100, 149, 237));
		txtCancelBooking.setBounds(64, 432, 125, 31);
		contentPane.add(txtCancelBooking);
		
		txtRooms = new JTextField();
		txtRooms.addMouseListener(new MouseAdapter() {
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
		txtRooms.setText("Rooms ");
		txtRooms.setHorizontalAlignment(SwingConstants.CENTER);
		txtRooms.setForeground(Color.WHITE);
		txtRooms.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRooms.setEditable(false);
		txtRooms.setColumns(10);
		txtRooms.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRooms.setBackground(new Color(100, 149, 237));
		txtRooms.setBounds(64, 504, 125, 31);
		contentPane.add(txtRooms);
		
		txtGuests = new JTextField();
		txtGuests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Guests guests= new Guests();
				//guests.setVisible(true);
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
		txtGuests.setText("Guests");
		txtGuests.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuests.setForeground(Color.WHITE);
		txtGuests.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtGuests.setEditable(false);
		txtGuests.setColumns(10);
		txtGuests.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtGuests.setBackground(new Color(100, 149, 237));
		txtGuests.setBounds(64, 564, 125, 31);
		contentPane.add(txtGuests);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(248, 162, 16, 538);
		contentPane.add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(0, 146, 1200, 16);
		contentPane.add(textPane_1);
		
		txtRoomData = new JTextField();
		txtRoomData.setText("Room Data");
		txtRoomData.setHorizontalAlignment(SwingConstants.CENTER);
		txtRoomData.setForeground(Color.BLACK);
		txtRoomData.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		txtRoomData.setEditable(false);
		txtRoomData.setColumns(10);
		txtRoomData.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		txtRoomData.setBackground(new Color(100, 149, 237));
		txtRoomData.setBounds(778, 187, 191, 71);
		contentPane.add(txtRoomData);
		
		textPane_2 = new JTextPane();
		textPane_2.setBackground(new Color(255, 255, 255));
		textPane_2.setEditable(false);
		textPane_2.setBounds(581, 219, 102, 10);
		contentPane.add(textPane_2);
		
		textPane_3 = new JTextPane();
		textPane_3.setBackground(new Color(255, 255, 255));
		textPane_3.setEditable(false);
		textPane_3.setBounds(296, 219, 100, 10);
		contentPane.add(textPane_3);
		
		textPane_4 = new JTextPane();
		textPane_4.setBackground(new Color(255, 255, 255));
		textPane_4.setEditable(false);
		textPane_4.setBounds(296, 219, 7, 350);
		contentPane.add(textPane_4);
		
		textPane_5 = new JTextPane();
		textPane_5.setBackground(new Color(255, 255, 255));
		textPane_5.setEditable(false);
		textPane_5.setBounds(676, 219, 7, 350);
		contentPane.add(textPane_5);
		
		textPane_6 = new JTextPane();
		textPane_6.setBackground(new Color(255, 255, 255));
		textPane_6.setEditable(false);
		textPane_6.setBounds(296, 563, 387, 7);
		contentPane.add(textPane_6);
		
		textPane_7 = new JTextPane();
		textPane_7.setEditable(false);
		textPane_7.setBounds(706, 219, 75, 10);
		contentPane.add(textPane_7);
		
		textPane_8 = new JTextPane();
		textPane_8.setEditable(false);
		textPane_8.setBounds(968, 219, 100, 10);
		contentPane.add(textPane_8);
		
		textPane_9 = new JTextPane();
		textPane_9.setEditable(false);
		textPane_9.setBounds(706, 219, 7, 350);
		contentPane.add(textPane_9);
		
		textPane_10 = new JTextPane();
		textPane_10.setEditable(false);
		textPane_10.setBounds(1061, 219, 7, 350);
		contentPane.add(textPane_10);
		
		textPane_11 = new JTextPane();
		textPane_11.setEditable(false);
		textPane_11.setBounds(706, 563, 362, 7);
		contentPane.add(textPane_11);
		
		lblNewLabel = new JLabel("Booking Info");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		lblNewLabel.setBounds(467, 30, 488, 71);
		contentPane.add(lblNewLabel);
		
		JDateChooser dtCheckIn = new JDateChooser();
		dtCheckIn.setBounds(881, 321, 140, 22);
		contentPane.add(dtCheckIn);
		this.dtCheckIn = dtCheckIn;
		
		JDateChooser dtCheckOut = new JDateChooser();
		dtCheckOut.setBounds(881, 378, 140, 22);
		contentPane.add(dtCheckOut);
		this.dtCheckOut = dtCheckOut;
		
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

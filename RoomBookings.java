package com.resortbookingapp;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RoomBookings extends JFrame {
	
	public static Connection con;
	public static Statement statement;
	public static ResultSet resultSet;
	public static DefaultTableModel model;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtRefresh;
	private static JTable table;
	private int mouseX, mouseY;
	private JTextField txtBookingList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws ClassNotFoundException, SQLException  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomBookings frame = new RoomBookings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	
	model = new DefaultTableModel();
	table = new JTable(model);
	
	String url = "jdbc:mysql://localhost:3306/sedimadatabase";
	String username = "root";
	String password = "Clement02";
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	con = DriverManager.getConnection(url, username, password);
	System.out.println("Connection successful");
	
	String query = "SELECT * FROM tblbookings";
	statement = con.createStatement();
	resultSet = statement.executeQuery(query);
	
	// Add data to the model from the ResultSet
			while (resultSet.next()) {
			    // Retrieve data from the ResultSet and add it to the model
			    String column1Data = resultSet.getString("room_No");
			    String column2Data = resultSet.getString("full_Name");
			    String column3Data = resultSet.getString("surname");
			    String column4Data = resultSet.getString("cellNumber");
			    String column5Data = resultSet.getString("visitor_Count");
			    String column6Data = resultSet.getString("expCheck_In");
			    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data});
			}
	refreshTable();		
			
			
	
}


	/**
	 * Create the frame.
	 */
	public RoomBookings() {
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
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn checkIn = new CheckIn();
				checkIn.setVisible(true);
				dispose();
			}
		});
		textField.setText("Check In");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField.setBackground(new Color(100, 149, 237));
		textField.setBounds(64, 219, 125, 31);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout checkOut = new checkout();
				checkOut.setVisible(true);
				dispose();
			}
		});
		textField_1.setText("Check Out");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_1.setBackground(new Color(100, 149, 237));
		textField_1.setBounds(64, 294, 125, 31);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setVisible(true);
				dispose();
			}
		});
		textField_2.setText("Room Booking");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_2.setBackground(new Color(100, 149, 237));
		textField_2.setBounds(64, 361, 125, 31);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelBooking cancelBooking = new CancelBooking();
				cancelBooking.setVisible(true);
				dispose();
			}
		});
		textField_3.setText("Cancel Booking");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_3.setBackground(new Color(100, 149, 237));
		textField_3.setBounds(64, 432, 125, 31);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
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
		textField_4.setText("Rooms ");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.WHITE);
		textField_4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_4.setBackground(new Color(100, 149, 237));
		textField_4.setBounds(64, 504, 125, 31);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("Guests");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_5.setBackground(new Color(100, 149, 237));
		textField_5.setBounds(64, 564, 125, 31);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel = new JLabel("Booking Info");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		lblNewLabel.setBounds(467, 30, 488, 71);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(0, 146, 1200, 16);
		contentPane.add(textPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(248, 162, 16, 538);
		contentPane.add(textPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 188, 886, 405);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setUndecorated(true);
		
		
		
		String[] columnNames = {"Room No", "Full Name", "Surname", "Contact Number", "Visitors", "Expected Check-In"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		
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
		
		txtRefresh = new JTextField();
		txtRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshTable();
			}
		});
		txtRefresh.setText("Refresh");
		txtRefresh.setHorizontalAlignment(SwingConstants.CENTER);
		txtRefresh.setForeground(Color.WHITE);
		txtRefresh.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRefresh.setEditable(false);
		txtRefresh.setColumns(10);
		txtRefresh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRefresh.setBackground(new Color(100, 149, 237));
		txtRefresh.setBounds(328, 620, 125, 31);
		contentPane.add(txtRefresh);
		
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
		
		
		
		
	}
	//Refresh table method
			private static void refreshTable() {
			    model.setRowCount(0); // Clear the existing table data

			    try {
			        resultSet = statement.executeQuery("SELECT * FROM tblbookings");
			        while (resultSet.next()) {
			            String[] rowData = {
			                resultSet.getString("room_No"),
			                resultSet.getString("full_Name"),
			                resultSet.getString("surname"),
			                
			                resultSet.getString("cellNumber"),
			                resultSet.getString("visitor_Count"),
			                resultSet.getString("expCheck_In")
			            };
			            model.addRow(rowData);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
}

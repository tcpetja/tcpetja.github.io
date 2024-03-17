package com.resortbookingapp;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;*/

public class Rooms extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private static JTable table;
	private int mouseX, mouseY;
	
	//DB Connection Variables
	private static Connection con;
	private static Statement statement;
	private static ResultSet resultSet;
	private static DefaultTableModel model;
	private JTextField textField_8;
	private JTextField txtBookingList;
	
	//static Connection connection ;
	//static String databaseName =  "sedimadatabase";
	//static String url = "jdbc:mysql://localhost:3306/"+ databaseName;
	
	//static String mySQLUsername = "root";
	//static String mySQLPassword = "Clement02";

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InstantiationException,IllegalAccessException,ClassNotFoundException,SQLException{
			
		EventQueue.invokeLater(() -> {
            try {
                Rooms frame = new Rooms();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
		
		String query = "SELECT * FROM tblrooms ORDER BY room_No ASC;";
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
		
		// Add data to the model from the ResultSet
				while (resultSet.next()) {
				    // Retrieve data from the ResultSet and add it to the model
				    String column1Data = resultSet.getString("room_No");
				    String column2Data = resultSet.getString("suite_Type");
				    String column3Data = resultSet.getString("check_In");
				    String column4Data = resultSet.getString("check_Out");
				    String column5Data = resultSet.getString("is_Available");
				    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data});
				}

        
	}
	
	/**
	 * Create the frame.
	 */
	public void openRooms() {
			
		}
	public Rooms() {
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
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn checkIn = new CheckIn();
				checkIn.setVisible(true);
				dispose();
			}
		});
		textField_2.setText("Check In");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_2.setBackground(new Color(100, 149, 237));
		textField_2.setBounds(64, 219, 125, 31);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout checkOut = new checkout();
				checkOut.setVisible(true);
				dispose();
			}
		});
		textField_3.setText("Check Out");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_3.setBackground(new Color(100, 149, 237));
		textField_3.setBounds(64, 294, 125, 31);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setVisible(true);
				dispose();
			}
		});
		textField_4.setText("Room Booking");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.WHITE);
		textField_4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_4.setBackground(new Color(100, 149, 237));
		textField_4.setBounds(64, 361, 125, 31);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelBooking cancelBooking = new CancelBooking();
				cancelBooking.setVisible(true);
				dispose();
			}
		});
		textField_5.setText("Cancel Booking");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_5.setBackground(new Color(100, 149, 237));
		textField_5.setBounds(64, 432, 125, 31);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openRooms();
			}
		});
		textField_6.setText("Rooms ");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setForeground(Color.WHITE);
		textField_6.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_6.setBackground(new Color(100, 149, 237));
		textField_6.setBounds(64, 504, 125, 31);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.addMouseListener(new MouseAdapter() {
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
		textField_7.setText("Guests");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setForeground(Color.WHITE);
		textField_7.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_7.setBackground(new Color(100, 149, 237));
		textField_7.setBounds(64, 564, 125, 31);
		contentPane.add(textField_7);
		
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
		
		
		String[] columnNames = {"Room No", "Suite Type", "Check-In", "Check-Out","Is Available"};
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
		
		textField_8 = new JTextField();
		textField_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshTable();
			}
		});
		textField_8.setText("Refresh");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setForeground(Color.WHITE);
		textField_8.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_8.setBackground(new Color(100, 149, 237));
		textField_8.setBounds(327, 620, 125, 31);
		contentPane.add(textField_8);
		
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
			        resultSet = statement.executeQuery("SELECT * FROM tblrooms ORDER BY room_No ASC;");
			        while (resultSet.next()) {
			            String[] rowData = {
			                resultSet.getString("room_No"),
			                resultSet.getString("suite_Type"),
			                resultSet.getString("check_In"),
			                resultSet.getString("check_Out"),
			                resultSet.getString("is_Available")
			                
			                
			            };
			            model.addRow(rowData);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
	}


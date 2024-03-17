package com.resortbookingapp;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
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

public class checkout extends JFrame {

	private JPanel contentPane;
	private JTextField txtRoomNo;
	private JTextField txtCheckOut;
	private JTextField txtClose;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField txtRoomBooking;
	private JTextField txtCancelBooking;
	private JTextField textField_16;
	private JTextField textField_17;
	private int mouseX, mouseY;
	private JTextField txtBookingList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkout frame = new checkout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public checkout() {
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
		
		JLabel lblNames = new JLabel("Room No:");
		lblNames.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNames.setBounds(536, 350, 92, 29);
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
		
		txtRoomNo = new JTextField();
		txtRoomNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(638, 353, 191, 22);
		contentPane.add(txtRoomNo);
		
		txtCheckOut = new JTextField();
		txtCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection con = null;
				Statement statement = null;
				String url = "jdbc:mysql://localhost:3306/sedimadatabase";
				String username = "root";
				String password = "Clement02";
				int roomNumber = Integer.parseInt(txtRoomNo.getText());
				
				
				
		            
		            try {
		            	Class.forName("com.mysql.cj.jdbc.Driver");
						
						con = DriverManager.getConnection(url, username, password);

		                
		                statement = con.createStatement();
		                
		                // SQL query to delete the guest using room number as the identifier
		                String insertQuery = "DELETE FROM tblguests WHERE room_No = " + roomNumber;

		               

		                // Execute the delete query
		                int rowsAffected = statement.executeUpdate(insertQuery);

		                if (rowsAffected > 0) {
		                	boolean isAvailable =  true;
		                    String chngAvailQuery = "UPDATE tblrooms SET is_Available = " + isAvailable + " WHERE room_No = " + roomNumber;

		                  
		                    statement.executeUpdate(chngAvailQuery);
		                    JOptionPane.showMessageDialog(checkout.this,"Guest checked out successfully from room " + roomNumber, "Input Error", JOptionPane.ERROR_MESSAGE);
		                } else {
		                	JOptionPane.showMessageDialog(checkout.this,"No guest found in room " + roomNumber, "Input Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (SQLException | ClassNotFoundException e1) {
		                e1.printStackTrace(); // Handle the exception properly in your application
		            } finally {
		                try {
		                    if (statement != null) {
		                        statement.close();
		                    }
		                    if (con != null) {
		                        con.close();
		                    }
		                } catch (SQLException se) {
		                    se.printStackTrace();
		                }
		            }

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
		txtCheckOut.setBounds(762, 620, 125, 31);
		contentPane.add(txtCheckOut);
		
		txtClose = new JTextField();
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		txtClose.setText("Close");
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtClose.setEditable(false);
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBackground(new Color(240, 128, 128));
		txtClose.setBounds(913, 620, 125, 31);
		contentPane.add(txtClose);
		
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
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.WHITE);
		textPane_2.setBounds(495, 442, 380, 10);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.WHITE);
		textPane_3.setBounds(495, 284, 380, 10);
		contentPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.WHITE);
		textPane_4.setBounds(495, 284, 7, 168);
		contentPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBackground(Color.WHITE);
		textPane_5.setBounds(875, 284, 7, 168);
		contentPane.add(textPane_5);
		
		JLabel lblNewLabel = new JLabel("Check Out");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		lblNewLabel.setBounds(467, 30, 488, 71);
		contentPane.add(lblNewLabel);
		
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

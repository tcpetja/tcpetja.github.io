package com.resortbookingapp;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CancelBooking extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private int mouseX, mouseY;
	private JTextField txtBookingList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelBooking frame = new CancelBooking();
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
	public CancelBooking() {
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
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField.setColumns(10);
		textField.setBounds(638, 353, 191, 22);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("Submit");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_1.setBackground(new Color(100, 149, 237));
		textField_1.setBounds(762, 620, 125, 31);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("Clear");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_2.setBackground(new Color(240, 128, 128));
		textField_2.setBounds(913, 620, 125, 31);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn checkIn = new CheckIn();
				checkIn.setVisible(true);
				dispose();
			}
		});
		textField_3.setText("Check In");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_3.setBackground(new Color(100, 149, 237));
		textField_3.setBounds(64, 219, 125, 31);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkout checkOut = new checkout();
				checkOut.setVisible(true);
				dispose();
			}
		});
		textField_4.setText("Check Out");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.WHITE);
		textField_4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_4.setBackground(new Color(100, 149, 237));
		textField_4.setBounds(64, 294, 125, 31);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomBooking roomBooking = new RoomBooking();
				roomBooking.setVisible(true);
				dispose();
			}
		});
		textField_5.setText("Room Booking");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_5.setBackground(new Color(100, 149, 237));
		textField_5.setBounds(64, 361, 125, 31);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelBooking cancelBooking = new CancelBooking();
				cancelBooking.setVisible(true);
				dispose();
			}
		});
		textField_6.setText("Cancel Booking");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setForeground(Color.WHITE);
		textField_6.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_6.setBackground(new Color(100, 149, 237));
		textField_6.setBounds(64, 432, 125, 31);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.addMouseListener(new MouseAdapter() {
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
		textField_7.setText("Rooms ");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setForeground(Color.WHITE);
		textField_7.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_7.setBackground(new Color(100, 149, 237));
		textField_7.setBounds(64, 504, 125, 31);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.addMouseListener(new MouseAdapter() {
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
		textField_8.setText("Guests");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setForeground(Color.WHITE);
		textField_8.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_8.setBackground(new Color(100, 149, 237));
		textField_8.setBounds(64, 564, 125, 31);
		contentPane.add(textField_8);
		
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
		
		JLabel lblCancelBooking = new JLabel("Cancel Booking");
		lblCancelBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelBooking.setFont(new Font("Harlow Solid Italic", Font.BOLD, 35));
		lblCancelBooking.setBounds(467, 30, 488, 71);
		contentPane.add(lblCancelBooking);
		
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

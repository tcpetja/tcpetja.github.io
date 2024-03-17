package com.resortbookingapp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionsPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtCheckIn;
	private JTextField txtRoomBooking;
	private JTextField txtCheckOut;
	private JTextField txtCancelBooking;
	private JTextField txtRooms;
	private JTextField txtGuest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsPage frame = new OptionsPage();
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
	public OptionsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 629, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCheckIn = new JTextField();
		txtCheckIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckIn chkIn = new CheckIn();
				chkIn.setVisible(true);
				dispose();
			}
		});
		txtCheckIn.setBounds(169, 71, 124, 71);
		txtCheckIn.setText("Check In");
		txtCheckIn.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckIn.setForeground(Color.WHITE);
		txtCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCheckIn.setEditable(false);
		txtCheckIn.setColumns(10);
		txtCheckIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCheckIn.setBackground(new Color(0, 250, 154));
		contentPane.add(txtCheckIn);
		
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
		txtRoomBooking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRoomBooking.setEditable(false);
		txtRoomBooking.setColumns(10);
		txtRoomBooking.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRoomBooking.setBackground(new Color(100, 149, 237));
		txtRoomBooking.setBounds(169, 158, 124, 71);
		contentPane.add(txtRoomBooking);
		
		txtCheckOut = new JTextField();
		txtCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rooms rooms = new Rooms();
				rooms.setVisible(true);
				dispose();
			}
		});
		txtCheckOut.setText("Check Out");
		txtCheckOut.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckOut.setForeground(Color.WHITE);
		txtCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCheckOut.setEditable(false);
		txtCheckOut.setColumns(10);
		txtCheckOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCheckOut.setBackground(new Color(255, 0, 0));
		txtCheckOut.setBounds(381, 71, 124, 71);
		contentPane.add(txtCheckOut);
		
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
		txtCancelBooking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCancelBooking.setEditable(false);
		txtCancelBooking.setColumns(10);
		txtCancelBooking.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtCancelBooking.setBackground(new Color(100, 149, 237));
		txtCancelBooking.setBounds(381, 158, 124, 71);
		contentPane.add(txtCancelBooking);
		
		txtRooms = new JTextField();
		txtRooms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rooms rooms = new Rooms();
				rooms.setVisible(true);
				dispose();
			}
		});
		txtRooms.setText("Rooms");
		txtRooms.setHorizontalAlignment(SwingConstants.CENTER);
		txtRooms.setForeground(Color.WHITE);
		txtRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRooms.setEditable(false);
		txtRooms.setColumns(10);
		txtRooms.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRooms.setBackground(new Color(100, 149, 237));
		txtRooms.setBounds(169, 244, 124, 71);
		contentPane.add(txtRooms);
		
		txtGuest = new JTextField();
		txtGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Guests guests= new Guests();
				guests.setVisible(true);
				dispose();
			}
		});
		txtGuest.setText("Guests");
		txtGuest.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuest.setForeground(Color.WHITE);
		txtGuest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGuest.setEditable(false);
		txtGuest.setColumns(10);
		txtGuest.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0)));
		txtGuest.setBackground(new Color(100, 149, 237));
		txtGuest.setBounds(381, 244, 124, 71);
		contentPane.add(txtGuest);
		
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
		lblX.setBounds(572, 0, 57, 47);
		contentPane.add(lblX);
		setUndecorated(true);
	}

}

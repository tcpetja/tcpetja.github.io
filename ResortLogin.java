package com.resortbookingapp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResortLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtLog;
	private JTextField txtClose;
	private JLabel lblX;
	private int mouseX, mouseY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResortLogin frame = new ResortLogin();
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
	public ResortLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 629, 356);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create a panel as a draggable area
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(0, 0, 0, 0));
        titleBar.setBounds(0, 0, 572, 30); // Adjust according to your design
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
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(176, 218, 277, 31);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(176, 158, 277, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtLog = new JTextField();
		txtLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//OptionsPage options = new OptionsPage();
				//options.setVisible(true);
				CheckIn checkin = new CheckIn();
				checkin.setVisible(true);
				dispose();
				
			}
		});
		txtLog.setForeground(new Color(255, 255, 255));
		txtLog.setBackground(new Color(100, 149, 237));
		txtLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtLog.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLog.setHorizontalAlignment(SwingConstants.CENTER);
		txtLog.setText("Log In");
		txtLog.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLog.setEditable(false);
		txtLog.setBounds(176, 275, 125, 31);
		contentPane.add(txtLog);
		txtLog.setColumns(10);
		
		txtClose = new JTextField();
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setBackground(new Color(240, 128, 128));
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setText("Close");
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setEditable(false);
		txtClose.setBounds(327, 275, 125, 31);
		contentPane.add(txtClose);
		txtClose.setColumns(10);
		
		//Profile Image manipulation
		JLabel user = new JLabel("");
		user.setHorizontalAlignment(SwingConstants.CENTER);

		// Load the original image
		ImageIcon originalIcon = new ImageIcon(ResortLogin.class.getResource("/profile.png"));
		Image originalImagep = originalIcon.getImage();

		// Resize the image to fit the JLabel dimensions
		Image resizedImage = originalImagep.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

		// Create a new ImageIcon with the resized image
		ImageIcon resizedIconp = new ImageIcon(resizedImage);

		// Set the resized icon to the JLabel
		user.setIcon(resizedIconp);

		user.setBounds(108, 152, 44, 40);
		contentPane.add(user);
		
		//Lock Image Manipulation
		JLabel pass = new JLabel("");
		pass.setHorizontalAlignment(SwingConstants.CENTER);

		// Load the original image
		ImageIcon originalIcon1 = new ImageIcon(ResortLogin.class.getResource("/lock.png"));
		Image originalImage = originalIcon1.getImage();

		// Resize the image to fit the JLabel dimensions
		Image resizedImage1 = originalImage.getScaledInstance(100, 55, Image.SCALE_SMOOTH);

		// Create a new ImageIcon with the resized image
		ImageIcon resizedIcon = new ImageIcon(resizedImage1);

		// Set the resized icon to the JLabel
		pass.setIcon(resizedIcon);

		pass.setBounds(108, 216, 44, 39);
		contentPane.add(pass);
		
		JLabel lblLogin = new JLabel("  LOGIN");
		
		// Load the original image
				ImageIcon originalIcon11 = new ImageIcon(ResortLogin.class.getResource("/profile1.png"));
				Image originalImageuser = originalIcon11.getImage();

				// Resize the image to fit the JLabel dimensions
				Image resizedImage11 = originalImageuser.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				// Create a new ImageIcon with the resized image
				ImageIcon resizedIconuser = new ImageIcon(resizedImage11);

				// Set the resized icon to the JLabel
				lblLogin.setIcon(resizedIconuser);

		
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblLogin.setBounds(176, 74, 277, 53);
		contentPane.add(lblLogin);
		
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(572, 0, 57, 47);
		contentPane.add(lblX);
		
		
		setUndecorated(true);
	}
}

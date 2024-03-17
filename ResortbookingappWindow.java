package com.resortbookingapp;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class ResortbookingappWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResortbookingappWindow window = new ResortbookingappWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResortbookingappWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 883, 633);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel resortLogo = new JLabel("");
		resortLogo.setHorizontalAlignment(SwingConstants.CENTER);
		// Load the original image
				ImageIcon originalIcon = new ImageIcon(ResortbookingappWindow.class.getResource("/SedimaLogo.png"));
				Image originalImage = originalIcon.getImage();

				// Resize the image to fit the JLabel dimensions
				Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

				// Create a new ImageIcon with the resized image
				ImageIcon resizedIconp = new ImageIcon(resizedImage);

				// Set the resized icon to the JLabel
				resortLogo.setIcon(resizedIconp);

				resortLogo.setBounds(30, 10, 152, 162);
				frame.getContentPane().add(resortLogo);
				
				JPanel panel = new JPanel();
				panel.setBounds(74, 218, 400, 368);
				frame.getContentPane().add(panel);
				
				JLabel lblNewLabel_1 = new JLabel("Full Names");
				panel.add(lblNewLabel_1);
				
				textField = new JTextField();
				textField.setColumns(10);
				panel.add(textField);
				
				JLabel lblNewLabel = new JLabel("Full Names");
				panel.add(lblNewLabel);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				panel.add(textField_1);
	}
}

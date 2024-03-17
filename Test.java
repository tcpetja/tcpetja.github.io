package com.resortbookingapp;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;


public class Test extends JFrame {
	private static Connection con;
	private static Statement statement;
	private static ResultSet resultSet;
	private static DefaultTableModel model;
	
	


	private JPanel contentPane;
	private JTextField txtROom;
	private JTextField txtFullName;
	private JTextField textSurname;
	private JTextField txtcarReg;
	private JTextField txtNoVisitors;
	private static JTable table;
	private JDateChooser dateChooser;
	
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
		
		String query = "SELECT * FROM tblguests";
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
		
		

		// Add data to the model from the ResultSet
		while (resultSet.next()) {
		    // Retrieve data from the ResultSet and add it to the model
		    String column1Data = resultSet.getString("room_No");
		    String column2Data = resultSet.getString("full_Name");
		    String column3Data = resultSet.getString("surname");
		    String column4Data = resultSet.getString("Car_Registration");
		    String column5Data = resultSet.getString("visitor_Count");
		    String column6Data = resultSet.getString("check_In");
		    String column7Data = resultSet.getString("check_Out");
		    model.addRow(new Object[]{column1Data, column2Data, column3Data, column4Data, column5Data, column6Data, column7Data});
		}
		
		

		
	}
	//Refresh table method
	private void refreshTable() {
	    model.setRowCount(0); // Clear the existing table data

	    try {
	        resultSet = statement.executeQuery("SELECT * FROM tblguests");
	        while (resultSet.next()) {
	            String[] rowData = {
	                resultSet.getString("room_No"),
	                resultSet.getString("full_Name"),
	                resultSet.getString("surname"),
	                resultSet.getString("Car_Registration"),
	                resultSet.getString("visitor_Count"),
	                resultSet.getString("check_In"),
	                resultSet.getString("check_Out")
	            };
	            model.addRow(rowData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	/**
	 * Create the frame.
	 */
	public Test() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dateChooser = new JDateChooser(); // Create a JDateChooser instance
        dateChooser.setBounds(10, 30, 150, 20); // Adjust the position and size as needed
        contentPane.add(dateChooser);
        
		txtROom = new JTextField();
		txtROom.setText("ROomNUmber");
		txtROom.setBounds(102, 88, 96, 19);
		contentPane.add(txtROom);
		txtROom.setColumns(10);
		
		txtFullName = new JTextField();
		txtFullName.setText("funn Name");
		txtFullName.setBounds(102, 143, 96, 19);
		contentPane.add(txtFullName);
		txtFullName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setText("Surname");
		textSurname.setBounds(102, 185, 96, 19);
		contentPane.add(textSurname);
		textSurname.setColumns(10);
		
		txtcarReg = new JTextField();
		txtcarReg.setText("Car Registration");
		txtcarReg.setBounds(102, 232, 96, 19);
		contentPane.add(txtcarReg);
		txtcarReg.setColumns(10);
		
		txtNoVisitors = new JTextField();
		txtNoVisitors.setText("number of visitors");
		txtNoVisitors.setBounds(102, 261, 96, 19);
		contentPane.add(txtNoVisitors);
		txtNoVisitors.setColumns(10);
		
		table = new JTable();
		table.setBounds(391, 53, 628, 272);
		contentPane.add(table);
		
		JButton btnAddRecord = new JButton("Add Record");
		btnAddRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				        // Retrieve data from text fields
				        String roomNumber = txtROom.getText();
				        String fullName = txtFullName.getText();
				        String surname = textSurname.getText();
				        String carRegistration = txtcarReg.getText();
				        String visitors = txtNoVisitors.getText();
				        
				        // Get the selected date from the JDateChooser
				        java.util.Date selectedDate = dateChooser.getDate();

				        // Format the date to your desired format, e.g., "yyyy-MM-dd"
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String formattedDate = dateFormat.format(selectedDate);
				        
				        // Add the data to the database (use an INSERT statement)
				        try {
				            String insertQuery = "INSERT INTO tblguests (room_No, full_Name, surname, Car_Registration, visitor_Count,check_In) " +
				                               "VALUES ('" + roomNumber + "', '" + fullName + "', '" + surname + "', '" + carRegistration + "', '" + visitors + "','" + formattedDate + "')";
				            statement.executeUpdate(insertQuery);
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        }

				        // Clear text fields
				        txtROom.setText("");
				        txtFullName.setText("");
				        textSurname.setText("");
				        txtcarReg.setText("");
				        txtNoVisitors.setText("");
				        dateChooser.setCalendar(null); // Clear the selected date

				        // Refresh the JTable by re-fetching data
				        refreshTable();
				 
	
			}
		});
		btnAddRecord.setBounds(102, 351, 85, 21);
		contentPane.add(btnAddRecord);
		
		String[] columnNames = {"Room No", "Full Name", "Surname", "Car Registration", "Visitors", "Check-In", "Check-Out"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
	}

}

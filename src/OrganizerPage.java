import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class OrganizerPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Organizer orgLogin = new Organizer("kalai", "kalai@123");
					OrganizerPage frame = new OrganizerPage(orgLogin);
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
	public OrganizerPage(Organizer org) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 226, 643);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 200, 0));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setBounds(10, 10, 206, 42);
		textField.setText(org.getOrgName());
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(10, 572, 206, 42);
		panel.add(btnLogout);
		
		JButton btnMyProfile = new JButton("My Contests");
		btnMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerPage(org).setVisible(true);
			}
		});
		btnMyProfile.setForeground(Color.RED);
		btnMyProfile.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMyProfile.setBounds(10, 144, 206, 42);
		panel.add(btnMyProfile);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerProfile(org).setVisible(true);
			}
		});
		btnProfile.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnProfile.setBounds(10, 219, 206, 42);
		panel.add(btnProfile);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(256, 10, 830, 643);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 810, 350);
		panel_1.add(scrollPane);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(370, 469, 263, 39);
		
		table = new JTable();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM contest WHERE organizer_id=" + org.getAccountId());
//			table.setEnabled(false);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(Exception e1) {
//			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Connection error");
		}
		scrollPane.setViewportView(table);
		
		JLabel lblContestName = new JLabel("Contest Name");
		lblContestName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContestName.setBounds(146, 410, 168, 39);
		panel_1.add(lblContestName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(368, 410, 265, 39);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStartTime = new JLabel("Start time");
		lblStartTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStartTime.setBounds(146, 469, 168, 39);
		panel_1.add(lblStartTime);
		
		panel_1.add(dateChooser);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEndTime.setBounds(146, 528, 168, 39);
		panel_1.add(lblEndTime);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(368, 528, 265, 39);
		panel_1.add(dateChooser_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(643, 469, 53, 39);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setBounds(706, 469, 53, 39);
		panel_1.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_2.setBounds(643, 528, 53, 39);
		panel_1.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_3.setBounds(706, 528, 53, 39);
		panel_1.add(comboBox_3);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(146, 370, 168, 30);
		panel_1.add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.RED);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setBounds(368, 370, 265, 30);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
					Statement stmt = conn.createStatement();
					Date date = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(dateChooser.getDate().toString());
					Date date_1 = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(dateChooser_1.getDate().toString());
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				    String strDate = formatter.format(date);
				    String endDate = formatter.format(date_1);
				    
					System.out.println(strDate);
					String hrs = comboBox.getSelectedItem().toString();
					String mins = comboBox_1.getSelectedItem().toString();
					
					System.out.println(endDate);
					String hrs_1 = comboBox_2.getSelectedItem().toString();
					String mins_1 = comboBox_3.getSelectedItem().toString();
					strDate = strDate + " " + hrs + ":" + mins + ":00";
					endDate = endDate + " " + hrs_1 + ":" + mins_1 + ":00";
					String sql = "INSERT INTO contest(organizer_id, contest_name, start_time, end_time) VALUES(" + org.getAccountId() + ", '" + textField_1.getText().toString() + "', '" + strDate + "', '" + endDate + "')";
//					System.out.println(sql);
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully Created");
					comboBox.setSelectedItem("00");
					comboBox_1.setSelectedItem("00");
					comboBox_2.setSelectedItem("00");
					comboBox_3.setSelectedItem("00");
					dateChooser.setDate(null);
					dateChooser_1.setDate(null);
					textField_1.setText(null);
					textField_2.setText(null);
					ResultSet rs = stmt.executeQuery("SELECT * FROM contest WHERE organizer_id=" + org.getAccountId());
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch(Exception e1) {
					 System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Cannot Create");
				}
			}
		});
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setBackground(Color.GREEN);
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreate.setBounds(146, 588, 150, 45);
		panel_1.add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
					Statement stmt = conn.createStatement();
//					textField_2.setText(selectedContestId);
//					textField_1.setText(selectedContestName);
//					stmt.executeUpdate("UPDATE contest SET contest_name=" + textField_2.getText().toString());
//					ResultSet rs = stmt.executeQuery("SELECT * FROM contest WHERE organizer_id=" + org.getAccountId());
//					table.setModel(DbUtils.resultSetToTableModel(rs));
//					System.out.println(dateChooser.getDate().toString());
//					System.out.println(comboBox.getModel().getSelectedItem().toString());
//					System.out.println(comboBox_1.getModel().getSelectedItem().toString());
//					Sun Nov 29 09:00:00 IST 2020
					Date date = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(dateChooser.getDate().toString());
					Date date_1 = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(dateChooser_1.getDate().toString());
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				    String strDate = formatter.format(date);
				    String endDate = formatter.format(date_1);
				    
					// System.out.println(strDate);
					String hrs = comboBox.getSelectedItem().toString();
					String mins = comboBox_1.getSelectedItem().toString();
					
					// System.out.println(endDate);
					String hrs_1 = comboBox_2.getSelectedItem().toString();
					String mins_1 = comboBox_3.getSelectedItem().toString();
					strDate = strDate + " " + hrs + ":" + mins + ":00";
					endDate = endDate + " " + hrs_1 + ":" + mins_1 + ":00";
					String sql = "UPDATE contest SET contest_name='" + textField_1.getText().toString() + "', start_time='" + strDate + "', end_time='" + endDate + "' WHERE contest_id=" + textField_2.getText().toString();
					// System.out.println(sql);
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					ResultSet rs = stmt.executeQuery("SELECT * FROM contest WHERE organizer_id=" + org.getAccountId());
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch(Exception e1) {
					// System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Cannot Delete");
				}
			}
		});
		btnUpdate.setBackground(Color.YELLOW);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(340, 588, 150, 45);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
					Statement stmt = conn.createStatement();
					int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete", "Warning", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						stmt.executeUpdate("DELETE FROM contest WHERE contest_id=" + textField_2.getText().toString());
						JOptionPane.showMessageDialog(null, "Successfully Deleted");
					}
					ResultSet rs = stmt.executeQuery("SELECT * FROM contest WHERE organizer_id=" + org.getAccountId());
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch(Exception e1) {
					// System.out.println(e1);
					JOptionPane.showMessageDialog(null, "Cannot Delete");
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(526, 588, 150, 45);
		panel_1.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedItem("00");
				comboBox_1.setSelectedItem("00");
				comboBox_2.setSelectedItem("00");
				comboBox_3.setSelectedItem("00");
				dateChooser.setDate(null);
				dateChooser_1.setDate(null);
				textField_1.setText(null);
				textField_2.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClear.setForeground(Color.RED);
		btnClear.setBounds(10, 377, 85, 39);
		panel_1.add(btnClear);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
//				System.out.println(selectedIndex);
//				System.out.println(df.getValueAt(selectedIndex, 3));
				String selectedContestId = df.getValueAt(selectedIndex, 0).toString();
				String selectedContestName = df.getValueAt(selectedIndex, 2).toString();
				String startTime = df.getValueAt(selectedIndex, 3).toString();
				String endTime = df.getValueAt(selectedIndex, 4).toString();
//				System.out.println(startTime + " " + endTime);
				Date date = null;
				String hours = null;
				String mins = null;
				Date date_1 = null;
				String hours_1 = null;
				String mins_1 = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
//					hours = Integer.toString(date.getHours());
					
					date_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
//					hours_1 = Integer.toString(date.getHours());
					
					int temp = date.getHours();
					int temp_1 = date_1.getHours();
					if(temp < 10) {
						hours = "0" + Integer.toString(temp);
					} else {
						hours = Integer.toString(date.getHours());
					}
					
					if(temp_1 < 10) {
						hours_1 = "0" + Integer.toString(temp_1);
					} else {
						hours_1 = Integer.toString(date_1.getHours());
					}
					
//					System.out.println(hours);
					temp = date.getMinutes();
					temp_1 = date_1.getMinutes();
					if(temp < 10) {
						mins = "0" + Integer.toString(temp);
					} else {
						mins = Integer.toString(temp);
					}
					if(temp_1 < 10) {
						mins_1 = "0" + Integer.toString(temp_1);
					} else {
						mins_1 = Integer.toString(temp_1);
					}
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dateChooser.setDate(date);
				dateChooser_1.setDate(date_1);
				textField_2.setText(selectedContestId);
				textField_2.setEditable(false);
				textField_1.setText(selectedContestName);
				comboBox.getModel().setSelectedItem(hours);
				comboBox_1.getModel().setSelectedItem(mins);
				comboBox_2.getModel().setSelectedItem(hours_1);
				comboBox_3.getModel().setSelectedItem(mins_1);
			}
		});
	}
}

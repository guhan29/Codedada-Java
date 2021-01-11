import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class OrganizerProfile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Organizer orgLogin = new Organizer("kalai", "kalai@123");
					OrganizerProfile frame = new OrganizerProfile(orgLogin);
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
	public OrganizerProfile(Organizer org) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String orgId = null;
		String orgName = null;
		String orgDesc=null;
		String username = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM organizer WHERE organizer_id=" + org.getAccountId());
			if(rs.next()) {
				orgId = rs.getString("organizer_id");
				orgName = rs.getString("organizer_name");
				orgDesc = rs.getString("organization_desc");
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM account WHERE account_id=" + org.getAccountId());
				if(rs1.next()) {
					username = rs1.getString("username");
				} else {
					throw new Exception("Connection error");
				}
			} else {
				throw new Exception("Connection error");
			}
		} catch(Exception e1) {
			// System.out.println(e1);
			// e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection error");
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 226, 643);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setText(org.getOrgName());
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.ORANGE);
		textField.setBounds(10, 10, 206, 42);
		panel.add(textField);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerLogin().setVisible(true);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBackground(Color.BLACK);
		button.setBounds(10, 572, 206, 42);
		panel.add(button);
		
		JButton button_1 = new JButton("My Contests");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerPage(org).setVisible(true);
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_1.setBounds(10, 144, 206, 42);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Profile");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerProfile(org).setVisible(true);
			}
		});
		button_2.setForeground(Color.RED);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_2.setBounds(10, 219, 206, 42);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(246, 10, 830, 643);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Guhan\\Documents\\eclipse-java-workspace\\Contest\\src\\images\\user.png"));
		label.setBackground(new Color(240, 240, 240));
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setForeground(Color.BLACK);
		label.setBounds(286, 10, 220, 220);
		panel_1.add(label);
		
		JLabel lblAccountId = new JLabel("Account Id");
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAccountId.setBounds(78, 244, 154, 42);
		panel_1.add(lblAccountId);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setForeground(Color.RED);
		textField_1.setBounds(265, 244, 500, 42);
		textField_1.setText(orgId);
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUsename = new JLabel("Usename");
		lblUsename.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsename.setBounds(78, 312, 154, 42);
		panel_1.add(lblUsename);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(265, 312, 500, 42);
		textField_2.setText(username);
		textField_2.setEditable(false);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(78, 375, 154, 42);
		panel_1.add(lblName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(265, 375, 500, 42);
		textField_3.setText(orgName);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(78, 441, 154, 42);
		panel_1.add(lblDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(265, 441, 500, 76);
		textArea.setText(orgDesc);
		panel_1.add(textArea);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("UPDATE organizer SET organizer_name='" + textField_3.getText().toString() +"', organization_desc='" + textArea.getText().toString() + "' WHERE organizer_id=" + org.getAccountId());
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					setVisible(false);
					new OrganizerProfile(org).setVisible(true);
				} catch(Exception e1) {
					// System.out.println(e1);
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection error");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(255, 0, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(311, 576, 154, 42);
		panel_1.add(btnUpdate);
	}
}

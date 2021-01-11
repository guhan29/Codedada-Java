import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import net.proteanit.sql.DbUtils;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ParticipantPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
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
					ParticipantPage frame = new ParticipantPage(new Participant("guhan", "guhan@123"));
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
	public ParticipantPage(Participant part) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 210, 643);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.setBounds(10, 586, 190, 47);
		panel.add(btnLogout);
		
		textField = new JTextField();
		textField.setBackground(Color.ORANGE);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setBounds(10, 10, 190, 47);
		textField.setText(part.getUsername());
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnContests = new JButton("Contests");
		btnContests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ParticipantPage(part).setVisible(true);
			}
		});
		btnContests.setForeground(Color.RED);
		btnContests.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnContests.setBounds(10, 138, 190, 47);
		panel.add(btnContests);
		
		JButton btnQuestions = new JButton("Questions");
		btnQuestions.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnQuestions.setBounds(10, 216, 190, 47);
		panel.add(btnQuestions);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(230, 10, 846, 643);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 826, 326);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				String selectedContestId = df.getValueAt(selectedIndex, 0).toString();
				String selectedContestName = df.getValueAt(selectedIndex, 2).toString();
				String startTime = df.getValueAt(selectedIndex, 3).toString();
				String endTime = df.getValueAt(selectedIndex, 4).toString();
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
					Date date_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
					Date d = new Date();
					textField_1.setText(selectedContestName);
					if(date_1.compareTo(d) <= 0) {
						textField_2.setText("Ended");
					} else if(date.compareTo(d) <= 0) {
						textField_2.setText("Running");
					} else {
						textField_2.setText("Yet to start");
					}
					textField_3.setText(selectedContestId);
					System.out.println(selectedIndex);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM contest");
//			table.setEnabled(false);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(Exception e1) {
//			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Connection error");
		}
		scrollPane.setViewportView(table);
		
		JLabel lblContestName = new JLabel("Contest Name");
		lblContestName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContestName.setBounds(98, 385, 161, 41);
		panel_1.add(lblContestName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(301, 385, 316, 41);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStatus.setBounds(98, 459, 161, 41);
		panel_1.add(lblStatus);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setBounds(301, 459, 316, 41);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnJoinContest = new JButton("Join Contest");
		btnJoinContest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				String selectedContestId = textField_3.getText().toString();
				String selectedContestName = textField_1.getText().toString();
				String status = textField_2.getText().toString();
//				String startTime = df.getValueAt(selectedIndex, 3).toString();
//				String endTime = df.getValueAt(selectedIndex, 4).toString();
//				try {
//					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
//					Date date_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
//					Date d = new Date();
//					textField_1.setText(selectedContestName);
//					if(date_1.compareTo(d) <= 0) {
//						textField_2.setText("Ended");
//					} else if(date.compareTo(d) <= 0) {
//						textField_2.setText("Running");
//					} else {
//						textField_2.setText("Yet to start");
//					}
//					textField_3.setText(selectedContestId);
//					System.out.println(selectedIndex);
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				if(status.equalsIgnoreCase("Running")) {
//					String sql = "SELECT * FROM question WHERE contest_id=" + selectedContestId;
//					try {
//						Class.forName("com.mysql.cj.jdbc.Driver");
//						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
//						Statement stmt = conn.createStatement();
//						ResultSet rs = stmt.executeQuery(sql);
//						table.setModel(DbUtils.resultSetToTableModel(rs));
//						if(rs.next()) {
//							String questionId = rs.getString("question")
//						}
//					} catch(Exception e1) {
//						System.out.println(e1);
//						JOptionPane.showMessageDialog(null, "Connection error");
//					}
					Contest cont = new Contest();
					cont.setContest(Integer.parseInt(selectedContestId));
					setVisible(false);
					new ParticipantQuestion(part, cont).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "You can't attend the contest now");
				}
			}
		});
		btnJoinContest.setForeground(Color.WHITE);
		btnJoinContest.setBackground(Color.GREEN);
		btnJoinContest.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnJoinContest.setBounds(301, 555, 161, 41);
		panel_1.add(btnJoinContest);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setBounds(647, 385, 161, 41);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
	}
}

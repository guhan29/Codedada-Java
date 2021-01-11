import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParticipantQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contest cont = new Contest();
					cont.setContest(Integer.parseInt("1"));
					ParticipantQuestion frame = new ParticipantQuestion(new Participant("guhan", "guhan@123"), cont);
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
	public ParticipantQuestion(Participant part, Contest cont) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 210, 703);
		contentPane.add(panel);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		button.setBackground(Color.BLACK);
		button.setBounds(10, 586, 190, 47);
		panel.add(button);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setText(part.getUsername());
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.ORANGE);
		textField.setBounds(10, 10, 190, 47);
		panel.add(textField);
		
		JButton button_1 = new JButton("Contests");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ParticipantPage(part).setVisible(true);
//				new ParticipantPage(cont)
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_1.setBounds(10, 138, 190, 47);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Questions");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ParticipantQuestion(part, cont).setVisible(true);
			}
		});
		button_2.setForeground(Color.RED);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_2.setBounds(10, 216, 190, 47);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(230, 10, 846, 703);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 826, 326);
		panel_1.add(scrollPane);
		
		table = new JTable();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT question_id, question_name, question_description, question_points, contest_id FROM question WHERE contest_id=" + cont.contestId);
//			table.setEnabled(false);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(Exception e1) {
//			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Connection error");
		}
		scrollPane.setViewportView(table);
		
		JLabel lblQuestionName = new JLabel("Question Name");
		lblQuestionName.setBounds(98, 385, 161, 41);
		lblQuestionName.setForeground(new Color(0, 0, 0));
		lblQuestionName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblQuestionName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(301, 385, 316, 41);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(98, 459, 161, 41);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblDescription);
		
		JButton btnSolve = new JButton("Solve");
		
		btnSolve.setBounds(306, 652, 161, 41);
		btnSolve.setForeground(Color.WHITE);
		btnSolve.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSolve.setBackground(Color.GREEN);
		panel_1.add(btnSolve);
		
		textField_3 = new JTextField();
		textField_3.setBounds(647, 385, 161, 41);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		panel_1.add(textField_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(301, 469, 508, 76);
		textArea.setEditable(false);
		panel_1.add(textArea);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				String selectedQuestionId = df.getValueAt(selectedIndex, 0).toString();
				String selectedQuestionName = df.getValueAt(selectedIndex, 1).toString();
				String selectedDesc = df.getValueAt(selectedIndex, 2).toString();
//				String endTime = df.getValueAt(selectedIndex, 4).toString();
				String selectedQuestionPoints = df.getValueAt(selectedIndex, 3).toString();
				String selectedQuestionContestId = df.getValueAt(selectedIndex, 4).toString();
				
				textField_1.setText(selectedQuestionName);
				textField_3.setText(selectedQuestionId);
				textField_2.setText(selectedQuestionContestId);
				textArea.setText(selectedDesc);
				
				/*try {
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
				}*/
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setBounds(33, 647, 153, 46);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnswer.setBounds(98, 575, 161, 41);
		panel_1.add(lblAnswer);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textField_4.setBounds(301, 575, 508, 41);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String questionId = textField_3.getText().toString();
				String contestId = textField_2.getText().toString();
				int participantId = part.getAccountId();
				String solution = textField_4.getText().toString().trim();
				
				Submission sub = new Submission(Integer.parseInt(questionId));
//				q.setQuestion(Integer.parseInt(s));
				sub.submitSolution(Integer.parseInt(questionId), Integer.parseInt(contestId), participantId, solution);
//				System.out.println(sub.status);
				if(sub.status) {
					JOptionPane.showMessageDialog(null, "Correct Answer");
					sub.save();
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer");
				}
			}
		});
	}
}

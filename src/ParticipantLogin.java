import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParticipantLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParticipantLogin frame = new ParticipantLogin();
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
	public ParticipantLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 6, true), "Welcome Participant", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 25), new Color(255, 0, 0)));
		panel.setBounds(10, 10, 1066, 643);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Guhan\\Documents\\eclipse-java-workspace\\Contest\\src\\images\\user.png"));
		label.setBounds(398, 54, 220, 220);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(10, 342, 1046, 291);
		panel.add(panel_1);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(274, 38, 184, 37);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(468, 38, 373, 37);
		panel_1.add(textField);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(274, 110, 184, 37);
		panel_1.add(label_2);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText().toString();
				String password = passwordField.getText().toString();
				Participant part = new Participant(username, password);
				System.out.println(part.status);
				if(part.status) {
					setVisible(false);
					new ParticipantPage(part).setVisible(true);
				} else {
					textField.setText(null);
					passwordField.setText(null);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBackground(new Color(60, 179, 113));
		button.setBounds(324, 194, 134, 37);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(498, 194, 128, 37);
		panel_1.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(468, 110, 373, 37);
		panel_1.add(passwordField);
	}
}

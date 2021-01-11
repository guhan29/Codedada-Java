import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 6, true), "Codedada-Login", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 25), new Color(255, 0, 0)));
		panel.setBounds(10, 0, 1166, 563);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Guhan\\Documents\\eclipse-java-workspace\\Contest\\src\\images\\talk-bg.PNG"));
		lblNewLabel.setBounds(10, 29, 1166, 534);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(10, 576, 1166, 85);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnParticipant = new JButton("Participant");
		btnParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ParticipantLogin().setVisible(true);
			}
		});
		btnParticipant.setForeground(Color.WHITE);
		btnParticipant.setBackground(Color.GREEN);
		btnParticipant.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnParticipant.setBounds(169, 22, 195, 35);
		panel_1.add(btnParticipant);
		
		JButton btnOrganizer = new JButton("Organizer");
		btnOrganizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new OrganizerLogin().setVisible(true);
			}
		});
		btnOrganizer.setForeground(Color.WHITE);
		btnOrganizer.setBackground(Color.RED);
		btnOrganizer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOrganizer.setBounds(774, 22, 195, 35);
		panel_1.add(btnOrganizer);
	}
}

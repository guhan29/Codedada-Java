import java.sql.*;

public class Participant extends Account {
	int participantId;
	String name, about;
	public boolean status = false;
	
	Participant() {
		
	}
	
	Participant(String username, String password) {
		super(username, password);
		this.setParticipant();
	}
	
	Participant(String username, String password, String name, String about) {
		super(username, password);
		this.name = name;
		this.about = about;
	}
	
	protected int getParticipantId() {
		this.setParticipant();
		return this.participantId;
	}
	
	public String getParticipantName() {
		return this.name;
	}
	
	public boolean setParticipant() {
		try {
			if(!super.setAccount()) {
				return false;
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM participant WHERE participant_id=" + this.getAccountId();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				this.name = rs.getString("participant_name");
				this.participantId = rs.getInt("participant_id");
				this.about = rs.getString("about");
			} else {
				System.out.println("Participant: Invalid username or password");
			}
			conn.close();
		} catch(Exception eGet) {
			System.out.println(eGet);
		}
		this.status = true;
		return true;
	}
	
	public void save() {
		try {
			super.save();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			String sqlInsert = "INSERT INTO participant(participant_id, participant_name, about) VALUES(" + this.getAccountId() + ", '" + this.name + "', '" + this.about + "')";
			stmt.executeUpdate(sqlInsert);
			System.out.println("Created Participant");
			this.setParticipant();
			conn.close();
		} catch(Exception eSave) {
			System.out.println(eSave);
		}
	}
}

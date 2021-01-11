import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;;

public class Contest extends Organizer {
	protected int contestId, organizerId;
	public String contestName, startTime, endTime;
	Contest() {
		
	}
	Contest(String username, String password) {
		super(username, password);
		super.setOrganizer();
	}
	
	protected int getContestId() {
		return this.contestId;
	}

	public void createContest(String contestName, String startTime, String endTime) {
		this.contestName = contestName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getContestName() {
		return this.contestName;
	}
	
	public boolean setOrganizer() {
		if(super.setOrganizer()) return true;
		return false;
	}
	
	public void setContest(int contestId) {
		this.contestId = contestId;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			// String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			String sql = "SELECT * FROM contest WHERE contest_id=" + this.contestId;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				this.contestId = rs.getInt("contest_id");
				this.organizerId = rs.getInt("organizer_id");
				this.contestName = rs.getString("contest_name");
				this.startTime = rs.getString("start_time");
				this.endTime = rs.getString("end_time");
			} else {
				System.out.println("Invalid id");
			}
			conn.close();
		} catch(Exception eSave) {
//			JOptionPane.showMessageDialog(null, "error");
			System.out.println(eSave);
		}
	}
	
	public void save() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			// String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			String sqlInsert = "INSERT INTO contest(organizer_id, contest_name, start_time, end_time) VALUES(" + this.getAccountId() + ", '" + this.contestName + "','" + this.startTime + "', '" + this.endTime +"')";
			stmt.executeUpdate(sqlInsert);
			System.out.println("Created Contest");
			conn.close();
		} catch(Exception eSave) {
//			JOptionPane.showMessageDialog(null, "error");
			System.out.println(eSave);
		}
	}
}

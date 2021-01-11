import java.sql.*;


public class Question extends Contest {
	protected int questionId;
	protected String questionSolution;
	public int questionPoints;
	public String questionName, questionDescription;
	
	Question() {
		
	}
	public void setQuestion(int questionId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			// String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			String sql = "SELECT * FROM question WHERE question_id=" + questionId;
			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("Created Contest");
			if(rs.next()) {
				this.questionId = questionId;
				this.questionName = rs.getString("question_name");
				this.questionDescription = rs.getString("question_description");
				this.questionPoints = Integer.parseInt(rs.getString("question_points"));
				this.questionSolution = rs.getString("question_solution");
				this.contestId = Integer.parseInt(rs.getString("contest_id"));
			}
			conn.close();
		} catch(Exception eSave) {
//			JOptionPane.showMessageDialog(null, "error");
			System.out.println(eSave);
		}
	}
	protected int getContestId() {
		return super.getContestId();
	}
	public void createQuestion(int questionId, String questionName, String questionDescription, String questionSolution, int questionPoints) {
		this.questionId = questionId;
		this.questionName = questionName;
		this.questionDescription = questionDescription;
		this.questionPoints = questionPoints;
		this.questionSolution = questionSolution;
	}
	public int getQuestionId() {
		return this.questionId;
	}
	public void save() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			// String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			String sqlInsert = "INSERT INTO question(organizer_id, contest_name, start_time, end_time) VALUES(" + this.getAccountId() + ", '" + this.contestName + "','" + this.startTime + "', '" + this.endTime +"')";
			stmt.executeUpdate(sqlInsert);
			System.out.println("Created Contest");
			conn.close();
		} catch(Exception eSave) {
//			JOptionPane.showMessageDialog(null, "error");
			System.out.println(eSave);
		}
	}
}

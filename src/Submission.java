import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Submission extends Question {
	protected int participantId, points, contestId;
	protected String solution, verdict;
	public boolean status=false;
	Submission(int questionId) {
		super.setQuestion(questionId);
	}
	public void submitSolution(int questionId, int contestId, int participantId, String solution) {
		super.setQuestion(questionId);
		this.contestId = contestId;
		this.solution = solution;
		this.participantId = participantId;
		validateSubmission();
	}
	
	private void validateSubmission() {
//		System.out.println(super.questionSolution.trim() + " " + this.solution);
		if(super.questionSolution.trim().equals(this.solution)) {
			this.verdict = "AC";
			this.points = super.questionPoints;
			this.status = true;
		} else {
			this.verdict = "WA";
			this.points = 0;
			this.status = false;
		}
	}
	
	public String[] getResult() {
		String[] result = {this.verdict, Integer.toString(this.points)};
		return result;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String getVerdict() {
		return this.verdict;
	}
	
	public void save() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			// String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			String sqlInsert = "INSERT INTO submission(question_id, participant_id, contest_id, points, solution, verdict) VALUES(" + this.getQuestionId() + ", " + this.participantId + ", " + this.contestId + ", " + this.points + ", '" + this.solution + "', '" + this.verdict + "')";
//			System.out.println(sqlInsert);
			stmt.executeUpdate(sqlInsert);
//			System.out.println("Submitted");
			conn.close();
		} catch(Exception eSave) {
//			JOptionPane.showMessageDialog(null, "error");
			System.out.println(eSave);
			eSave.printStackTrace();
		}
	}
}

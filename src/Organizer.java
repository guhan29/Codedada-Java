import java.sql.*;

public class Organizer extends Account {
	private int orgId;
	public String orgDesc, orgName;
	public boolean status=false;
	
	Organizer() {
		
	}
	
	Organizer(String username, String password) {
		super(username, password);
		this.setOrganizer();
	}
	
	Organizer(String username, String password, String orgName, String orgDesc) {
		super(username, password);
		this.orgName = orgName;
		this.orgDesc = orgDesc;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
	public String getOrgName() {
		return this.orgName;
	}
	
	public String getOrgDesc() {
		return this.orgDesc;
	}
	
	@Override
	public int getAccountId() {
		return super.getAccountId();
	}
	
	public boolean setOrganizer() {
		try {
			if(!super.setAccount()) {
//				throw new Exception("Invalid credentials");
//				this.status = false;
				return false;
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				this.orgId = rs.getInt("organizer_id");
				this.orgName = rs.getString("organizer_name");
				this.orgDesc = rs.getString("organization_desc");
			} else {
//				this.status = false;
				System.out.println("Organizer: Invalid username or password");
			}
			conn.close();
		} catch(Exception eGet) {
			System.out.println(eGet);
//			this.status = false;
			return false;
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
			String sql = "SELECT * FROM organizer WHERE organizer_id=" + this.getAccountId();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Organizer alredy exists");
			} else {
				String sqlInsert = "INSERT INTO organizer(organizer_id, organizer_name, organization_desc) VALUES(" + this.getAccountId() + ", '" + this.orgName + "', '" + this.orgDesc + "')";
				stmt.executeUpdate(sqlInsert);
				System.out.println("Created Organizer");
				this.setOrganizer();
			}
			conn.close();
		} catch(Exception eSave) {
			System.out.println(eSave);
		}
	}
}

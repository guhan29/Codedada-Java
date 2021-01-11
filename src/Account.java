import java.sql.*;

import javax.swing.JOptionPane;

public class Account {
	private int account_id;
	private String password;
	protected String username;
	
	Account() {
		
	}
	
	Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	protected boolean setAccount() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM account WHERE username='" + this.username + "' AND password= '" + this.password + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				this.account_id = rs.getInt("account_id");
				this.username = rs.getString("username");
				this.password = rs.getString("password");
			} else {
				System.out.println("Invalid username or password");
				JOptionPane.showMessageDialog(null, "Invalid username or password");
				return false;
			}
			conn.close();
		} catch(Exception eGet) {
			System.out.println(eGet);
			return false;
		}
		return true;
	}
	
	protected String getUsername() {
		this.setAccount();
		return this.username;
	}
	
	protected int getAccountId() {
		this.setAccount();
		return this.account_id;
	}
	
	protected void save() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_codedada?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Kolkata", "root", "");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM account WHERE username='" + this.username + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Username alredy exists");
			} else {
				String sqlInsert = "INSERT INTO account(username, password) VALUES('" + this.username + "', '" + this.password + "')";
				try {
					stmt.executeUpdate(sqlInsert);
					System.out.println("Created account");
					this.setAccount();
				} catch(Exception eInsert) {
					System.out.println(eInsert);
				}
			}
			rs.close();
			conn.close();
		} catch(Exception eSave) {
			System.out.println(eSave);
		}
	}
}

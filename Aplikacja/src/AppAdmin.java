import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppAdmin

{
	Connection con = DriverManager.getConnection("jdbc:sybase:Tds:localhost:2638", "DBA", "sql");

	AppAdmin() throws SQLException {

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * from Uzytkownicy");
		int i = 0;
		while (rs.next()) {
			///System.out.println(rs.getString(1));
			i++;
		}
	}

	public static void main(String[] args) throws Exception {
		new AppAdmin();
	}

}

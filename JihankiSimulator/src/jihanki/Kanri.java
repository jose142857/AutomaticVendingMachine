package jihanki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// iloveyou
public class Kanri {

	String password = "123456";

	public void zaiko() throws SQLException {

		String sql = "select * from drink";

		// // crate statement
		Statement stmt = JDBCUtils.createStatement();
		// // get data from table
		ResultSet rs = stmt.executeQuery(sql);
		// // show data
		while (rs.next()) {
			System.out.println(rs.getInt("id") + "  " + rs.getString("name") + "  " + rs.getInt("quantity"));

		}
	}

	public void maisu() throws SQLException {
		String sql = "select * from money";

		// // crate statement
		Statement stmt = JDBCUtils.createStatement();
		// // get data from table
		ResultSet rs = stmt.executeQuery(sql);
		// // show data
		while (rs.next()) {
			System.out.println(rs.getInt("type") + "  " + rs.getInt("quantity"));

		}
	}

	public void uriage() throws SQLException {

		Statement stmt = JDBCUtils.createStatement();
		// get data from table
		ResultSet rs = stmt.executeQuery("select uriage from uriage ");
		// show data
		while (rs.next()) {
			System.out.println(rs.getInt("uriage"));

		}

	}
}

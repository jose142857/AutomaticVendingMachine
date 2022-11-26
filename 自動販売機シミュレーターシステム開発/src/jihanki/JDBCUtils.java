package jihanki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	public static Connection connection = null;

	public final static String SQL_EXCEPTION = "Lỗi kết nối SQL";

	// connection取得
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/jihanki";
			String uesrname = "root";
			String password = "";
			connection = DriverManager.getConnection(url, uesrname, password);
		} catch (SQLException e) {
			System.out.println(SQL_EXCEPTION);
			e.printStackTrace();
		}
		return connection;
	}

	// connectionキャンセル
	public static void close() {
		try {
			if (connection == null || connection.isClosed()) {
				return;
			} else
				connection.close();
		} catch (SQLException e) {
			System.out.println(SQL_EXCEPTION);
			e.printStackTrace();
		}
	}

	// パラメータのないステートメント使用
	public static Statement createStatement() {
		Statement statement = null;
		try {
			statement = getConnection().createStatement();
		} catch (SQLException e) {
			System.out.println(SQL_EXCEPTION);
			e.printStackTrace();
		}
		return statement;
	}

	// パラメータがあるステートメント使用
	public static PreparedStatement createPreparedStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println(SQL_EXCEPTION);
			e.printStackTrace();
		}
		return preparedStatement;
	}

}

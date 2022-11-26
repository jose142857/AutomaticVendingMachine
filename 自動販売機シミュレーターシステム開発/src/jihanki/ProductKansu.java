package jihanki;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductKansu {
	//データベースから商品ごとを呼び出す
	public Product getProductById(int id) throws SQLException {
		Product product = null;
		String sql = "SELECT * FROM drink where id = ?";
		PreparedStatement preparedStatement = JDBCUtils.createPreparedStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			 product  = new Product(resultSet.getInt("id"), resultSet.getString("name"),
					resultSet.getInt("value"), resultSet.getInt("quantity"));
		}
		return product;
}
	}

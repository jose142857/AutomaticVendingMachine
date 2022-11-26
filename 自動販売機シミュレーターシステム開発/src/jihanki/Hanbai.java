package jihanki;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hanbai {

	int input1000, input10, input50, input100, input500, sum;
	String select_num;
	int money10 , money50 , money100 , money500 , money1000 ;
	int uriage = 0;
	int a, b, c, d, e;
	Scanner sc = new Scanner(System.in);
	//在庫にあるお金のデータを取得し、変数に代入する。
	public void getMoneyQuantityList() throws SQLException {

		List<Integer> Quantity_List = new ArrayList<Integer>();
		int money;
		Statement stmt = JDBCUtils.createStatement();

		ResultSet rs2 = stmt.executeQuery("select quantity from money");

		while (rs2.next()) {

			money = rs2.getInt("quantity");
			Quantity_List.add(money);

		}
		money10 = Quantity_List.get(0);
		money50 = Quantity_List.get(1);
		money100 = Quantity_List.get(2);
		money500 = Quantity_List.get(3);
		money1000 = Quantity_List.get(4);
	}
	//商品メニュー表示
	public void showMenu() throws SQLException {

		String sql = "select * from drink ";
		Statement stmt = JDBCUtils.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("-----------------------------------------");
		System.out.println("商品一覧\n");
		while (rs.next()) {
			if (rs.getInt("quantity") == 0) {
				System.out.println(rs.getString("name")+ "は売り切れです。");
			}else{
//			System.out.printf(rs.getInt("id") + "  " + rs.getString("name") + "  " + rs.getInt("value")+"  " );
			System.out.printf("%d\t\t%-25s\t\t%d円\t\t\n", rs.getInt("id"),rs.getString("name"),rs.getInt("value"));
		}
		}
		System.out.println("-----------------------------------------");
	}
		//商品のデータ更新
	public void updateProductQuantity(int quantity, int id) throws SQLException {

		String sql = "update drink set quantity  = ? where id = ? ";
		PreparedStatement ps = JDBCUtils.createPreparedStatement(sql);

		ps.setInt(1, quantity);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	//お金のデータ更新
	public void updateMoneyQuantity(int quantity, int type) throws SQLException {

		String sql = "update money set quantity  = ? where type = ? ";
		PreparedStatement ps = JDBCUtils.createPreparedStatement(sql);

		ps.setInt(1, quantity);
		ps.setInt(2, type);
		ps.executeUpdate();
	}
	//商品選択
	public void productSelect(Product product) throws SQLException {

		if (sum >= product.value && product.quantity >0) {
			System.out.println(product.name + "を購入しました。");

			sum = sum - product.value;
			uriage += product.value;
			product.quantity--;
			updateProductQuantity(product.quantity, product.id);

			if (sum > 0) {
				System.out.println("お釣り：" + sum);
			}
		}else {
			System.out.println("購入不可です。");

		}
	}
	//入金
	public int nyukin() throws SQLException {
		do {
			Hanbai hanbai = new Hanbai();
			sum = 0;
			getMoneyQuantityList();

			System.out.println("お金を投入してください（上限1990円）");

			System.out.print("10円：");
			input10 = sc.nextInt();
			money10 += input10;

			System.out.print("50円：");
			input50 = sc.nextInt();
			money50 += input50;

			System.out.print("100円：");
			input100 = sc.nextInt();
			money100 += input100;

			System.out.print("500円：");
			input500 = sc.nextInt();
			money500 += input500;

			System.out.print("1000円：");
			input1000 = sc.nextInt();
			money1000 += input1000;

			hanbai.updateMoneyQuantity(money10,10);
			hanbai.updateMoneyQuantity(money50,50);
			hanbai.updateMoneyQuantity(money100,100);
			hanbai.updateMoneyQuantity(money500,500);
			hanbai.updateMoneyQuantity(money1000,1000);


			sum = 10 * input10 + 50 * input50 + 100 * input100 + 500 * input500 + 1000 * input1000;

			PreparedStatement ps1 = JDBCUtils.createPreparedStatement("update uriage set sum  = ? ");
			ps1.setInt(1,sum);
			ps1.executeUpdate();

			System.out.println("合計=" + sum);
			if (sum > 1990) {
				System.out.println("投入金額が上限金額を超えています。");
			}
			if (sum < 110) {
				System.out.println("投入金額が最安商品より少ないです。");
			}

		} while (sum > 1990 || sum < 110);



		return sum;
	}
	//商品購入
public void kounyu() throws SQLException {

	Hanbai hanbai = new Hanbai();
//	PreparedStatement ps2 = JDBCUtils.createPreparedStatement("update uriage set uriage  = ? ");
//	ps2.setInt(1,uriage);
//	ps2.executeUpdate();

	ProductKansu productKansu = new ProductKansu();
	Product product1 = productKansu.getProductById(1);
	Product product2 = productKansu.getProductById(2);
	Product product3 = productKansu.getProductById(3);
	Product product4 = productKansu.getProductById(4);
	Product product5 = productKansu.getProductById(5);

	getMoneyQuantityList();
		do {
			System.out.println("-----------------------------------------");
			System.out.println("購入可能商品一覧\n");
			Statement st = JDBCUtils.createStatement();
			ResultSet rs3 = st.executeQuery("select * from drink,uriage where  quantity > 0 and value <= uriage.sum  ");

		while (rs3.next()) {
//			System.out.println(
//					rs3.getInt("id") + "  " + rs3.getString("name") + " " + rs3.getInt("value"));
			System.out.printf("%d\t\t%-25s\t\t%d円\t\t\n", rs3.getInt("id"),rs3.getString("name"),rs3.getInt("value"));
		}
		System.out.println("購入商品を選択してください（商品番号を入力、キャンセル場合は「0」を入力） ");
		select_num = sc.next();

		if (select_num.equals("1")) {
			productSelect(product1);

		} else if (select_num.equals("2")) {
			productSelect(product2);

		} else if (select_num.equals("3")) {
			productSelect(product3);

		}else if(select_num.equals("4")){
			productSelect(product4);

		}else if(select_num.equals("5")){
			productSelect(product5);

		}else if(select_num.equals("0")){
			System.out.println("購入をやめました");
			break;

		}else{
			System.out.println("再選択してください");
		}
		System.out.println("売上 :"+uriage);

		}while(sum>=110);

		PreparedStatement ps3 = JDBCUtils.createPreparedStatement("update uriage set uriage  = ? ");
		ps3.setInt(1,uriage);
		ps3.executeUpdate();

		a = sum/1000 ; b=(sum%1000)/500;
		c = ((sum%1000)%500)/100; d=(((sum%1000)%500)%100)/50;
	    e = ((((sum%1000)%500)%100)%50)/10;

		money1000-=a;
		money500-=b;
		money100-=c;
		money50-=d;
		money10-=e;

		hanbai.updateMoneyQuantity(money10,10);
		hanbai.updateMoneyQuantity(money50,50);
		hanbai.updateMoneyQuantity(money100,100);
		hanbai.updateMoneyQuantity(money500,500);
		hanbai.updateMoneyQuantity(money1000,1000);

	}
}
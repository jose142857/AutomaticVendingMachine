package jihanki;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Hanbai hanbai = new Hanbai();

		hanbai.getMoneyQuantityList();

		int sosa1, sosa2;

		Kanri kanri = new Kanri();

		label: do {
			//おつりが足りない場合は準備中状況になる
			if (hanbai.money10 < 4 || hanbai.money100 < 4 || hanbai.money50 < 1 || hanbai.money500 < 1) {
				System.out.println("準備中");
				break;
			} else {
				System.out.println("操作を選んでください：");
				System.out.println("1.管理業務");
				System.out.println("2.商品購入");
				sosa1 = sc.nextInt();

				String pw;
				int count = 0;
				switch (sosa1) {
				case 1:

					do {
						//パスワード確認
						System.out.println("パスワード入力してください");
						pw = sc.next();
						if (pw.equals(kanri.password)) {
							break;
						}

						else {
							System.out.println("パスワードが正しくありません。");
							count++;

						}

					} while (count < 3);//パスワードは間違えて入力3回まで

					if (count == 3) {
						continue label;
					}

					System.out.println("管理操作を選んでください：");
					System.out.println("1.在庫確認");
					System.out.println("2.枚数確認");
					System.out.println("3.売上確認");
					sosa2 = sc.nextInt();

					switch (sosa2) {
					case 1:
						//在庫確認
						kanri.zaiko();
						break;
					case 2:
					//枚数確認
						kanri.maisu();
						;
						break;
					case 3:
						//売上確認
						kanri.uriage();
						break;
					default:
						break;
					}
					break;
				case 2:
					//販売kクラスの関数を呼び出す
					hanbai.showMenu();
					hanbai.nyukin();
					hanbai.kounyu();

				}
			}

		} while (true);

	}
}

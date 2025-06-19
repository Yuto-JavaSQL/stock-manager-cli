package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

import dao.ProductDao;
import model.Product;

public class Main {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/inventory_db?serverTimezone=JST";
		String user = "devuser";
		String password = "invpass";
		
		try(Connection conn = DriverManager.getConnection(url, user, password);
			Scanner scanner = new Scanner(System.in)){
			
			ProductDao productDao = new ProductDao(conn);
		
			while (true) {
				System.out.println("\n==商品管理システム ==");
				System.out.println("1. 商品一覧表示");
				System.out.println("2. 商品登録");
				System.out.println("0. 終了");
				System.out.println("選択肢を入力してください");
			
				String input = scanner.nextLine();
			
				switch (input) {
				case "1":
					List<Product> products = productDao.findAll();
					for (Product p : products) {
						System.out.printf("ID: %d / 名前: %s / 価格: %.2f / 在庫: %d\n",
								p.getProductId(), p.getProductName(), p.getPrice(), p.getStockQuantity());
					}
					break;
			
				case "2":
					System.out.print("商品名：");
					String name = scanner.nextLine();
					System.out.print("価格：");
					double price = Double.parseDouble(scanner.nextLine());
					System.out.print("在庫数：");
					int stock = Integer.parseInt(scanner.nextLine());
				
					Product newProduct = new Product();
					newProduct.setProductName(name);
					newProduct.setPrice(price);
					newProduct.setStockQuantity(stock);
				
					productDao.insert(newProduct);
					System.out.println("商品を登録しました。");
					break;
				
				case "0":
					System.out.println("終了します。");
					return;
				
				default:
					System.out.println("無効な入力です。");
				}
			}
		}catch (Exception e) {
		System.out.println("エラー："+ e.getMessage());
		e.printStackTrace();
		}
	}
}

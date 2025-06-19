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
				System.out.println("3. 商品更新");
				System.out.println("4. 商品削除");
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
					
				case "3":
					int updateId = readInt(scanner, "更新する商品のID：");
					System.out.print("新しい商品名：");
					String newName = scanner.nextLine();
					double newPrice = readDouble(scanner, "新しい価格：");
					int newStock = readInt(scanner, "新しい在庫数：");
					
					Product updateProduct = new Product(updateId, newName, newPrice, newStock);
					productDao.update(updateProduct);
					break;
					
				case "4":
					int deleteId = readInt(scanner, "削除する商品のID：");
					productDao.delete(deleteId);
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

	//入力補助メソッド（整数）
	private static int readInt(Scanner scanner, String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return Integer.parseInt(scanner.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("数値を入力してください。");
			}
		}
	}

	private static double readDouble(Scanner scanner, String prompt) {
		while(true) {
			try {
				System.out.print(prompt);
				return Double.parseDouble(scanner.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("数値を入力してください。");
			}
		}
	}
}

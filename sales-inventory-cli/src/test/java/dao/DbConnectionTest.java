package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/inventory_db";
		String user = "devuser";
		String password = "invpass";
		
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			if (conn != null) {
				System.out.println("MySQL接続成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MySQL接続失敗"+ e.getMessage());
		}
	}
}

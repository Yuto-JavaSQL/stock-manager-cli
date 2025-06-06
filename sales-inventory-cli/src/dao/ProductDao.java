package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDao {
	private Connection conn;
	
	//コンストラクタ
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<Product> findAll() throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM puducts";
		try (PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Product p = new Product(
							rs.getInt("product_id"),
							rs.getString("product_name"),
							rs.getDouble("price"),
							rs.getInt("stock_quantity")
					);
					list.add(p);
							
				}
				return list;
		}
	}
	
	public void insert(Product product) throws SQLException {
		String sql = "INSERT INTO product (product_name, price, stock_quantity) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, product.getProductName());
			stmt.setDouble(1, product.getPrice());
			stmt.setInt(1, product.getStockQuantity());
			stmt.executeQuery();
			
		}
	}

}

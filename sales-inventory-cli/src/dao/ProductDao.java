package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDao {
	private final Connection conn;
	
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<Product> findAll() throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM products";
		
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
		String sql = "INSERT INTO products (product_name, price, stock_quantity) VALUES (?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, product.getProductName());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getStockQuantity());
			stmt.executeUpdate();
			
		}
	}
	
	public void update(Product product) {
		String sql = "UPDATE products SET product_name = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
		try(PreparedStatement pstm = conn.prepareStatement(sql)) {
				
				pstm.setString(1, product.getProductName());
				pstm.setDouble(2, product.getPrice());
				pstm.setInt(3, product.getStockQuantity());
				pstm.setInt(4, product.getProductId());
				
				int rowsAffected = pstm.executeUpdate();
				if(rowsAffected > 0) {
					System.out.println("商品情報を更新しました。");
				}else {
					System.out.println("指定したIDの商品が見つかりません。");
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int productId) {
		String sql = "DELETE FROM products WHERE product_id = ?";
		try(PreparedStatement pstm = conn.prepareStatement(sql)){
			
			pstm.setInt(1, productId);
			int rowsAffected = pstm.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("商品を削除しました。");
			}else {
				System.out.println("指定したIDの商品が見つかりません。");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

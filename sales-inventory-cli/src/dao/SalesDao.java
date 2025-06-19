package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Sales;

public class SalesDao {
	
	private final Connection conn;
	
	public SalesDao(Connection conn) {
		this.conn =  conn;
	}
	
	//販売情報の登録
	public void insert(Sales sales) throws SQLException {
		String sql = "INSERT INTO sales (product_id, quantity_sold, sale_date) VALUES(?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, sales.getProductId());
			stmt.setInt(2, sales.getQuantity());
			stmt.setTimestamp(3, Timestamp.valueOf(sales.getSaleDate()));
			stmt.executeUpdate();
		}
	}
	
	//全販売履歴の取得
	public List<Sales> findAll() throws SQLException {
		List<Sales> list = new ArrayList<>();
		String sql = "SELECT * FROM sales ORDER BY sale_date DESC";
		try(PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()){
			
			while (rs.next()) {
				Sales s = new Sales();
				s.setSaleId(rs.getInt("sales_id"));
				s.setProductId(rs.getInt("product_id"));
				s.setQuantity(rs.getInt("quantity_sold"));
				s.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
				list.add(s);
			}
		}
		return list;
	}

}

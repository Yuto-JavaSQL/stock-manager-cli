package dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import model.Product;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDaoTest {
	
	private Connection conn;
	private ProductDao productDao;

	@BeforeAll
	public void setup() throws Exception {
		String url = "jdbc:mysql://localhost:3306/inventory_db";
		String user = "devuser";
		String password = "invpass";
		
		conn = DriverManager.getConnection(url, user, password);
		productDao = new ProductDao(conn);
	}
	
	@AfterAll
	public void teardown() throws Exception {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
	
	
	@Test
	public void testInsertAndFindAll() throws Exception {
		Product product = new Product();
		product.setProductName("テスト商品");
		product.setPrice(100.0);
		product.setStockQuantity(10);
		
		productDao.insert(product);
		
		List<Product> list = productDao.findAll();
		
		boolean found = list.stream().anyMatch(p -> "テスト商品".equals(p.getProductName()) && p.getPrice() == 100.0);
		
		assertTrue(found, "挿入した商品が見つかるはず");
	}
	
	@Test
	public void testInsertMultipleAndFindAll() throws Exception {
		//商品を挿入
		Product product1 = new Product();
		product1.setProductName("商品A");
		product1.setPrice(50.0);
		product1.setStockQuantity(5);
		productDao.insert(product1);
		
		Product product2 = new Product();
		product2.setProductName("商品B");
		product2.setPrice(150.0);
		product2.setStockQuantity(20);
		productDao.insert(product2);
		
		List<Product> list = productDao.findAll();
		
		boolean foundA = list.stream().anyMatch(p -> "商品A".equals(p.getProductName()) && p.getPrice() == 50.0);
		boolean foundB = list.stream().anyMatch(p -> "商品B".equals(p.getProductName()) && p.getPrice() == 150.0);
		
		assertTrue(foundA, "商品Aが見つかるはず");
		assertTrue(foundB, "商品Bが見つかるはず");
	}
}

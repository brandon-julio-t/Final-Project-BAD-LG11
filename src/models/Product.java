package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import facades.Database;

public class Product {
	private int ClothingId;
	private int ClothingSizeId;
	private int ClothingTypeId;
	private String ClothingName;
	private int ClothingPrice;
	private int ClothingStock;

	public static Vector<Product> getAll() {
		Vector<Product> products = new Vector<Product>();
		ResultSet rs = Database.getInstance().executeQuery("SELECT * FROM clothing");
		try {
			while (rs.next()) {
				products.add(
						new Product(rs.getInt("ClothingId"), rs.getInt("ClothingSizeId"), rs.getInt("ClothingTypeId"),
								rs.getString("ClothingName"), rs.getInt("ClothingPrice"), rs.getInt("ClothingStock")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public static void insertProduct(String nameField, int clothingTypeList, int sizeList, int priceField, int quantityField) {
		PreparedStatement ps = Database.getInstance().prepareStatement("INSERT INTO clothing(ClothingSizeId, ClothingTypeId, ClothingName, ClothingPrice, ClothingStock) VALUES (?,?,?,?,?)");

		try {
			ps.setInt(1, sizeList);
			ps.setInt(2, clothingTypeList);
			ps.setString(3, nameField);
			ps.setInt(4, priceField);
			ps.setInt(5, quantityField);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProduct(int clothId, String nameField, int clothingTypeList, int sizeList, int priceField, int quantityField) {
		PreparedStatement ps = Database.getInstance().prepareStatement("UPDATE clothing SET ClothingSizeId = ?, ClothingTypeId = ?, ClothingName = ?, ClothingPrice = ?, ClothingStock = ? WHERE ClothingId = ?");

		try {
			ps.setInt(1, sizeList);
			ps.setInt(2, clothingTypeList);
			ps.setString(3, nameField);
			ps.setInt(4, priceField);
			ps.setInt(5, quantityField);
			ps.setInt(6, clothId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteProduct(int clothId) {
		PreparedStatement ps = Database.getInstance().prepareStatement("DELETE FROM clothing WHERE ClothingId = ?");

		try {
			ps.setInt(1, clothId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int getClothingId() {
		return ClothingId;
	}

	public void setClothingId(int clothingId) {
		this.ClothingId = clothingId;
	}

	public int getClothingSizeId() {
		return ClothingSizeId;
	}

	public void setClothingSizeId(int clothingSizeId) {
		this.ClothingSizeId = clothingSizeId;
	}

	public int getClothingTypeId() {
		return ClothingTypeId;
	}

	public void setClothingTypeId(int clothingTypeId) {
		this.ClothingTypeId = clothingTypeId;
	}

	public String getClothingName() {
		return ClothingName;
	}

	public void setClothingName(String clothingName) {
		this.ClothingName = clothingName;
	}

	public int getClothingPrice() {
		return ClothingPrice;
	}

	public void setClothingPrice(int clothingPrice) {
		this.ClothingPrice = clothingPrice;
	}

	public int getClothingStock() {
		return ClothingStock;
	}

	public void setClothingStock(int clothingStock) {
		this.ClothingStock = clothingStock;
	}

	public Product(int clothingId, int clothingSizeId, int clothingTypeId, String clothingName, int clothingPrice, int clothingStock) {
		super();
		this.ClothingId = clothingId;
		this.ClothingSizeId = clothingSizeId;
		this.ClothingTypeId = clothingTypeId;
		this.ClothingName = clothingName;
		this.ClothingPrice = clothingPrice;
		this.ClothingStock = clothingStock;
	}

}

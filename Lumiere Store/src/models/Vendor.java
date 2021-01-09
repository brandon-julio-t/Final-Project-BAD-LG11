package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import facades.Database;

public class Vendor {
	private int VendorId;
	private String VendorName;
	private String VendorEmail;
	private String VendorAddress;
	private String VendorPhoneNumber;

	public static Vector<Vendor> getAll() {
		Vector<Vendor> vendors = new Vector<Vendor>();
		ResultSet rs = Database.getInstance().executeQuery("SELECT * FROM vendor");
		try {
			while (rs.next()) {
				vendors.add(
						new Vendor(rs.getInt("VendorId"), rs.getString("VendorName"), rs.getString("VendorEmail"),
								rs.getString("VendorAddress"), rs.getString("VendorPhoneNumber")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	}

	public static void insertVendor(String nameField, String emailField, String addressField, String phoneField) {
		PreparedStatement ps = Database.getInstance().prepareStatement("INSERT INTO vendor(VendorName, VendorEmail, VendorAddress, VendorPhoneNumber) VALUES (?,?,?,?)");

		try {
			ps.setString(1, nameField);
			ps.setString(2, emailField);
			ps.setString(3, addressField);
			ps.setString(4, phoneField);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateVendor(int vendorId, String nameField, String emailField, String addressField, String phoneField) {
		PreparedStatement ps = Database.getInstance().prepareStatement("UPDATE vendor SET VendorName = ?, VendorEmail = ?, VendorAddress = ?, VendorPhoneNumber = ? WHERE VendorId = ?");

		try {
			ps.setString(1, nameField);
			ps.setString(2, emailField);
			ps.setString(3, addressField);
			ps.setString(4, phoneField);
			ps.setInt(5, vendorId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public static void deleteVendor(int vendorId) {
		PreparedStatement ps = Database.getInstance().prepareStatement("DELETE FROM vendor WHERE VendorId = ?");

		try {
			ps.setInt(1, vendorId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public int getVendorId() {
		return VendorId;
	}
	public void setVendorId(int vendorId) {
		this.VendorId = vendorId;
	}
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		this.VendorName = vendorName;
	}
	public String getVendorEmail() {
		return VendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.VendorEmail = vendorEmail;
	}
	public String getVendorAddress() {
		return VendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.VendorAddress = vendorAddress;
	}
	public String getVendorPhoneNumber() {
		return VendorPhoneNumber;
	}
	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.VendorPhoneNumber = vendorPhoneNumber;
	}
	public Vendor(int vendorId, String vendorName, String vendorEmail, String vendorAddress, String vendorPhoneNumber) {
		super();
		this.VendorId = vendorId;
		this.VendorName = vendorName;
		this.VendorEmail = vendorEmail;
		this.VendorAddress = vendorAddress;
		this.VendorPhoneNumber = vendorPhoneNumber;
	}

	@Override
	public String toString() {
		return getVendorName();
	}
}

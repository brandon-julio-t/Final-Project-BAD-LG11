package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SaleHistory {
    private Integer clothingId;
    private String clothingName;
    private ClothingType clothingType;
    private Date saleDate;
    private Integer salePrice;
    private Integer saleQuantity;

    public SaleHistory(ResultSet resultSet) throws SQLException {
        clothingId = resultSet.getInt("ClothingId");
        clothingName = resultSet.getString("ClothingName");
        clothingType = new ClothingType(resultSet);
        saleDate = resultSet.getDate("SaleDate");
        salePrice = resultSet.getInt("SalePrice");
        saleQuantity = resultSet.getInt("SaleQuantity");
    }

    public Integer getClothingId() {
        return clothingId;
    }

    public void setClothingId(Integer clothingId) {
        this.clothingId = clothingId;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Integer saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Integer getSubTotal() {
        return getSaleQuantity() * getSalePrice();
    }
}

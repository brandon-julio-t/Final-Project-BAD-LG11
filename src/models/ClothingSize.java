package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothingSize {
    private int clothingSizeId;
    private String clothingSizeName;

    public ClothingSize(ResultSet resultSet) throws SQLException {
        clothingSizeId = resultSet.getInt("ClothingSizeId");
        clothingSizeName = resultSet.getString("ClothingSizeName");
    }

    public int getClothingSizeId() {
        return clothingSizeId;
    }

    public void setClothingSizeId(int clothingSizeId) {
        this.clothingSizeId = clothingSizeId;
    }

    public String getClothingSizeName() {
        return clothingSizeName;
    }

    public void setClothingSizeName(String clothingSizeName) {
        this.clothingSizeName = clothingSizeName;
    }

    @Override
    public String toString() {
        return getClothingSizeName();
    }
}

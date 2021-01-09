package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothingType {
    private int clothingTypeId;
    private String clothingTypeName;

    public ClothingType(ResultSet resultSet) throws SQLException {
        clothingTypeId = resultSet.getInt("ClothingTypeId");
        clothingTypeName = resultSet.getString("ClothingTypeName");
    }

    public int getClothingTypeId() {
        return clothingTypeId;
    }

    public void setClothingTypeId(int clothingTypeId) {
        this.clothingTypeId = clothingTypeId;
    }

    public String getClothingTypeName() {
        return clothingTypeName;
    }

    public void setClothingTypeName(String clothingTypeName) {
        this.clothingTypeName = clothingTypeName;
    }

    @Override
    public String toString() {
        return getClothingTypeName();
    }
}

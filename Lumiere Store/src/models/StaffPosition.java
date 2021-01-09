package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffPosition {
    private int staffPositionId;
    private String staffPositionName;

    public StaffPosition(ResultSet resultSet) throws SQLException {
        staffPositionId = resultSet.getInt("StaffPositionId");
        staffPositionName = resultSet.getString("StaffPositionName");
    }

    public int getStaffPositionId() {
        return staffPositionId;
    }

    public void setStaffPositionId(int staffPositionId) {
        this.staffPositionId = staffPositionId;
    }

    public String getStaffPositionName() {
        return staffPositionName;
    }

    public void setStaffPositionName(String staffPositionName) {
        this.staffPositionName = staffPositionName;
    }
}

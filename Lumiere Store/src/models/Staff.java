package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.abstracts.User;

public class Staff extends User {
    private StaffPosition staffPosition;
    private Integer staffSalary;
    private String npwp;

    public Staff(ResultSet resultSet) throws SQLException {
        super(resultSet);
        staffPosition = new StaffPosition(resultSet);
        staffSalary = resultSet.getInt("StaffSalary");
        npwp = resultSet.getString("npwp");
    }

    public StaffPosition getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(StaffPosition staffPosition) {
        this.staffPosition = staffPosition;
    }

    public Integer getStaffSalary() {
        return staffSalary;
    }

    public void setStaffSalary(Integer staffSalary) {
        this.staffSalary = staffSalary;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }
}

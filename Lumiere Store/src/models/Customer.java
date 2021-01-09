package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.abstracts.User;

public class Customer extends User {
    private Integer customerPoint;

    public Customer(ResultSet resultSet) throws SQLException {
        super(resultSet);
        customerPoint = resultSet.getInt("CustomerPoint");
    }

    public Integer getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(Integer customerPoint) {
        this.customerPoint = customerPoint;
    }
}

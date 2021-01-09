package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.abstracts.User;

public class Customer extends User {
    private String customerId;

    public Customer(ResultSet resultSet) throws SQLException {
        super(resultSet);
        customerId = resultSet.getString("CustomerId");
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

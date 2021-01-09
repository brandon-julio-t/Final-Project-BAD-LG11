package models.abstracts;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Staff;

public abstract class User {
    private Integer userId;
    private String userName;
    private String userGender;
    private String userAddress;
    private String userPhoneNumber;
    private String userEmail;
    private String userPassword;

    public User(ResultSet resultSet) throws SQLException {
        userId = resultSet.getInt("UserId");
        userName = resultSet.getString("UserName");
        userGender = resultSet.getString("UserGender");
        userAddress = resultSet.getString("UserAddress");
        userPhoneNumber = resultSet.getString("UserPhoneNumber");
        userEmail = resultSet.getString("UserEmail");
        userPassword = resultSet.getString("UserPassword");
    }

    public Boolean isAdmin() {
        if (this instanceof Staff) {
            Staff staff = (Staff) this;
            return staff.getStaffPosition().getStaffPositionName().equals("admin");
        }

        return false;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import facades.Database;
import interfaces.CrudRepository;
import models.Customer;

public class CustomerRepository implements CrudRepository<Customer> {
    public Optional<Customer> getByEmailAndPassword(String email, String password) {
        try {
            String sql = "select * from `user` u join `customer` c on u.UserId = c.UserId where UserEmail = ? and UserPassword = ?";

            PreparedStatement stmt = Database.getInstance().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Customer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Customer Save(Customer model) {
        return null;
    }

    @Override
    public Customer Update(Customer model) {
        return null;
    }

    @Override
    public Customer Delete(Customer model) {
        return null;
    }

    @Override
    public Collection<Customer> getAll() {
        return null;
    }

    @Override
    public Optional<Customer> getById(int id) {
        return Optional.empty();
    }
}

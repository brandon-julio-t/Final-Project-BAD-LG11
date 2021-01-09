package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import facades.Database;
import interfaces.CrudRepository;
import models.Staff;

public class StaffRepository implements CrudRepository<Staff> {
    public Optional<Staff> getByEmailAndPassword(String email, String password) {
        try {
            String sql = "select * from `user` u join `staff` s on u.UserId = s.UserId join `staffposition` sp on s.StaffPositionId = sp.StaffPositionId where UserEmail = ? and UserPassword = ?";

            PreparedStatement stmt = Database.getInstance().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Staff(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Staff Save(Staff model) {
        return null;
    }

    @Override
    public Staff Update(Staff model) {
        return null;
    }

    @Override
    public Staff Delete(Staff model) {
        return null;
    }

    @Override
    public Collection<Staff> getAll() {
        return null;
    }

    @Override
    public Optional<Staff> getById(int id) {
        return Optional.empty();
    }
}

package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import facades.Database;
import interfaces.ReadonlyRepository;
import models.ClothingSize;

public class ClothingSizeRepository implements ReadonlyRepository<ClothingSize> {
    @Override
    public Collection<ClothingSize> getAll() {
        try {
            ResultSet rs = Database.getInstance().executeQuery("select * from `ClothingSize`");
            Collection<ClothingSize> clothings = new ArrayList<>();

            while (rs.next()) {
                clothings.add(new ClothingSize(rs));
            }

            return clothings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<ClothingSize> getById(int id) {
        try {
            String sql = "select * from `ClothingSize` where `ClothingSizeId` = ?";

            PreparedStatement stmt = Database.getInstance().prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ClothingSize(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

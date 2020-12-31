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
import models.ClothingType;

public class ClothingTypeRepository implements ReadonlyRepository<ClothingType> {
    @Override
    public Collection<ClothingType> getAll() {
        try {
            ResultSet rs = Database.getInstance().executeQuery("select * from `ClothingType`");
            Collection<ClothingType> clothings = new ArrayList<>();

            while (rs.next()) {
                clothings.add(new ClothingType(rs));
            }

            return clothings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<ClothingType> getById(int id) {
        try {
            String sql = "select * from `ClothingType` where `ClothingTypeId` = ?";

            PreparedStatement stmt = Database.getInstance().prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ClothingType(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

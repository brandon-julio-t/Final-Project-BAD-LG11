package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import facades.Authentication;
import facades.Database;
import interfaces.ReadonlyRepository;
import models.SaleHistory;

public class SaleHistoryRepository implements ReadonlyRepository<SaleHistory> {
    @Override
    public Collection<SaleHistory> getAll() {
        try {
            Collection<SaleHistory> entities = new ArrayList<>();
            String sql =
                    "select c.ClothingId, ClothingName, c.ClothingTypeId, ClothingTypeName, SaleDate, SalePrice, SaleQuantity\n" +
                            "from saleheader sh\n" +
                            "         join saledetail sd on sh.SaleId = sd.SaleId\n" +
                            "         join clothing c on sd.ClothingId = c.ClothingId\n" +
                            "         join clothingtype ct on ct.ClothingTypeId = c.ClothingTypeId\n" +
                            "where CustomerId = ?\n" +
                            "  and c.ClothingId not in (\n" +
                            "    select r.ClothingId from review r where c.ClothingId = r.ClothingId\n" +
                            ");";
            PreparedStatement stmt = Database.getInstance().prepareStatement(sql);
            stmt.setInt(1, Authentication.getUser().getUserId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entities.add(new SaleHistory(rs));
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<SaleHistory> getById(int id) {
        return Optional.empty();
    }
}

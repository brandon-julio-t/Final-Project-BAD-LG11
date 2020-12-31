package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import facades.Database;
import interfaces.ReadonlyRepository;
import models.SaleHistory;

public class SaleHistoryRepository implements ReadonlyRepository<SaleHistory> {
    @Override
    public Collection<SaleHistory> getAll() {
        try {
            Collection<SaleHistory> entities = new ArrayList<>();
            String sql =
                    "select c.ClothingId, ClothingName, ClothingPrice, c.ClothingTypeId, ClothingTypeName, SaleDate, SaleQuantity " +
                            "from saleheader sh join saledetail sd on sh.SaleId = sd.SaleId " +
                            "join clothing c on sd.ClothingId = c.ClothingId " +
                            "join clothingtype ct on ct.ClothingTypeId = c.ClothingTypeId";
            ResultSet rs = Database.getInstance().executeQuery(sql);
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

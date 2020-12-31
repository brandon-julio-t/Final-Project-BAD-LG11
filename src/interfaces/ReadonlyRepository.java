package interfaces;

import java.util.Collection;
import java.util.Optional;

public interface ReadonlyRepository<T> {
    Collection<T> getAll();
    Optional<T> getById(int id);
}

package interfaces;

public interface CrudRepository<T> extends ReadonlyRepository<T> {
    T Save(T model);
    T Update(T model);
    T Delete(T model);
}

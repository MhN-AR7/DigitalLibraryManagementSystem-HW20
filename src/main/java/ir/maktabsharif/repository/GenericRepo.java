package ir.maktabsharif.repository;

import ir.maktabsharif.model.BaseModel;

import java.util.Optional;

public interface GenericRepo<T extends BaseModel<ID>, ID> {
    void insert(T t);
    Optional<T> findById(ID id);
    boolean update(T t);
    boolean delete(ID id);
}

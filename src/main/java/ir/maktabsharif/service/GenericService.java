package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.BaseModel;

public interface GenericService<T extends BaseModel<ID>, ID> {
    T register(T t) throws BusinessException;
    T getById(ID id) throws BusinessException;
    T update(T t) throws BusinessException;
    void delete(ID id) throws BusinessException;
    void validate(T t) throws BusinessException;
}

package org.azure.database.dao;

import java.util.List;

/**
 * Created by adil on 25/11/15.
 */
public interface IGenericDAO<T,K> {

    public T get(K id);
    public void save(T entity);
    public T update(T entity);
    public void delete(T entity);
    public List<T> getByProperty(String property, Object value);
    public T getByPropertyUnique(String property, Object value);
}

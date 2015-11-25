package org.azure.database.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by adil on 25/11/15.
 */

public class GenericHibernateDAO<T,K> implements IGenericDAO<T,K> {

    private SessionFactory sessionFactory;
    private Class<T> clazz;

    public GenericHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
    @Override
    public T get(K id) {
        T entity = sessionFactory.getCurrentSession().get(clazz,(Serializable)id);
        return entity;
    }

    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public T update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity; // TODO - Does Hibernate update the class reference too?
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<T> getByProperty(String property, Object value) {
        Session hSession = sessionFactory.getCurrentSession();
        Criteria criteria = hSession.createCriteria(this.clazz);
        criteria.add(Restrictions.eq(property,value));
        return criteria.list();

    }

    @Override
    public T getByPropertyUnique(String property, Object value) {
        return (T)sessionFactory.getCurrentSession().createCriteria(this.clazz).add(
                Restrictions.eq(property, value)
        ).uniqueResult();
    }
}

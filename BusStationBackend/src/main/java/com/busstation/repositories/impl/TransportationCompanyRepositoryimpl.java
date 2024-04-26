package com.busstation.repositories.impl;

import com.busstation.pojo.Role;
import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TransportationCompanyRepositoryimpl implements TransportationCompanyRepository {
    @Autowired
    private  LocalSessionFactoryBean sessionFactory;

    @Override
    public TransportationCompany getTransportationCompanyById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TransportationCompany> criteria = builder.createQuery(TransportationCompany.class);
        Root root = criteria.from(TransportationCompany.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        Query query = session.createQuery(criteria);
        return (TransportationCompany) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void saveTransportationCompany(TransportationCompany newtransportationCompany) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(newtransportationCompany);
    }

    @Override
    public void updateTransportationCompany(TransportationCompany transportationCompany) {
        Session session = sessionFactory.getObject().getCurrentSession();
        TransportationCompany existingCompany = session.byId(TransportationCompany.class).load(transportationCompany.getId());
        existingCompany.setName(transportationCompany.getName());
        existingCompany.setAvatar(transportationCompany.getAvatar());
        existingCompany.setPhone(transportationCompany.getPhone());
        existingCompany.setEmail(transportationCompany.getEmail());
        session.flush();
    }

    @Override
    public void deleteTransportationCompany(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        TransportationCompany transportationCompany = session.byId(TransportationCompany.class).load(id);
        if (transportationCompany != null) {
            session.delete(transportationCompany);
        }
    }
}

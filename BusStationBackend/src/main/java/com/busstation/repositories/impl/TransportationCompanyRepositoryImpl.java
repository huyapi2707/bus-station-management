package com.busstation.repositories.impl;

import com.busstation.pojo.TransportationCompany;
import com.busstation.repositories.TransportationCompanyRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@PropertySource("classpath:configuration.properties")
public class TransportationCompanyRepositoryImpl implements TransportationCompanyRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Autowired
    Environment environment;

    @Override
    public List<TransportationCompany> getAll(Map<String, String> params) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<TransportationCompany> criteriaQuery = builder.createQuery(TransportationCompany.class);
        Root root = criteriaQuery.from(TransportationCompany.class);
        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(builder.like(root.get("name"),  String.format("%%%s%%", kw)));
        }

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(criteriaQuery);

        String page = params.get("page");
        if (page != null && !page.isEmpty()) {
            int pageSize = Integer.parseInt(environment.getProperty("transportationCompany.pageSize").toString());
            int start = (Integer.parseInt(page) -1 ) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);

        }
        List<TransportationCompany> result = query.getResultList();
        return result;
    }

    @Override
    public TransportationCompany getTransportationCompanyById(int id) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
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
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        session.persist(newtransportationCompany);
    }

    @Override
    public void updateTransportationCompany(TransportationCompany transportationCompany) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        TransportationCompany existingCompany = session.byId(TransportationCompany.class).load(transportationCompany.getId());
        existingCompany.setName(transportationCompany.getName());
        existingCompany.setAvatar(transportationCompany.getAvatar());
        existingCompany.setPhone(transportationCompany.getPhone());
        existingCompany.setEmail(transportationCompany.getEmail());
        session.flush();
    }

    @Override
    public void deleteTransportationCompany(int id) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        TransportationCompany transportationCompany = session.byId(TransportationCompany.class).load(id);
        if (transportationCompany != null) {
            session.delete(transportationCompany);
        }
    }

    @Override
    public void verifiedTransportationCompany(int id) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            TransportationCompany transportationCompany = session.byId(TransportationCompany.class).load(id);
            if (transportationCompany != null) {
                transportationCompany.setIsVerified(!transportationCompany.getIsVerified());
                session.update(transportationCompany);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

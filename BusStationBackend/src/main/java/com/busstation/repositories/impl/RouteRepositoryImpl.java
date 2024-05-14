package com.busstation.repositories.impl;

import com.busstation.pojo.Route;
import com.busstation.repositories.RouteRepository;
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
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Autowired
    private Environment environment;

    @Override
    public List<Route> getAll(Map<String, String> params) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Route> criteriaQuery = builder.createQuery(Route.class);
        Root<Route> root = criteriaQuery.from(Route.class);
        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(builder.like(root.get("name"), "%" + kw + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        Query query = session.createQuery(criteriaQuery);

        String page = params.get("page");
        if (page != null && !page.isEmpty()) {
            int pageSize = Integer.parseInt(environment.getProperty("route.pageSize"));
            int start = (Integer.parseInt(page) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public Route getRouteById(Long id) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        return session.get(Route.class, id);
    }

    @Override
    public void saveRoute(Route route) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        session.saveOrUpdate(route);
    }

    @Override
    public void updateRoute(Route route) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        Route existingRoute = session.byId(Route.class).load(route.getId());
        if (existingRoute != null) {
            existingRoute.setName(route.getName());
            session.flush();
        }
    }

    @Override
    public void deleteRoute(Long id) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        Route route = session.byId(Route.class).load(id);
        if (route != null) {
            session.delete(route);
        }
    }
}

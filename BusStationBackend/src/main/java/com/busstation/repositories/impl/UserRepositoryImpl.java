package com.busstation.repositories.impl;

import com.busstation.pojo.User;
import com.busstation.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public User getUserByUserName(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("username"), username));
        Query query = session.createQuery(criteriaQuery);

        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void saveUser(User newUser) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.save(newUser);
    }
}

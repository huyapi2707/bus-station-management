package com.busstation.repositories.impl;

import com.busstation.pojo.PaymentMethod;
import com.busstation.repositories.PaymentMethodRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<PaymentMethod> getAll() {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        return session.createQuery("SELECT a FROM PaymentMethod a ", PaymentMethod.class).getResultList();
    }
}

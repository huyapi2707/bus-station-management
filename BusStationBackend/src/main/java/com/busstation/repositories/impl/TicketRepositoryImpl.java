package com.busstation.repositories.impl;

import com.busstation.pojo.OnlinePaymentResult;
import com.busstation.pojo.Ticket;
import com.busstation.repositories.TicketRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    @Override
    public void saveAll(List<Ticket> tickets) {
        Session session = localSessionFactoryBean.getObject().getCurrentSession();
        tickets.forEach(session::save);
    }

    @Override
    public void updateAll(List<Ticket> tickets) {
        Session session = localSessionFactoryBean.getObject().getCurrentSession();
        tickets.forEach(session::update);
    }


}

package com.rostylka.newlib.dao;

import com.rostylka.newlib.models.User;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDao {

    private SessionFactory sf;

    @Transactional
    public List<User> readAll() {
        return sf.getCurrentSession().createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    public void createUser(User user) {
        sf.getCurrentSession().persist(user);

    }

    @Transactional
    public User readById(int id) {
        return sf.getCurrentSession().get(User.class, id);
    }

    @Transactional
    public void update(User user) {
        sf.getCurrentSession().merge(user);
    }

    @Transactional
    public void delete(User user) {
        sf.getCurrentSession().remove(user);
    }

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}

package com.zkproject.dao;

import com.zkproject.HibernateUtil;
import com.zkproject.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao extends GenericDao<User> {

    public static User findByEmail(String email) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query que=ses.createQuery("FROM User b WHERE b.email= :v ");
        que.setString("v", email);
        List list=que.list();
        if(list.size() > 0) {
            ses.close();
            return (User) list.get(0);
        }
        return null;
    }
}

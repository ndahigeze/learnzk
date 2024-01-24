package com.zkproject.dao;

import com.zkproject.HibernateUtil;
import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoDao extends GenericDao<Todo>  {
    public static List<Todo> findByUser(User user) {
        try{
            Session ses= HibernateUtil.getSessionFactory().openSession();
            Query que=ses.createQuery("FROM Todo b WHERE b.user= :v ");
            que.setInteger("v", user.getId());

            List list= que.list();
            ses.close();
            return (List<Todo>) list;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<Todo>();
        }

    }
}

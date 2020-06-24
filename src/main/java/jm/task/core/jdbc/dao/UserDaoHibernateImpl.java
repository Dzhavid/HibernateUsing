package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

public class UserDaoHibernateImpl implements UserDao {


    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Query sqlQuery = session.createSQLQuery("CREATE TABLE User(Id INT auto_increment primary key ,Name VARCHAR(10)," +
                    "LastName VARCHAR(10),Age INT)");
            sqlQuery.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Query sqlQuery = session.createSQLQuery("DROP TABLE user");
            sqlQuery.executeUpdate();
            transaction.commit();
            session.close();

        } catch (Exception e) {

        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE User WHERE id = " + id);
        transaction.commit();
        session.close();
    }


    @Override
    public List<User> getAllUsers() {
        List list = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query sqlQuery = session.createNativeQuery("SELECT * FROM user", User.class);
            list = sqlQuery.getResultList();
            transaction.commit();
            session.close();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User ");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}

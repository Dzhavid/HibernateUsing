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
            SQLQuery sqlQuery = session.createSQLQuery("CREATE TABLE ba (Id BIGINT auto_increment,Name VARCHAR(10)," +
                    "LastName VARCHAR(10),Age INT,constraint ba_pk" +
                    " primary key (Id))");
            sqlQuery.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("DROP TABLE ba");
            sqlQuery.executeUpdate();
            sqlQuery.addEntity(User.class);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {

        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            //Transaction transaction = null;
            Transaction transaction = session.beginTransaction();
            // User user = new User(name,lastName,age);
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        }catch (Exception e){

        }
    }


    @Override
    public void removeUserById(long id) {

    }


    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT*FROM ba");
            List<User> list = sqlQuery.list();
            transaction.commit();
            session.close();
            return list;
        }catch (Exception e){

        }
        return null;
    }



    @Override
    public void cleanUsersTable() {

    }
}

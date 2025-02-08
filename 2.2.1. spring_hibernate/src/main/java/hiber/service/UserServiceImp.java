package hiber.service;

import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDaoImp userDaoImp;
    @Autowired
    private HibernateTransactionManager getTransactionManager;
    @Autowired
    private DataSource getDataSource;
    @Autowired
    private LocalSessionFactoryBean getSessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByCar(String model, int serises) {
        try (Session session = getSessionFactory.getObject().openSession()) {
            String hql = "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("model", model);
            query.setParameter("series", serises);
            return query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
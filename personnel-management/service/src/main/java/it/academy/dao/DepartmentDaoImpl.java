package it.academy.dao;

import it.academy.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao{

    SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    @Transactional
    public List<Department> findAllDepartment() {
        final List<Department> departments = sessionFactory
                .openSession()
                .createQuery("from Department", Department.class)
                .list();
        return departments;
    }

    @Override
    @Transactional
    public Department getOneDepartment(String id) {
        Department department=sessionFactory.openSession().get(Department.class, id);
        return department;
    }

    @Override
    public String createDepartment(String id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteDepartment(Department department) {
//        final Session session = sessionFactory.openSession();
//        session.delete(department);
//        session.flush();
        final String id = department.getId();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("delete from Department where id=:id", Department.class);
        query.setParameter(1, id);
        query.executeUpdate();
        transaction.commit();
    }


}

package it.academy.dao;

import it.academy.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Department> findAllDepartment() {
        final List<Department> departments = sessionFactory
                .openSession()
                .createQuery("from Department", Department.class)
                .list();
        return departments;
    }




}

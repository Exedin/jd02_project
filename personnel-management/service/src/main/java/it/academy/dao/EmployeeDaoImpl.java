package it.academy.dao;

import it.academy.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> findEmployeeWithoutDepartment() {
        List<Employee> employees = sessionFactory
                .openSession()
                .createQuery("from Employee where D_ID is null", Employee.class)
                .list();

        return employees;
    }
}

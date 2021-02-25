package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    @Override
    public String save(Employee employee) {
        Session session = sessionFactory
                .openSession();
        final Transaction transaction = session.beginTransaction();
        String id = (String) session
                .save(employee);
        transaction.commit();
        return id;
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory
                .openSession();
        final Transaction transaction = session.beginTransaction();
        session
                .delete(employee);
        transaction.commit();

    }

    @Override
    public Employee getOneEmployee(String id) {
        Employee employee=sessionFactory.openSession().get(Employee.class, id);
        return employee;
    }
}

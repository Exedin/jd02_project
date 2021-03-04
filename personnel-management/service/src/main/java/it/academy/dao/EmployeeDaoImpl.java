package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    SessionFactory sessionFactory;

    @Autowired
    DepartmentDaoImpl departmentDaoImpl;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAllEmployeeWithoutDepartment() {
        List<Employee> employees = sessionFactory
                .openSession()
                .createQuery("from Employee where D_ID is null", Employee.class)
                .list();

        return employees;
    }

    @Override
    public String createEmployee(Employee employee) {
        String save = (String) sessionFactory.getCurrentSession().save(employee);
        return save;
    }
//    @Override
//    public String createEmployee(Employee employee) {
//        Session session = sessionFactory
//                .openSession();
//        final Transaction transaction = session.beginTransaction();
//        String id = (String) session
//                .save(employee);
//        transaction.commit();
//        return id;
//    }

    @Override
    public void delete(String id) {
        Employee employee = sessionFactory.getCurrentSession()
                .get(Employee.class, id);
        sessionFactory.getCurrentSession().delete(employee);

    }
//    @Override
//    public void delete(String id) {
//
//        final Session session = sessionFactory.openSession();
//        final List<Employee> list = session.createQuery("from Employee where id='" + id + "'", Employee.class).list();
//        System.out.println(list);
//        Employee employee = list.get(0);
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            //do some work
//            session.delete(employee);
//            tx.commit();
//
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//
//    }

    @Override
    public Employee getOneEmployee(String id) {
        Employee employee=sessionFactory.openSession().get(Employee.class, id);
        return employee;
    }

    @Override
    public void removeEmployeeFromDepartment(String id) {
        Employee employee=sessionFactory.getCurrentSession().get(Employee.class, id);
        employee.setDepartment(null);
        sessionFactory.getCurrentSession().save(employee);

    }
//    @Override
//    public void removeEmployeeFromDepartment(String id) {
//        Employee employee=sessionFactory.openSession().get(Employee.class, id);
//        employee.setDepartment(null);
//        Session session = sessionFactory
//                .openSession();
//        final Transaction transaction = session.beginTransaction();
//        session.update(employee);
//        transaction.commit();
//
//    }

    @Override
    public void addEmployeeToDepartment(String employeeId, String departmentId) {
        Department oneDepartment = departmentDaoImpl.getOneDepartment(departmentId);
        Employee employee=sessionFactory.getCurrentSession().get(Employee.class, employeeId);
        employee.setDepartment(oneDepartment);
        sessionFactory.getCurrentSession().saveOrUpdate(employee);

    }
//    @Override
//    public void addEmployeeToDepartment(String employeeId, String departmentId) {
//        Department oneDepartment = departmentDaoImpl.getOneDepartment(departmentId);
//        Employee employee=sessionFactory.getCurrentSession().get(Employee.class, employeeId);
//        employee.setDepartment(oneDepartment);
//        Session session = sessionFactory
//                .openSession();
//        final Transaction transaction = session.beginTransaction();
//        session.update(employee);
//        transaction.commit();
//
//    }
}

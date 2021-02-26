package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Department> getAllDepartment() {
        final List<Department> departments = sessionFactory
                .openSession()
                .createQuery("from Department", Department.class)
                .list();
        return departments;
    }

    @Override
    public Department getOneDepartment(String id) {
        Department department=sessionFactory.openSession().get(Department.class, id);
        return department;
    }

    @Override
    public String createDepartment(String id) {
        return null;
    }

    @Override
    public void deleteDepartment(Department department) {
//        final Session session = sessionFactory.openSession();
//        final List<Department> departments =
//                session.createQuery("from Department where id='4028b88177d913b20177d913b4120002'", Department.class)
//                        .list();
//        System.out.println(departments);
//        Department department = departments.get(0);
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            //do some work
//            final List<Employee> employeeList = department.getEmployeeList();
//            employeeList.stream().forEach(employee -> employee.setDepartment(null));
//            session.delete(department);
//            tx.commit();
//
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
        final Session session = sessionFactory.openSession();
            //do some work
            final List<Employee> employeeList = department.getEmployeeList();
            employeeList.stream().forEach(employee -> employee.setDepartment(null));
            session.delete(department);

    }

    @Override
    public String save(Department department) {
        Session session = sessionFactory
                .openSession();
        final Transaction transaction = session.beginTransaction();
        String id = (String) session
                .save(department);
        transaction.commit();
        return id;
    }

    @Override
    public void removeAllEmployeeFromDepartment(Department department) {
        final Session currentSession = sessionFactory.openSession();
        List<Employee> employeeList = department.getEmployeeList();
        employeeList.stream().forEach(employee -> employee.setDepartment(null));
        currentSession.update(department);
    }
}

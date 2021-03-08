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

import java.io.Serializable;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        if (id ==null){
            //TODO новый эксепшен мессадж "Вы ввели null"
        }
        Department department = sessionFactory.openSession().get(Department.class, id);
        if (department==null){
            //TODO новый эксепшен мессадж "Вы ввели некорретный айди"
        }
        return department;
    }


    @Override
    public String createDepartment(Department department) {
        Serializable save = sessionFactory.getCurrentSession().save(department);
        return String.valueOf(save);
    }

    @Override
    public void deleteDepartment(String id) {
        Department department = sessionFactory
                .getCurrentSession()
                .createQuery("from Department where id='" + id + "'", Department.class)
                .list().get(0);
        List<Employee> employeeList = department.getEmployeeList();
        employeeList.stream().forEach(employee -> employee.setDepartment(null));
        sessionFactory.getCurrentSession().delete(department);

    }


}

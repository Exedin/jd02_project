package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DepartmentTest extends BaseTest {

    @Test
    public void create(){
        Department department=new Department(null,
                "Eng",
                "8017-2421906",
                "01.09.2008",
                "Detapment of ingeneer", null
        );
        Employee employee = new Employee(null,
                new EmployeeFullName("Andrei", "Trukhanovich", "Vladimirovich"),
                "26.05.1988",
                "+375295592527",
                "deathexedin@gmail.com",
                "engeneer",
                "01.01.2017",
                department);

        Employee employee1 = new Employee(null,
                new EmployeeFullName("Andrei1", "Trukhanovich1", "Vladimirovich1"),
                "26.05.1988",
                "+375295592527",
                "deathexedin@gmail.com",
                "engeneer",
                "01.01.2017", department);

        List<Employee> employeeList=new ArrayList<>(2);
        employeeList.add(employee);
        employeeList.add(employee1);
        department.setEmployeeList(employeeList);

        final Session session = factory.openSession();
        Transaction tx = null;
        final Serializable save;
        try {
            tx = session.beginTransaction();
            //do some work
//            session.save(employee);
//            session.save(employee1);
            session.persist(department);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
//        assertNotNull(save);
    }

    @Test
    public void read(){
        cleanInsert("DepartmentTest.xml");
        final Session session = factory.openSession();
        final List<Department> departments = session.createQuery("from Department", Department.class).list();
//        System.out.println(departments);
        assertEquals(2l, (long)departments.size());
        List<Employee> employeeList = departments.stream().filter(department -> department.getId().equals("4028e49e776ea47d01776ea47f770001"))
                .map(department -> department.getEmployeeList()).findFirst().orElse(null);
//        System.out.println(employeeList);
        assertEquals(2l, employeeList.size());
    }
    @Test
    public void delete(){
        cleanInsert("DepartmentTest.xml");
        final Session session = factory.openSession();
        final List<Department> departments =
                session.createQuery("from Department where id='4028e49e776ea47d01776ea47f770001'", Department.class)
                        .list();
        System.out.println(departments);
        final Department department = departments.get(0);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //do some work
            final List<Employee> employeeList = department.getEmployeeList();
            employeeList.stream().forEach(employee -> employee.setDepartment(null));
            session.delete(department);
            tx.commit();
            final List<Department> departmentsAfterDelete = session.createQuery("from Department", Department.class).list();
            assertEquals(1l,departmentsAfterDelete.size() );

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

    }
}
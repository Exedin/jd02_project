package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeTest extends BaseTest {

    @Test
    public void create(){

        Employee employee = new Employee(null,
                new EmployeeFullName("Andrei", "Trukhanovich", "Vladimirovich"),
                "26.05.1988",
                "+375295592527",
                "deathexedin@gmail.com",
                "engeneer",
                "01.01.2017", null);

        final Session session = factory.openSession();
        Transaction tx = null;
        final Serializable save;
        try {
            tx = session.beginTransaction();
            save=session.save(employee);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        assertNotNull(save);
    }

    @Test
    public void read(){
        cleanInsert("DepartmentTest.xml");
        final Session session = factory.openSession();
        final List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
        assertEquals(2l, (long)employees.size());

    }
    @Test
    public void delete(){
        cleanInsert("DepartmentTest.xml");
        final Session session = factory.openSession();
        final List<Employee> employees =
                session.createQuery("from Employee where id='4028e49e776ea47d01776ea47f6c0000'", Employee.class)
                        .list();
        System.out.println(employees);
        final Employee employee = employees.get(0);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(employee);
            tx.commit();
            final List<Employee> employeesAfterDelete = session.createQuery("from Employee", Employee.class).list();
            assertEquals(1l,employeesAfterDelete.size() );

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

    }
}
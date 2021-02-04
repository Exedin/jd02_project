package it.academy.main;

import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.model.EmployeeFullName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        SessionFactory factory =
                new MetadataSources(registry)
                        .buildMetadata()
                        .buildSessionFactory();
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

        final Session session = factory.openSession();
//        department.setEmployeeList(List.of(employee, employee1));
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //do some work
            session.save(employee);
            session.save(employee1);
            final Serializable save = session.save(department);
            tx.commit();
            final Department department1 = session.get(Department.class, save);
            System.out.println(save);
            System.out.println(department1);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

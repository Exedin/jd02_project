package it.academy.main;

import it.academy.dao.DaoConfiguration;
import it.academy.dao.DepartmentDao;
import it.academy.dao.DepartmentDaoImpl;
import it.academy.dao.EmployeeDaoImpl;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.model.EmployeeFullName;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(DaoConfiguration.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        DepartmentDaoImpl departmentDaoImpl = context.getBean("departmentDaoImpl", DepartmentDaoImpl.class);
        final EmployeeDaoImpl employeeDaoImpl = context.getBean("employeeDaoImpl", EmployeeDaoImpl.class);


        final Department oneDepartment = departmentDaoImpl.getOneDepartment("4028b88177d913b20177d913b4120002");
        System.out.println("\n\n\nОдин департамент!");
        System.out.println(oneDepartment);

        final List<Employee> employeeWithoutDepartment = employeeDaoImpl.findEmployeeWithoutDepartment();
        System.out.println("\n\n\nРаботники без департаменты!");
        System.out.println(employeeWithoutDepartment);

        final Employee oneEmployee = employeeDaoImpl.getOneEmployee("4028b88177d455bf0177d455c1c20000");
        System.out.println("\n\n\nОдин работник");
        System.out.println(oneEmployee);

        employeeDaoImpl.delete(oneEmployee);



//        departmentDaoImpl.deleteDepartment(oneDepartment);
//
//        System.out.println("\n\n\nВсе департаменты!");
//        List<Department> allDepartment = departmentDaoImpl.findAllDepartment();
//        System.out.println(allDepartment);



//        EmployeeDaoImpl employeeDaoImpl = context.getBean("employeeDaoImpl", EmployeeDaoImpl.class);
//
//
//        Employee employee=new Employee();
//        EmployeeFullName employeeFullName=
//                new EmployeeFullName("TestName", "TestSurname", "TestMiddle");
//        employee.setFullName(employeeFullName);
//        String save = employeeDaoImpl.save(employee);

//        List<Employee> employeeWithoutDepartment = employeeDaoImpl.findEmployeeWithoutDepartment();
//        System.out.println(employeeWithoutDepartment);
//
//        employeeDaoImpl.delete(employee);
//
//        System.out.println("После удаления");
//        List<Employee> employeeWithoutDepartment1 = employeeDaoImpl.findEmployeeWithoutDepartment();
//        System.out.println(employeeWithoutDepartment1);


        context.close();
    }
}

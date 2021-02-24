package it.academy.main;

import it.academy.dao.DepartmentDao;
import it.academy.dao.DepartmentDaoImpl;
import it.academy.dao.EmployeeDaoImpl;
import it.academy.model.Department;
import it.academy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ComponentScan(basePackages = "it.academy")
public class Main {
    @Autowired
    private DepartmentDaoImpl departmentDaoImpl;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(Main.class);

//        DepartmentDaoImpl departmentDaoImpl = context.getBean("departmentDaoImpl", DepartmentDaoImpl.class);
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//        List<Department> allDepartment = departmentDaoImpl.findAllDepartment();
//        System.out.println(allDepartment);

        EmployeeDaoImpl employeeDaoImpl = context.getBean("employeeDaoImpl", EmployeeDaoImpl.class);
        List<Employee> employeeWithoutDepartment = employeeDaoImpl.findEmployeeWithoutDepartment();
        System.out.println(employeeWithoutDepartment);
        context.close();
    }
}

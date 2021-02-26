package it.academy.main;

import it.academy.dao.DaoConfiguration;
import it.academy.dao.DepartmentDao;
import it.academy.dao.DepartmentDaoImpl;
import it.academy.dao.EmployeeDaoImpl;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.model.EmployeeFullName;
import it.academy.service.DepartmentService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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
        final DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);

        //Один департамент
        Department department = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f123002");
        System.out.println(department);

        //все департаменты
        final List<Department> allDepartment = departmentService.getAllDepartment();
        System.out.println("\n\n\n"+allDepartment);


        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f123001");

//        final DepartmentDaoImpl departmentDaoImpl = context.getBean("departmentDaoImpl", DepartmentDaoImpl.class);
//        final EmployeeDaoImpl employeeDaoImpl = context.getBean("employeeDaoImpl", EmployeeDaoImpl.class);


//        final Department oneDepartment = departmentDaoImpl.getOneDepartment("4028b88177d913b20177d913b4120002");
//        System.out.println("\n\n\nОдин департамент!");
//        System.out.println(oneDepartment);
//
//        final List<Employee> employeeWithoutDepartment = employeeDaoImpl.findEmployeeWithoutDepartment();
//        System.out.println("\n\n\nРаботники без департаменты!");
//        System.out.println(employeeWithoutDepartment);
//
//        final Employee oneEmployee = employeeDaoImpl.getOneEmployee("4028b88177d455bf0177d455c1c20000");
//        System.out.println("\n\n\nОдин работник");
//        System.out.println(oneEmployee);
//
//        departmentDaoImpl.deleteDepartment(oneDepartment);


//        Employee employee1 = new Employee(null,
//                new EmployeeFullName("Andrei1", "Trukhanovich1", "Vladimirovich1"),
//                "26.05.1988",
//                "+375295592527",
//                "deathexedin@gmail.com",
//                "engeneer",
//                "01.01.2017", null);
//        employeeDaoImpl.save(employee1);


//        Department department=new Department(null,
//                "Eng",
//                "8017-2421906",
//                "01.09.2008",
//                "Detapment of ingeneer", null
//        );
//
//        final String save = departmentDaoImpl.save(department);
//        System.out.println(save);

//        Employee oneEmployee = employeeDaoImpl.getOneEmployee("4028e49e776ea47d01776ea47f6c0003");
//        System.out.println("\n\n\nОдин работник");
//        System.out.println(oneEmployee);
//        employeeDaoImpl.removeEmployeeFromDepartment("4028e49e776ea47d01776ea47f6c0003");
//

//            employeeDaoImpl.addEmployeeToDepartment("4028e49e776ea47d01776ea47f6c0003", "4028e49e776ea47d01776ea47f123001");

        context.close();
    }
}

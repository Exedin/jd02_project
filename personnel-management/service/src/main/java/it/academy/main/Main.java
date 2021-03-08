package it.academy.main;

import it.academy.dao.DaoConfiguration;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.service.DepartmentService;
import it.academy.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoConfiguration.class);


        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        final DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);
        final EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);

        //Один департамент
//        Department department = departmentService.getOneDepartment("4028b88177fedf890177fee388500000");
//        System.out.println(department);

        //один работник
//        final Employee oneEmployee = employeeService.getOneEmployee("4028e49e776ea47d01776ea47f6c0001");
//        System.out.println(oneEmployee);

//        //все департаменты
//        final List<Department> allDepartment = departmentService.getAllDepartment();
//        System.out.println("\n\n\n" + allDepartment);
//
//        final List<Employee> allEmployeeWithoutDepartment = employeeService.getAllEmployeeWithoutDepartment();
//        System.out.println(allEmployeeWithoutDepartment);

//        employeeService.addEmployeeToDepartment("4028e49e776ea47d01776ea47f6c0001", "4028b88177e2a5c50177e2a5c7f40000");
//        employeeService.removeEmployeeFromDepartment("4028e49e776ea47d01776ea47f6c0001");


//        employeeService.deleteEmployee("4028b88177d98dfe0177d98e00ca0000");

//        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f123002");
//        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f123001");

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


//        Department department1=new Department(null,
//                "Eng",
//                "8017-2421906",
//                "01.09.2008",
//                "Detapment of ingeneer", null
//        );
////
//        String save = departmentService.createDepartment(department1);
//        System.out.println(save);


//        Employee oneEmployee = employeeDaoImpl.getOneEmployee("4028e49e776ea47d01776ea47f6c0003");
//        System.out.println("\n\n\nОдин работник");
//        System.out.println(oneEmployee);
//        employeeDaoImpl.removeEmployeeFromDepartment("4028e49e776ea47d01776ea47f6c0003");
//


        context.close();
    }
}

package it.academy;


import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.service.DepartmentService;
import it.academy.service.EmployeeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;


import java.util.List;

@Configuration
@ComponentScan(basePackages = "it.academy")
@Profile("test")

public class RestConfigurationTest {

    @Bean
    @Primary
    public DepartmentService departmentService() throws MyNotFoundException, MyIllegalArgumentException {
        System.out.println("Call mock departmentService()");
        DepartmentService departmentService =
                Mockito.mock(DepartmentService.class);

        Mockito.when(departmentService.getAllDepartment())
                .thenReturn(List.of(new Department(), new Department()));

        Mockito.when(departmentService.getOneDepartment("1"))
                .thenReturn(new Department());
        Mockito.when(departmentService.getOneDepartment("2"))
                .thenThrow(MyNotFoundException.class);

        Mockito.when(departmentService.getOneDepartment(null))
                .thenThrow(MyIllegalArgumentException.class);

        Mockito.when(departmentService.deleteDepartment( "1"))
                .thenReturn(true);

        Mockito.when(departmentService.deleteDepartment( "2"))
                .thenThrow(MyNotFoundException.class);


        Mockito.when(departmentService.createDepartment(new Department()))
                .thenReturn("1");
        Mockito.when(departmentService.createDepartment(null))
                .thenThrow(MyIllegalArgumentException.class);



        return departmentService;
    }
    @Bean
    @Primary
    public EmployeeService employeeService() throws MyNotFoundException, MyIllegalArgumentException {
        System.out.println("Call mock employeeService()");
        EmployeeService employeeService =
                Mockito.mock(EmployeeService.class);

        Mockito.when(employeeService.getAllEmployeeWithoutDepartment())
                .thenReturn(List.of(new Employee(), new Employee()));
        Mockito.when(employeeService.getAllEmployeeWithoutDepartment())
                .thenReturn(List.of(new Employee(), new Employee()));

        Mockito.when(employeeService.deleteEmployee("1"))
                .thenReturn(true);
        Mockito.when(employeeService.deleteEmployee("2"))
                .thenThrow(MyNotFoundException.class);

        Mockito.when(employeeService.createEmployee(new Employee()))
                .thenReturn("1");
        Mockito.when(employeeService.createEmployee(null))
                .thenThrow(MyIllegalArgumentException.class);

        Mockito.when(employeeService.getOneEmployee("1"))
                .thenReturn(new Employee());
        Mockito.when(employeeService.getOneEmployee("2"))
                .thenThrow(MyNotFoundException.class);
        Mockito.when(employeeService.getOneEmployee(null))
                .thenThrow(MyIllegalArgumentException.class);

        Mockito.when(employeeService.addEmployeeToDepartment("1", "1"))
                .thenReturn(true);
        Mockito.when(employeeService.addEmployeeToDepartment("2", "2"))
                .thenThrow(MyNotFoundException.class);


        return employeeService;
    }

}

package it.academy.service;

import it.academy.BaseTest;
import it.academy.DaoTestConfiguration;
import it.academy.dao.DaoConfiguration;

import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfiguration.class)
public class EmployeeServiceTest extends BaseTest {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @Test
    @Transactional
    public void getOneEmployee() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Employee oneEmployee = employeeService.getOneEmployee("4028e49e776ea47d01776ea47f6c0000");
        assertEquals("Andrei", oneEmployee.getFullName().getName());
    }
    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void getOneEmployeeWithBadId() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        Employee oneEmployee = employeeService.getOneEmployee("4028e49e776ea47d01776ea47f6c0010");
    }
    @Test(expected = MyIllegalArgumentException.class)
    @Transactional
    public void getOneEmployeeWithEmptyArgs() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        Employee oneEmployee = employeeService.getOneEmployee("");
    }

    //Разобраться с классом эксепшена
    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void getOneEmployeeWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        Employee oneEmployee = employeeService.getOneEmployee(null);
    }
    @Test
    @Transactional
    public void createEmployee() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Employee employee=new Employee();
        String save = employeeService.createEmployee(employee);
        Employee oneEmployee = employeeService.getOneEmployee(save);
        assertNotNull(employee);
    }
    @Test(expected = MyIllegalArgumentException.class)
    @Transactional
    public void createEmployeeWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        employeeService.createEmployee(null);
    }
    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void deleteEmployee() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.deleteEmployee("4028e49e776ea47d01776ea47f6c0000");
        employeeService.getOneEmployee("4028e49e776ea47d01776ea47f6c0000");
    }

    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void deleteEmployeeWithBadId() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.deleteEmployee("4028e49e776ea47d01776ea47f6c0010");
    }
    @Test(expected = MyIllegalArgumentException.class)
    @Transactional
    public void deleteEmployeeWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.deleteEmployee(null);
    }
    @Test(expected = MyIllegalArgumentException.class)
    @Transactional
    public void deleteEmployeeWithEmptyArgs() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.deleteEmployee("");
    }
    @Test
    @Transactional
    public void addEmployeeToDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.addEmployeeToDepartment("4028e49e776ea47d01776ea47f6c0003", "4028e49e776ea47d01776ea47f770001");
//        final List<Employee> employeeList = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f770001").getEmployeeList();
//        assertEquals(3, employeeList.size());
    }
    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void addEmployeeToDepartmentWithBadIdEmp() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.addEmployeeToDepartment("4028e49e776ea47d01776ea47f6c0011", "4028e49e776ea47d01776ea47f770001");
    }
    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void addEmployeeToDepartmentWithBadIdDep() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.addEmployeeToDepartment("4028e49e776ea47d01776ea47f770001", "4028e49e776ea47d01776ea47f770011");
    }
    @Test
    @Transactional
    public void removeEmployeeFromDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.removeEmployeeFromDepartment("4028e49e776ea47d01776ea47f6c0000");
        assertNull(employeeService.getOneEmployee("4028e49e776ea47d01776ea47f6c0000").getDepartment());

    }
    @Test
    @Transactional
    public void getAllEmployeeWithoutDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        List<Employee> allEmployeeWithoutDepartment = employeeService.getAllEmployeeWithoutDepartment();
        assertEquals(1, allEmployeeWithoutDepartment.size());
    }
    @Test(expected = MyNotFoundException.class)
    @Transactional
    public void getAllEmployeeWithoutDepartmentWithEmptyBase() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        employeeService.deleteEmployee("4028e49e776ea47d01776ea47f6c0003");
        List<Employee> allEmployeeWithoutDepartment = employeeService.getAllEmployeeWithoutDepartment();
        System.out.println(allEmployeeWithoutDepartment);
    }


}
package it.academy.service;

import it.academy.BaseTest;
import it.academy.DaoTestConfiguration;
import it.academy.dao.DaoConfiguration;
import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfiguration.class)
public class DepartmentServiceTest extends BaseTest {


    @Autowired
    DepartmentService departmentService;

    @org.junit.Test
    public void getOneDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f770001");
        assertEquals("test1", oneDepartment.getName());
    }

    @Test(expected = MyNotFoundException.class)
    public void getOneDepartmentWithBadId() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f7700011");

    }
    @Test(expected = MyIllegalArgumentException.class)
    public void getOneDepartmentWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment(null);

    }
    @Test(expected = MyIllegalArgumentException.class)
    public void getOneDepartmentWithEmptyArgs() throws MyNotFoundException, MyIllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("");

    }
    @org.junit.Test
    public void getAllDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        final List<Department> allDepartment = departmentService.getAllDepartment();
        //Then
        assertEquals(2, allDepartment.size());
        assertEquals("test2", allDepartment.get(1).getName());
    }
    @Test(expected = MyNotFoundException.class)
    public void getAllDepartmentEmptyBase() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770001");
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770002");
        //When
        final List<Department> allDepartment = departmentService.getAllDepartment();
        //Then

    }
    @org.junit.Test
    public void deleteDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770001");
        //Then
        List<Department> allDepartment = departmentService.getAllDepartment();
        assertEquals(1, allDepartment.size());
        assertEquals("test2", allDepartment.get(0).getName());
    }
    @Test(expected = MyNotFoundException.class)
    public void deleteDepartmentWithBadId() throws MyNotFoundException, MyIllegalArgumentException {
        //When
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770551");
    }
    @Test(expected = MyIllegalArgumentException.class)
    public void deleteDepartmentWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        //When
        departmentService.deleteDepartment(null);
    }
    @Test(expected = MyIllegalArgumentException.class)
    public void deleteDepartmentEmptyArgs() throws MyNotFoundException, MyIllegalArgumentException {
        //When
        departmentService.deleteDepartment("");
    }
    @org.junit.Test
    public void createDepartment() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        Department department =new Department();
        departmentService.createDepartment(department);
        //Then
        List<Department> allDepartment = departmentService.getAllDepartment();
        assertEquals(3, allDepartment.size());
    }
    @Test(expected = MyIllegalArgumentException.class)
    public void createDepartmentWithNull() throws MyNotFoundException, MyIllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        departmentService.createDepartment(null);
    }



}
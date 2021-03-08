package it.academy.service;

import it.academy.BaseTest;
import it.academy.dao.DaoConfiguration;
import it.academy.exception.IllegalArgumentException;
import it.academy.exception.NotFoundException;
import it.academy.model.Department;
import it.academy.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfiguration.class)
public class DepartmentServiceTest extends BaseTest {


    @Autowired
    DepartmentService departmentService;

    @org.junit.Test
    public void getOneDepartment() throws NotFoundException, IllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f770001");
        assertEquals("test1", oneDepartment.getName());
    }

    @Test(expected = NotFoundException.class)
    public void getOneDepartmentWithBadId() throws NotFoundException, IllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f7700011");

    }
    @Test(expected = IllegalArgumentException.class)
    public void getOneDepartmentWithNull() throws NotFoundException, IllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment(null);

    }
    @Test(expected = IllegalArgumentException.class)
    public void getOneDepartmentWithEmptyArgs() throws NotFoundException, IllegalArgumentException {
        cleanInsert("DepartmentTest.xml");
        Department oneDepartment = departmentService.getOneDepartment("");

    }
    @org.junit.Test
    public void getAllDepartment() throws NotFoundException, IllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        final List<Department> allDepartment = departmentService.getAllDepartment();
        //Then
        assertEquals(2, allDepartment.size());
        assertEquals("test2", allDepartment.get(1).getName());
    }
    @Test(expected = NotFoundException.class)
    public void getAllDepartmentEmptyBase() throws NotFoundException, IllegalArgumentException {
        //Given
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770001");
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770002");
        //When
        final List<Department> allDepartment = departmentService.getAllDepartment();
        //Then

    }
    @org.junit.Test
    public void deleteDepartment() throws NotFoundException, IllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770001");
        //Then
        List<Department> allDepartment = departmentService.getAllDepartment();
        assertEquals(1, allDepartment.size());
        assertEquals("test2", allDepartment.get(0).getName());
    }
    @Test(expected = NotFoundException.class)
    public void deleteDepartmentWithBadId() throws NotFoundException, IllegalArgumentException {
        //When
        departmentService.deleteDepartment("4028e49e776ea47d01776ea47f770551");
    }
    @Test(expected = IllegalArgumentException.class)
    public void deleteDepartmentWithNull() throws NotFoundException, IllegalArgumentException {
        //When
        departmentService.deleteDepartment(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deleteDepartmentEmptyArgs() throws NotFoundException, IllegalArgumentException {
        //When
        departmentService.deleteDepartment("");
    }
    @org.junit.Test
    public void createDepartment() throws NotFoundException, IllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        Department department =new Department();
        departmentService.createDepartment(department);
        //Then
        List<Department> allDepartment = departmentService.getAllDepartment();
        assertEquals(3, allDepartment.size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void createDepartmentWithNull() throws NotFoundException, IllegalArgumentException {
        //Given
        cleanInsert("DepartmentTest.xml");
        //When
        departmentService.createDepartment(null);
    }



}
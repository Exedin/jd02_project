package it.academy.dao;

import it.academy.model.Department;
import it.academy.service.DepartmentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfiguration.class)
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDaoImpl departmentDaoImpl;
    @Autowired
    DepartmentService departmentService;

    @org.junit.Test
    @Transactional
    public void getOneDepartment() {
        Department oneDepartment = departmentService.getOneDepartment("4028e49e776ea47d01776ea47f123001");
        System.out.println("\n\n\nОдин департамент!");
        System.out.println(oneDepartment);
    }

}
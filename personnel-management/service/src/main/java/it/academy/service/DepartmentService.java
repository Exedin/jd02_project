package it.academy.service;

import it.academy.dao.DepartmentDaoImpl;
import it.academy.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDaoImpl departmentDao;

    @Transactional
    public Department getDepartment(String id){
        return departmentDao.getOneDepartment(id);
    }

}

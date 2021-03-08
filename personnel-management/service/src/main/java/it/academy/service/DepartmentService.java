package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;
import it.academy.exception.IllegalArgumentException;
import it.academy.exception.NotFoundException;
import it.academy.model.Department;
import it.academy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    public Department getOneDepartment(String id) throws NotFoundException, IllegalArgumentException {
        if(id==null||id==""){
            throw new IllegalArgumentException("Illegal argument");
        }
        final Department oneDepartment = departmentDao.getOneDepartment(id);
        if (oneDepartment==null){
            throw new NotFoundException("Department with that id doesn't exist");
        }
        return oneDepartment;
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartment() throws NotFoundException {
        List<Department> allDepartment = departmentDao.getAllDepartment();
        if (allDepartment==null){
            throw new NotFoundException("Department with that id doesn't exist");
        }
        return allDepartment;
    }

    @Transactional
    public void deleteDepartment (String id) throws NotFoundException, IllegalArgumentException {
        Department oneDepartment = getOneDepartment(id);
        oneDepartment.getEmployeeList().stream().
                forEach(employee -> employeeDao.removeEmployeeFromDepartment(employee.getId()));
        departmentDao.deleteDepartment(id);
    }

    @Transactional
    public String createDepartment (Department department) throws IllegalArgumentException {
        if (department == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        String save = departmentDao.createDepartment(department);
        return save;
    }



}

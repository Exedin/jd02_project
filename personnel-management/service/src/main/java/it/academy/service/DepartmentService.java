package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;
import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Department;
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
    public Department getOneDepartment(String id) throws MyNotFoundException, MyIllegalArgumentException {
        if(id==null||id==""){
            throw new MyIllegalArgumentException("Illegal argument");
        }
        final Department oneDepartment = departmentDao.getOneDepartment(id);
        if (oneDepartment==null){
            throw new MyNotFoundException("Department with that id doesn't exist");
        }
        return oneDepartment;
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartment() throws MyNotFoundException {
        List<Department> allDepartment = departmentDao.getAllDepartment();
        if (allDepartment.size()==0){
            throw new MyNotFoundException("Any department doesn't exist");
        }
        return allDepartment;
    }

    @Transactional
    public void deleteDepartment (String id) throws MyNotFoundException, MyIllegalArgumentException {
        Department oneDepartment = getOneDepartment(id);
        oneDepartment.getEmployeeList().stream().
                forEach(employee -> employeeDao.removeEmployeeFromDepartment(employee.getId()));
        departmentDao.deleteDepartment(id);
    }

    @Transactional
    public String createDepartment (Department department) throws MyIllegalArgumentException {
        if (department == null) {
            throw new MyIllegalArgumentException("Illegal argument");
        }
        String save = departmentDao.createDepartment(department);
        return save;
    }



}

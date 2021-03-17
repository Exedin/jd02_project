package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;

import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Department;
import it.academy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDaoImpl;

    @Autowired
    DepartmentDao departmentDao;


    @Transactional
    public Employee getOneEmployee(String id) throws MyIllegalArgumentException, MyNotFoundException {
        Employee employee = employeeDaoImpl.getOneEmployee(id);
        if(id==null||id==""){
            throw new MyIllegalArgumentException("Illegal argument");
        }
        if (employee==null){
            throw new MyNotFoundException("Employee with that id doesn't exist");
        }
        return employee;
    }

    @Transactional
    public String createEmployee (Employee employee) throws MyIllegalArgumentException {
        if (employee == null) {
            throw new MyIllegalArgumentException("Illegal argument");
        }
        String save = employeeDaoImpl.createEmployee(employee);
        return save;
    }


    @Transactional
    public boolean deleteEmployee (String id) throws MyNotFoundException, MyIllegalArgumentException {
        if(id==null||id==""){
            throw new MyIllegalArgumentException("Illegal argument");
        }
        Employee oneEmployee = getOneEmployee(id);
        if (oneEmployee==null){
            throw new MyNotFoundException("Employee with that id doesn't exist");
        }
        employeeDaoImpl.delete(id);
        return true;
    }

    @Transactional
    public boolean addEmployeeToDepartment(String employeeId, String departmentId)
            throws MyNotFoundException, MyIllegalArgumentException {
        Employee oneEmployee = getOneEmployee(employeeId);
        Department oneDepartment = departmentDao.getOneDepartment(departmentId);
        employeeDaoImpl.addEmployeeToDepartment(oneEmployee, oneDepartment);
        return true;
    }

    @Transactional
    public boolean removeEmployeeFromDepartment(String id) throws MyNotFoundException, MyIllegalArgumentException {
        if(id==null||id==""){
            throw new MyIllegalArgumentException("Illegal argument");
        }
        Employee oneEmployee = getOneEmployee(id);
        if (oneEmployee==null){
            throw new MyNotFoundException("Employee with that id doesn't exist");
        }
        if (oneEmployee.getDepartment()==null){
            throw new MyNotFoundException("Employee doesn't have a department");
        }
        employeeDaoImpl.removeEmployeeFromDepartment(id);
        return true;
    }

    @Transactional
    public List<Employee> getAllEmployeeWithoutDepartment() throws MyNotFoundException {
        List<Employee> allEmployeeWithoutDepartment = employeeDaoImpl.getAllEmployeeWithoutDepartment();
        if (allEmployeeWithoutDepartment.isEmpty()){
            throw new MyNotFoundException("Employees without department don't exist");
        }
        return allEmployeeWithoutDepartment;
    }





}

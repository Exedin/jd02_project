package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;
import it.academy.exception.*;
import it.academy.exception.IllegalArgumentException;
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
    public Employee getOneEmployee(String id) throws NotFoundException, IllegalArgumentException {
        Employee employee = employeeDaoImpl.getOneEmployee(id);
        if(id==null||id==""){
            throw new IllegalArgumentException("Illegal argument");
        }
        if (employee==null){
            throw new NotFoundException("Employee with that id doesn't exist");
        }
        return employee;
    }

    @Transactional
    public String createEmployee (Employee employee){
        String save = employeeDaoImpl.createEmployee(employee);
        return save;
    }


    @Transactional
    public void deleteEmployee (String id) throws NotFoundException, IllegalArgumentException {
        if(id==null||id==""){
            throw new IllegalArgumentException("Illegal argument");
        }
        Employee oneEmployee = getOneEmployee(id);
        if (oneEmployee==null){
            throw new NotFoundException("Employee with that id doesn't exist");
        }
        employeeDaoImpl.delete(id);
    }

    @Transactional
    public void addEmployeeToDepartment(String employeeId, String departmentId)
            throws NotFoundException, IllegalArgumentException {
        Employee oneEmployee = getOneEmployee(employeeId);
        Department oneDepartment = departmentDao.getOneDepartment(departmentId);
        employeeDaoImpl.addEmployeeToDepartment(oneEmployee, oneDepartment);

    }

    @Transactional
    public void removeEmployeeFromDepartment(String id) throws NotFoundException, IllegalArgumentException {
        if(id==null||id==""){
            throw new IllegalArgumentException("Illegal argument");
        }
        Employee oneEmployee = getOneEmployee(id);
        if (oneEmployee==null){
            throw new NotFoundException("Employee with that id doesn't exist");
        }
        employeeDaoImpl.removeEmployeeFromDepartment(id);
    }

    @Transactional
    public List<Employee> getAllEmployeeWithoutDepartment() throws NotFoundException {
        List<Employee> allEmployeeWithoutDepartment = employeeDaoImpl.getAllEmployeeWithoutDepartment();
        if (allEmployeeWithoutDepartment==null){
            throw new NotFoundException("Employees without department don't exist");
        }
        return allEmployeeWithoutDepartment;
    }





}

package it.academy.service;

import it.academy.dao.EmployeeDao;
import it.academy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDaoImpl;

    @Transactional
    public Employee getOneEmployee(String id) {
        Employee employee = employeeDaoImpl.getOneEmployee(id);
        return employee;
    }

    @Transactional
    public String createEmployee (Employee employee){
        String save = employeeDaoImpl.createEmployee(employee);
        return save;
    }


    @Transactional
    public void deleteEmployee (String id){
        employeeDaoImpl.delete(id);
    }

    @Transactional
    public void addEmployeeToDepartment(String employeeId, String departmentId){
        employeeDaoImpl.addEmployeeToDepartment(employeeId,departmentId);
    }

    @Transactional
    public void removeEmployeeFromDepartment(String id){
        employeeDaoImpl.removeEmployeeFromDepartment(id);
    }

    @Transactional
    public List<Employee> getAllEmployeeWithoutDepartment(){
        List<Employee> allEmployeeWithoutDepartment = employeeDaoImpl.getAllEmployeeWithoutDepartment();
        return allEmployeeWithoutDepartment;
    }





}

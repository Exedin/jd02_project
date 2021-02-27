package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> getAllEmployeeWithoutDepartment();
    public Employee getOneEmployee(String id);
    public String createEmployee(Employee employee);
    public void delete(String id);
    public void removeEmployeeFromDepartment(String id);
    public void addEmployeeToDepartment(String employeeId, String departmentId);



}

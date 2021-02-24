package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> findEmployeeWithoutDepartment();
    public String save(Employee employee);
    public void delete(Employee employee);
}

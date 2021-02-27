package it.academy.dao;

import it.academy.model.Department;
import it.academy.model.Employee;

import java.util.List;

public interface DepartmentDao {
    public List<Department> getAllDepartment();
    public Department getOneDepartment(String id);
//    public String createDepartment(String id);
    public void deleteDepartment(String id);
    public String createDepartment(Department department);
//    public void removeAllEmployeeFromDepartment(Department department);
}

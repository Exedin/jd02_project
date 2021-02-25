package it.academy.dao;

import it.academy.model.Department;

import java.util.List;

public interface DepartmentDao {
    public List<Department> findAllDepartment();
    public Department getOneDepartment(String id);
    public String createDepartment(String id);
    public void deleteDepartment(Department department);
}

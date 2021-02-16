package it.academy.dao;

import it.academy.model.Department;

import java.util.List;

public interface DepartmentDao {
    public List<Department> findAllDepartment();
}

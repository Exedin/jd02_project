package it.academy.dao;

import it.academy.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
    private static List<Department> departments = List.of(
            Department.builder()
                    .id("1")
                    .dateOfFormation("01.09.2008")
                    .name("Civil engeniring")
                    .description("Department for desing building")
                    .phoneNumber("+375172421906")
                    .build(),
            Department.builder()
                    .id("2")
                    .dateOfFormation("01.08.2008")
                    .name("bookkeeping")
                    .description("Department for accounting and finance")
                    .phoneNumber("+375172434547")
                    .build(),
            Department.builder()
                    .id("3")
                    .dateOfFormation("01.01.2010")
                    .name("HR")
                    .description("Department for HR")
                    .phoneNumber("+375172434546")
                    .build()
    );

    @Override
    public List<Department> findAllDepartment() {
        return departments;
    }
}

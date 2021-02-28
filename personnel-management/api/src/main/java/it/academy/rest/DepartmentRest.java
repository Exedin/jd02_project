package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.model.Department;
import it.academy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentRest {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments/{id}")
    @ApiOperation("Read one department")
    public ResponseEntity<Department> readDepartment(@PathVariable String id) {

        final Department oneDepartment = departmentService.getOneDepartment(id);
        if (oneDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oneDepartment, HttpStatus.OK);
    }
    @GetMapping("/departments")
    @ApiOperation("Read all departments")
    public ResponseEntity<List<Department>> readAllDepartment() {

        List<Department> allDepartment = departmentService.getAllDepartment();
        return new ResponseEntity<>(allDepartment, HttpStatus.OK);
    }

}

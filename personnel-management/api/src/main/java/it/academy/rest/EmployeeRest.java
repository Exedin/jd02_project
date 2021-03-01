package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRest {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    @ApiOperation("Read one employee")
    public ResponseEntity<Employee> readOneEmployee(@PathVariable String id) {

        final Employee oneEmployee = employeeService.getOneEmployee(id);
        if (oneEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oneEmployee, HttpStatus.OK);
    }


}

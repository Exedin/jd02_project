package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.model.Employee;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRest {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees/{employeeId}")
    @ApiOperation("Read one employee")
    public ResponseEntity<Employee> readOneEmployee(@PathVariable String employeeId) {

        final Employee oneEmployee = employeeService.getOneEmployee(employeeId);
        if (oneEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oneEmployee, HttpStatus.OK);
    }

    @GetMapping("/employees")
    @ApiOperation("Read all employees without department")
    public ResponseEntity<List<Employee>> readAllEmployeeWithoutDepartment() {
        List<Employee> employees = employeeService.getAllEmployeeWithoutDepartment();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/employees/department_remove/{employeeId}")
    @ApiOperation("Remove one employee from department")
    public ResponseEntity deleteDepartment(@PathVariable String employeeId) {
        employeeService.removeEmployeeFromDepartment(employeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{employeeId}")
    @ApiOperation("Remove one employee")
    public ResponseEntity deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employees")
    @ApiOperation("Create employee")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PutMapping("/employees")
    @ApiOperation("Add employee to department")
    public ResponseEntity addEmployeeToDepartment(String employeeId, String departmentId){
        employeeService.addEmployeeToDepartment(employeeId, departmentId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}

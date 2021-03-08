package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.exception.IllegalArgumentException;
import it.academy.exception.NotFoundException;
import it.academy.model.Employee;
import it.academy.response.ErrorResponse;
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
    public ResponseEntity readOneEmployee (@PathVariable String employeeId)
            throws NotFoundException, IllegalArgumentException {
        final Employee oneEmployee = employeeService.getOneEmployee(employeeId);
        return new ResponseEntity (oneEmployee, HttpStatus.OK);
    }

    @GetMapping("/employees")
    @ApiOperation("Read all employees without department")
    public ResponseEntity<List<Employee>> readAllEmployeeWithoutDepartment()
            throws NotFoundException {
        List<Employee> employees = employeeService.getAllEmployeeWithoutDepartment();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/employees/department_remove/{employeeId}")
    @ApiOperation("Remove one employee from department")
    public ResponseEntity deleteEmployeeFromDepartment(@PathVariable String employeeId)
            throws NotFoundException, IllegalArgumentException {
        employeeService.removeEmployeeFromDepartment(employeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{employeeId}")
    @ApiOperation("Remove one employee")
    public ResponseEntity deleteEmployee(@PathVariable String employeeId)
            throws NotFoundException, IllegalArgumentException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employees")
    @ApiOperation("Create employee")
    public ResponseEntity createEmployee(@RequestBody Employee employee)
            throws IllegalArgumentException {
        if (employee == null) {
            throw new IllegalArgumentException("Illegal argument");
        }
        employeeService.createEmployee(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PutMapping("/employees")
    @ApiOperation("Add employee to department")
    public ResponseEntity addEmployeeToDepartment(String employeeId, String departmentId)
            throws NotFoundException, IllegalArgumentException {
        employeeService.addEmployeeToDepartment(employeeId, departmentId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity handleException (IllegalArgumentException exc){
        ErrorResponse errorResponse=new ErrorResponse();

        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity handleException (NotFoundException exc){
        ErrorResponse errorResponse=new ErrorResponse();

        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

}

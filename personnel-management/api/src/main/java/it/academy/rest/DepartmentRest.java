package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.exception.MyIllegalArgumentException;
import it.academy.exception.MyNotFoundException;
import it.academy.model.Department;
import it.academy.response.ErrorResponse;
import it.academy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentRest {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments/{departmentId}")
    @ApiOperation("Read one department")
    public ResponseEntity<Department> readDepartment(@PathVariable String departmentId)
            throws MyNotFoundException, MyIllegalArgumentException {
        final Department oneDepartment = departmentService.getOneDepartment(departmentId);
        return new ResponseEntity<>(oneDepartment, HttpStatus.OK);
    }
    @GetMapping("/departments")
    @ApiOperation("Read all departments")
    public ResponseEntity<List<Department>> readAllDepartment() throws MyNotFoundException {
        List<Department> allDepartment = departmentService.getAllDepartment();
        return new ResponseEntity<>(allDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/departments/{departmentId}")
    @ApiOperation("Delete one department")
    public ResponseEntity deleteDepartment(@PathVariable String departmentId)
            throws MyNotFoundException, MyIllegalArgumentException {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/departments")
    @ApiOperation("Create department")
    public ResponseEntity createDepartment(@RequestBody Department department)
            throws MyIllegalArgumentException {
        departmentService.createDepartment(department);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @ExceptionHandler
    public ResponseEntity handleException (MyIllegalArgumentException exc){
        ErrorResponse errorResponse=new ErrorResponse();

        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleException (MyNotFoundException exc){
        ErrorResponse errorResponse=new ErrorResponse();

        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }


}

package it.academy.web.controller;


import it.academy.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping ("/getAll")
    public String getAllDepartment (Model model){
        model.addAttribute("allDepartment", departmentDao.findAllDepartment());
        return "allDepartment";
    }
}

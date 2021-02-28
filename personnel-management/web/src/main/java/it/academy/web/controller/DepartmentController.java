package it.academy.web.controller;


import it.academy.dao.DepartmentDao;
import it.academy.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping ("/getAll")
    public String getAllDepartment (Model model){
        model.addAttribute("allDepartment", departmentDao.getAllDepartment());
        return "allDepartment";
    }
    @GetMapping ("/getOne")
    public String getOneDepartment (Model model){
        model.addAttribute("allDepartment", departmentDao.getAllDepartment());
        return "findOneDepartment";
    }

    @PostMapping ("/showOne")
    public String showOneDepartment (@RequestParam(value = "search") String name, Model model){
        final Department dep = departmentDao.getAllDepartment().stream()
                .filter(department -> department.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst().orElse(null);
        model.addAttribute("department", dep);

        return "showOneDepartment";
    }
}

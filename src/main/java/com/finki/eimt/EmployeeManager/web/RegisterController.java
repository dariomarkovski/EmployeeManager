package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.DepartmentService;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class RegisterController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public RegisterController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/register")
    public String getRegister(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "register";
    }

    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute Employee employee) {
        employeeService.registerEmployee(employee);
        return "redirect:/activation/";
    }

}

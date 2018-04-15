package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class LoginController {

    EmployeeService employeeService;

    @Autowired
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("employee", new Employee());
        return "login";
    }
}

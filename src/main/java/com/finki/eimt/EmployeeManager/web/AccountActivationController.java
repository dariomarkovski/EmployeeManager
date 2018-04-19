package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class AccountActivationController {

    private EmployeeService employeeService;

    @Autowired
    public AccountActivationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/activation")
    public String getAccountActivation() {
        return "activation";
    }

    @PostMapping("/activation")
    public String postAccountActivation(@RequestParam String code) {
        employeeService.activateAccount(code);
        return "redirect:/login/";
    }
}
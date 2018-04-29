package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@CrossOrigin
public class PasswordController {

    private EmployeeService employeeService;

    @Autowired
    public PasswordController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/reset-password")
    public String getResetPassword() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(@RequestParam String email) {
        employeeService.resetPassword(email);
        return "redirect:/login";
    }

    @GetMapping("/change-password")
    public String getChangePassword() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String postChangePassword(@RequestParam String currentPassword, @RequestParam String newPassword, Principal principal) {
        Employee employee = employeeService.changePassword(principal.getName(), currentPassword, newPassword);
        if (employee == null) {
            return "redirect:/change-password";
        } else {
            return "redirect:/logout";
        }
    }

}

package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.persistence.DepartmentDao;
import com.finki.eimt.EmployeeManager.persistence.EmployeeDao;
import com.finki.eimt.EmployeeManager.service.DepartmentService;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin
public class HomeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public HomeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/")
    public String getHome(Principal principal, Model model) {
        Employee employee = employeeService.getEmployeeFromEmail(principal.getName());
        List<Department> departmentList = departmentService.findAllDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("principal", principal);
        model.addAttribute("departments", departmentList);
        return "home";
    }

    @PostMapping(value="/saveChanges")
    public String getHome(Principal principal, @ModelAttribute Employee employee, Model model){
        employee = employeeService.saveEmployeeChanges(principal.getName(), employee);
        List<Department> departmentList = departmentService.findAllDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("principal", principal);
        model.addAttribute("departments", departmentList);
        return "redirect:/";
    }
}

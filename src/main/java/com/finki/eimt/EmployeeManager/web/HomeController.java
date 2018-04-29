package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.persistence.DepartmentDao;
import com.finki.eimt.EmployeeManager.persistence.EmployeeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin
public class HomeController {

    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;

    public HomeController(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @GetMapping(value = "/")
    public String getHome(Principal principal, Model model) {
        Employee employee = employeeDao.findByEmail(principal.getName());
        List<Department> departmentList = departmentDao.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("principal", principal);
        model.addAttribute("departments", departmentList);
        return "home";
    }
}

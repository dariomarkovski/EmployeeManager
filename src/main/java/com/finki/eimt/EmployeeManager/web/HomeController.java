package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.persistence.DepartmentDao;
import com.finki.eimt.EmployeeManager.persistence.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin
public class HomeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "/")
    public String getHome(Principal principal, Model model) {
        Employee employee = employeeDao.findByEmail(principal.getName());
        List<Department> departmentList = departmentDao.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentList);
        return "home";
    }
}

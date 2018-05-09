package com.finki.eimt.EmployeeManager.web;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.DepartmentService;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class AdminController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public AdminController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/admin")
    public String getAdmin(Model model, Principal principal, @RequestParam(defaultValue = "1") int currentPage) {
        Employee admin = employeeService.getEmployeeFromEmail(principal.getName());
        List<Employee> employees = employeeService.getEmployeesPage(currentPage);
        int totalPages = employeeService.getTotalPages();
        model.addAttribute("admin", admin);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("employees", employees);
        return "admin-page";
    }

    @GetMapping("/admin-new-employee")
    public String getAdminNewEmployee(Model model, Principal principal) {
        Employee admin = employeeService.getEmployeeFromEmail(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "admin-form-employee";
    }

    @PostMapping("/admin-new-employee")
    public String postAdminNewEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployeeFromAdmin(employee);
        return "redirect:/admin/";
    }

    @GetMapping("/admin-edit-employee/{email}")
    public String getAdminEditEmployee(@PathVariable String email, Principal principal, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeFromEmail(email));
        model.addAttribute("admin", employeeService.getEmployeeFromEmail(principal.getName()));
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "admin-form-employee";
    }

    @PostMapping("/admin-delete-employee/{email}")
    public String deleteAdminDeleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email);
        return "redirect:/admin/";
    }

    @GetMapping("/manager")
    public String getManager(Model model, Principal principal) {
        List<Employee> employees = employeeService.getAllEmployeesForManager(principal.getName());
        Employee manager = employeeService.getEmployeeFromEmail(principal.getName());
        model.addAttribute("admin", manager);
        model.addAttribute("employees", employees);
        return "admin-page";
    }

    @GetMapping("/manager-new-employee")
    public String getManagerNewEmployee(Model model, Principal principal) {
        Employee manager = employeeService.getEmployeeFromEmail(principal.getName());
        model.addAttribute("admin", manager);
        model.addAttribute("employee", new Employee());
        Employee employee = employeeService.getEmployeeFromEmail(principal.getName());
        Department department = employee.getDepartment();
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        model.addAttribute("departments", departments);
        return "admin-form-employee";
    }

    @PostMapping("/manager-new-employee")
    public String postManagerNewEmployee(@ModelAttribute Employee employee, Principal principal) {
        employeeService.saveEmployeeFromAdmin(employee);
        return "redirect:/manager/";
    }

    @GetMapping("/manager-edit-employee/{email}")
    public String getManagerEditEmployee(@PathVariable String email, Principal principal, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeFromEmail(email));
        model.addAttribute("admin", employeeService.getEmployeeFromEmail(principal.getName()));
        Employee employee = employeeService.getEmployeeFromEmail(principal.getName());
        Department department = employee.getDepartment();
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        model.addAttribute("departments", departments);
        return "admin-form-employee";
    }

    @PostMapping("/manager-delete-employee/{email}")
    public String deleteManagerDeleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email);
        return "redirect:/manager/";
    }

}

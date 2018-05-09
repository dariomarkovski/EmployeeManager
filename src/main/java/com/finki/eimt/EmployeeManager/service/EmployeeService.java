package com.finki.eimt.EmployeeManager.service;

import com.finki.eimt.EmployeeManager.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee activateAccount(String code);

    Employee resetPassword(String email);

    Employee changePassword(String email, String currentPassword, String newPassword);

    List<Employee> getNonActivatedEmployees();

    long deleteEmployee(String email);

    long countEmployees();

    Employee saveEmployee(Employee employee);

    Employee saveEmployeeChanges(String email, Employee employee);

    Employee saveEmployeeFromAdmin(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesForManager(String email);

    Employee getEmployeeFromEmail(String email);

    List<Employee> getEmployeesPage(int currentPage);

    int getTotalPages();
}

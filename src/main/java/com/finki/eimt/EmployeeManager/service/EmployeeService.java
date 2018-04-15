package com.finki.eimt.EmployeeManager.service;

import com.finki.eimt.EmployeeManager.model.Employee;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee activateAccount(String code);

    Employee resetPassword(String email);

    Employee changePassword(String email, String currentPassword, String newPassword);
}

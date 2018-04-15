package com.finki.eimt.EmployeeManager.persistence;

import com.finki.eimt.EmployeeManager.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, String> {

    Employee findByEmail(String email);

    Employee findByActivationCode(String activationCode);

}

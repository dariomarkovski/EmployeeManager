package com.finki.eimt.EmployeeManager.persistence;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, String> {

    Employee findByEmail(String email);

    Employee findByActivationCode(String activationCode);

    List<Employee> findAllByActivated(boolean activated);

    @Transactional
    long deleteByEmail(String email);

    List<Employee> findAll();

    List<Employee> findAllByDepartment(Department department);
}

package com.finki.eimt.EmployeeManager.persistence;

import com.finki.eimt.EmployeeManager.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {

    List<Department> findAll();

}

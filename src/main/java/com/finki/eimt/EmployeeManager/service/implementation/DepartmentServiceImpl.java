package com.finki.eimt.EmployeeManager.service.implementation;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.persistence.DepartmentDao;
import com.finki.eimt.EmployeeManager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentDao.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentDao.save(department);
    }

}

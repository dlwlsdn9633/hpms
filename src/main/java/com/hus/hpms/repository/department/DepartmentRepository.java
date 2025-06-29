package com.hus.hpms.repository.department;

import com.hus.hpms.constants.EOrderBy;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.*;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository
{
    public Optional<Department> save(Department user);
    public Optional<Department> findByDetailField(String detailField);
    public Optional<Department> findById(Long id);
    public Optional<Department> findByLoginId(String loginId);
    public boolean confirmDepartmentPassword(DepartmentLoginParam departmentLoginParam, String password);
    public List<DepTypeElement> findAllTypeDep();
    public List<MajorTypeElement> findAllTypeMajor(String field);
    public List<Department> findAll();
    public void update(String field, String detailField, Boolean master, Boolean admin, String id);
    public void updateConfirmStatus(Long id);
    public List<DepartmentPerformance> findAllDepTypeDepartmentsPerformance(EOrderBy orderBy);
    public List<DepartmentPerformance> findAllMajorTypeDepartmentsPerformace(EOrderBy orderBy);
}

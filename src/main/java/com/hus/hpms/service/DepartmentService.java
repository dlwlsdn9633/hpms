package com.hus.hpms.service;

import com.hus.hpms.constants.DepartmentSql;
import com.hus.hpms.constants.EOrderBy;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.*;
import com.hus.hpms.repository.department.DepartmentRepository;
import com.hus.hpms.repository.request.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService
{
    private final DepartmentRepository departmentRepository;
    private final RequestRepository requestRepository;

    public Optional<Department> save(Department department)
    {
        return departmentRepository.save(department);
    }

    public Optional<Department> login(DepartmentLoginParam departmentLoginParam)
    {
        Optional<Department> department = departmentRepository.findByLoginId(departmentLoginParam.getLoginId());
        if(department.isEmpty())
        {
            return department;
        }
        else
        {
            boolean confirmPassword = departmentRepository.confirmDepartmentPassword(departmentLoginParam, department.get().getLoginPw());
            if(confirmPassword)
            {
                return department;
            }
            else
            {
                return Optional.empty();
            }
        }
    }

    public List<DepTypeElement> findAllTypeDep()
    {
        return departmentRepository.findAllTypeDep();
    }

    public List<MajorTypeElement> findAllTypeMajor(String field)
    {
        return departmentRepository.findAllTypeMajor(field);
    }

    public Optional<Department> findById(Long id)
    {
        return departmentRepository.findById(id);
    }

    public void updateStatus(Long id)
    {
        departmentRepository.updateConfirmStatus(id);
    }

    public void update(DepartmentUpdateParam departmentUpdateParam, String id)
    {
        boolean isDep = (Integer.parseInt(departmentUpdateParam.getFieldType()) == 0);

        String field = (isDep) ? departmentUpdateParam.getDepField() :departmentUpdateParam.getMajorField();
        String detailField = (isDep) ? null: departmentUpdateParam.getMajorDetailField();

        Boolean master = (Integer.parseInt(departmentUpdateParam.getMasterOption()) == 1);
        Boolean admin = (Integer.parseInt(departmentUpdateParam.getAdminOption()) == 1);

        departmentRepository.update(field, detailField, master, admin, id);
    }

    public List<Department> findAll()
    {
        return departmentRepository.findAll();
    }

    public List<DepartmentPerformance> findAllDepTypeDepartmentsPerformance(EOrderBy orderBy)
    {
        List<DepartmentPerformance> departments = departmentRepository.findAllDepTypeDepartmentsPerformance(orderBy);
        for (DepartmentPerformance department : departments) {
            department.setRequests(requestRepository.findAllRequestsByDepartmentId(department.getId()));
        }
        return departments;
    }

    public List<DepartmentPerformance> findAllMajorTypeDepartmentsPerformace(EOrderBy orderBy)
    {

        List<DepartmentPerformance> departments = departmentRepository.findAllMajorTypeDepartmentsPerformace(orderBy);
        for (DepartmentPerformance department : departments) {
            department.setRequests(requestRepository.findAllRequestsByDepartmentId(department.getId()));
        }
        return departments;
    }
}

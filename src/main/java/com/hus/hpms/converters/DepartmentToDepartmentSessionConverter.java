package com.hus.hpms.converters;

import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.DepartmentSession;
import org.springframework.core.convert.converter.Converter;

public class DepartmentToDepartmentSessionConverter implements Converter<Department, DepartmentSession>
{
    @Override
    public DepartmentSession convert(Department source)
    {
        DepartmentSession departmentSession = new DepartmentSession();

        departmentSession.setId(source.getId());
        departmentSession.setField(source.getField());
        departmentSession.setDetailField(source.getDetailField());
        departmentSession.setCommit(source.getCommit());
        departmentSession.setMaster(source.getMaster());
        departmentSession.setAdmin(source.getAdmin());

        return departmentSession;
    }
}

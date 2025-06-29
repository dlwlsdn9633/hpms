package com.hus.hpms.repository.department.Impl;

import com.hus.hpms.repository.department.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JdbcDepartmentRepositoryImplTest
{
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void save()
    {

    }

    @Test
    void update()
    {

    }
}
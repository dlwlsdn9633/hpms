package com.hus.hpms.repository.department.Impl;

import com.hus.hpms.constants.DepartmentSql;
import com.hus.hpms.constants.EOrderBy;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.*;
import com.hus.hpms.repository.department.DepartmentCommitUpdateParam;
import com.hus.hpms.repository.department.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcDepartmentRepositoryImpl implements DepartmentRepository
{
    //private final JdbcTemplate template;
    private final PasswordEncoder passwordEncoder;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    public JdbcDepartmentRepositoryImpl(DataSource dataSource, PasswordEncoder passwordEncoder)
    {
        //this.template = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("DEPARTMENT")
                .usingGeneratedKeyColumns("id");
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Department> save(Department department)
    {
        boolean isExisted = isExistedDepartment(department);
        if(!isExisted)
        {
            department.setInsertDate(LocalDateTime.now());
            department.setUpdateDate(LocalDateTime.now());
            department.setLoginPw(passwordEncoder.encode(department.getLoginPw()));
            setMasterAdminCommitFalse(department);

            SqlParameterSource param = new BeanPropertySqlParameterSource(department);
            department.setId(simpleJdbcInsert.executeAndReturnKey(param).longValue());

            return Optional.of(department);
        }
        else
        {
            return Optional.empty();
        }
    }

    private void setMasterAdminCommitFalse(Department department)
    {
        department.setMaster(false);
        department.setAdmin(false);
        department.setCommit(false);
    }

    private boolean isExistedDepartment(Department department)
    {
        Map<String, Object> countParam = Map.of("loginId", department.getLoginId());
        Integer isExistedDepartment = namedParameterJdbcTemplate.queryForObject(DepartmentSql.FIND_DEPARTMENT_COUNT_BY_LOGIN_ID, countParam, Integer.class);
        assert isExistedDepartment != null;
        return isExistedDepartment != 0;
    }


    @Override
    public Optional<Department> findByDetailField(String detailField)
    {
        try
        {
            Map<String, Object> param = Map.of("detailField", detailField);
            Department department = namedParameterJdbcTemplate.queryForObject(DepartmentSql.FIND_DEPARTMENT_BY_FIELD, param, departmentRowMapper());
            return Optional.of(department);
        }
        catch(EmptyResultDataAccessException e)
        {
            log.error("error", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Department> findById(Long id)
    {
        try
        {
            Map<String, Object> param = Map.of("id", id);
            Department department = namedParameterJdbcTemplate.queryForObject(DepartmentSql.FIND_DEPARTMENT_BY_ID, param, departmentRowMapper());
            assert department != null;
            return Optional.of(department);
        }
        catch(EmptyResultDataAccessException e)
        {
            log.error("error", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Department> findByLoginId(String loginId)
    {
        try
        {
            Map<String, Object> param = Map.of("loginId", loginId);
            Department department = namedParameterJdbcTemplate.queryForObject(DepartmentSql.FIND_DEPARTMENT_BY_LOGIN_ID, param, departmentRowMapper());
            assert department != null;
            return Optional.of(department);
        }
        catch(EmptyResultDataAccessException e)
        {
            log.error("error", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean confirmDepartmentPassword(DepartmentLoginParam departmentLoginParam, String password)
    {
        return passwordEncoder.matches(departmentLoginParam.getLoginPw(), password);
    }

    @Override
    public List<DepTypeElement> findAllTypeDep()
    {
        return namedParameterJdbcTemplate.query(DepartmentSql.FIND_ALL_TYPE_DEP, depTypeElementRowMapper());
    }

    @Override
    public List<MajorTypeElement> findAllTypeMajor(String field)
    {
        Map<String, Object> param = Map.of("field", field);
        return namedParameterJdbcTemplate.query(DepartmentSql.FIND_ALL_TYPE_MAJOR_BY_FIELD, param, majorTypeElementRowMapper());
    }

    @Override
    public List<Department> findAll()
    {
        return namedParameterJdbcTemplate.query(DepartmentSql.FIND_ALL_DEPARTMENTS, departmentRowMapper());
    }

    @Override
    public void update(String field, String detailField, Boolean master, Boolean admin, String id)
    {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("field", field)
                .addValue("detailField", detailField)
                .addValue("master", master)
                .addValue("admin", admin)
                .addValue("id", id);
        namedParameterJdbcTemplate.update(DepartmentSql.UPDATE_DEPARTMENT, param);
    }
    @Override
    public void updateConfirmStatus(Long id)
    {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("commit", true)
                .addValue("updateDate", LocalDateTime.now())
                .addValue("id", id);
        namedParameterJdbcTemplate.update(DepartmentSql.UPDATE_COMMIT, param);
    }

    @Override
    public List<DepartmentPerformance> findAllDepTypeDepartmentsPerformance(EOrderBy orderBy) {
        String sql = (orderBy == EOrderBy.ASC) ? DepartmentSql.FIND_ALL_DEP_TYPE_DEPARTMENTS_PERFORMANCE_ORDER_BY_DONE_RATIO : DepartmentSql.FIND_ALL_DEP_TYPE_DEPARTMENTS_PERFORMANCE_ORDER_BY_DONE_RATIO_DESC;
        return namedParameterJdbcTemplate.query(sql, departmentPerformanceRowMapper());
    }

    @Override
    public List<DepartmentPerformance> findAllMajorTypeDepartmentsPerformace(EOrderBy orderBy) {
        String sql = (orderBy == EOrderBy.ASC) ? DepartmentSql.FIND_ALL_MAJOR_TYPE_DEPARTMENTS_PERFORMANCE_ORDER_BY_DONE_RATIO : DepartmentSql.FIND_ALL_MAJOR_TYPE_DEPARTMENTS_PERFORMANCE_ORDER_BY_DONE_RATIO_DESC;
        return namedParameterJdbcTemplate.query(sql, departmentPerformanceRowMapper());
    }

    private RowMapper<DepTypeElement> depTypeElementRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(DepTypeElement.class);
    }

    private RowMapper<MajorTypeElement> majorTypeElementRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(MajorTypeElement.class);
    }

    private RowMapper<Department> departmentRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(Department.class);
    }
    private RowMapper<DepartmentPerformance> departmentPerformanceRowMapper() {return BeanPropertyRowMapper.newInstance(DepartmentPerformance.class);}
}

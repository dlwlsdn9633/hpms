package com.hus.hpms.repository.request.Impl;

import com.hus.hpms.constants.EStatus;
import com.hus.hpms.constants.RequestSql;
import com.hus.hpms.domain.Request;
import com.hus.hpms.repository.request.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
public class JdbcRequestRepository implements RequestRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcRequestRepository(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Request> saveAll(List<Request> requests)
    {
        String id = UUID.randomUUID().toString();

        for (Request request : requests)
        {
            saveRequest(request, id);
        }

        return requests;
    }

    @Override
    public void delete(String id)
    {
        Map<String, Object> param = Map.of("id", id);
        namedParameterJdbcTemplate.update(RequestSql.DELETE_REQUEST, param);
    }

    @Override
    public List<Request> findAllById(String id)
    {
        Map<String, Object> param = Map.of("id", id);
        return namedParameterJdbcTemplate.query(RequestSql.FIND_ALL_REQUESTS_BY_ID, param, requestRowMapper());
    }

    @Override
    public void updateStatus(String id, EStatus currentStatus)
    {
        if (currentStatus == EStatus.READY)
        {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("status", "processing");
            namedParameterJdbcTemplate.update(RequestSql.UPDATE_STATUS_FROM_READY_TO_PROCESSING, param);
        }
        else if (currentStatus == EStatus.PROCESSING)
        {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("status", "done")
                    .addValue("completeDate", LocalDateTime.now());
            namedParameterJdbcTemplate.update(RequestSql.UPDATE_STATUS_FROM_PROCESSING_TO_DONE, param);
        }
        else
        {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("status", "processing");
            namedParameterJdbcTemplate.update(RequestSql.UPDATE_STATUS_FROM_DONE_TO_PROCESSING, param);
        }
    }

    @Override
    public List<Request> findAllRequestsByDepartmentId(Long departmentId)
    {
        Map<String, Object> param = Map.of("departmentId", departmentId);
        return namedParameterJdbcTemplate.query(RequestSql.FIND_ALL_REQUESTS_BY_DEPARTMENT_ID, param, requestRowMapper());
    }

    @Override
    public void update(List<Request> requests, String id)
    {
        delete(id);
        for (Request request : requests)
        {
            saveRequest(request, id);
        }
    }

    private void saveRequest(Request request, String id)
    {
        request.setId(id);
        SqlParameterSource param = new BeanPropertySqlParameterSource(request);
        namedParameterJdbcTemplate.update(RequestSql.INSERT_REQUEST, param);
    }

    private RowMapper<Request> requestRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(Request.class);
    }
}

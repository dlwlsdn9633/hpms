package com.hus.hpms.repository.request;

import com.hus.hpms.constants.EStatus;
import com.hus.hpms.domain.Request;

import java.util.List;

public interface RequestRepository
{
    public List<Request> saveAll(List<Request> requests);
    public void delete(String id);
    public void update(List<Request> requests, String id);
    public List<Request> findAllById(String id);
    public void updateStatus(String id, EStatus currentStatus);
    public List<Request> findAllRequestsByDepartmentId(Long departmentId);
}

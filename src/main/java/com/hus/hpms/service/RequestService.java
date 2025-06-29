package com.hus.hpms.service;

import com.hus.hpms.constants.EStatus;
import com.hus.hpms.domain.Request;
import com.hus.hpms.repository.request.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService
{
    private final RequestRepository requestRepository;
    public List<Request> save(List<Request> requests)
    {
        return requestRepository.saveAll(requests);
    }
    public void update(List<Request> requests, String id)
    {
        requestRepository.update(requests, id);
    }
    public void delete(String id)
    {
        requestRepository.delete(id);
    }
    public List<Request> findById(String id)
    {
        return requestRepository.findAllById(id);
    }
    public void updateStatus(String id, EStatus currentStatus)
    {
        requestRepository.updateStatus(id, currentStatus);
    }
}

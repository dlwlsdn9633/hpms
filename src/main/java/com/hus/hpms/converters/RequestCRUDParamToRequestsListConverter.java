package com.hus.hpms.converters;

import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.request.RequestCRUDParam;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RequestCRUDParamToRequestsListConverter implements Converter<RequestCRUDParam, List<Request>>
{
    @Override
    public List<Request> convert(RequestCRUDParam source)
    {
        List<Request> requests = new ArrayList<>();
        List<Long> departmentIds = source.getDepartmentIds();

        for (Long departmentId : departmentIds)
        {
            Request request = new Request();
            request.setDepartmentId(departmentId);
            request.setArea(source.getArea());
            request.setDetailArea(source.getDetailArea().get(request.getArea() - 1));
            request.setRequest(source.getRequest());
            request.setStatus("ready");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime parsedRequestDate = LocalDate.parse(source.getCurrentDate(), formatter).atStartOfDay();
            LocalDateTime parsedExpectedCompleteDate = LocalDate.parse(source.getExpectedCompleteDate(), formatter).atStartOfDay();

            request.setRequestDate(parsedRequestDate);
            request.setExpectedCompleteDate(parsedExpectedCompleteDate);
            request.setInsertDate(LocalDateTime.now());
            request.setUpdateDate(LocalDateTime.now());

            requests.add(request);
        }

        return requests;
    }
}

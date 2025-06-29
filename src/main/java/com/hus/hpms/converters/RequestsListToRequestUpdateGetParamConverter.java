package com.hus.hpms.converters;

import com.hus.hpms.domain.Department;
import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.request.RequestUpdateGetParam;
import com.hus.hpms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class RequestsListToRequestUpdateGetParamConverter implements Converter<List<Request>, RequestUpdateGetParam>
{
    DepartmentService departmentService;
    public RequestsListToRequestUpdateGetParamConverter(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }
    @Override
    public RequestUpdateGetParam convert(List<Request> requests)
    {
        RequestUpdateGetParam requestUpdateGetParam = new RequestUpdateGetParam();

        Request firstRequest = requests.get(0);

        requestUpdateGetParam.setArea(firstRequest.getArea());
        requestUpdateGetParam.setDetailArea(firstRequest.getDetailArea());
        requestUpdateGetParam.setRequest(firstRequest.getRequest());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedRequestDate = firstRequest.getRequestDate().format(formatter);
        String formattedExpectedCompleteDate = firstRequest.getExpectedCompleteDate().format(formatter);

        requestUpdateGetParam.setRequestDate(formattedRequestDate);
        requestUpdateGetParam.setExpectedCompleteDate(formattedExpectedCompleteDate);

        for (Request request : requests)
        {
            requestUpdateGetParam.getDepartmentIds().add(request.getDepartmentId());
            Optional<Department> currentDepartment = departmentService.findById(request.getDepartmentId());
            currentDepartment.ifPresent(value -> requestUpdateGetParam.getDepartments().add(value));
        }
        return requestUpdateGetParam;
    }

}

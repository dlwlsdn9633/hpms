package com.hus.hpms.controller;

import com.hus.hpms.constants.EStatus;
import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.request.RequestCRUDParam;
import com.hus.hpms.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController
{
    private final RequestService requestService;
    private final DefaultConversionService conversionService;

    @PostMapping("/create")
    public String create(RequestCRUDParam requestCRUDParam)
    {
        List<Request> requests = conversionService.convert(requestCRUDParam, List.class);
        String id = requestService.save(requests).get(0).getId();
        return "redirect:/request/" + id;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, RequestCRUDParam requestCRUDParam)
    {
        List<Request> requests = conversionService.convert(requestCRUDParam, List.class);
        requestService.update(requests, id);
        return "redirect:/request/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id)
    {
        requestService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/status/update/{id}/{status}")
    public String UpdateStatus(@PathVariable("id") String id, @PathVariable("status") int status)
    {
        EStatus currentStatus = switch (status) {
            case 0 -> EStatus.READY;
            case 1 -> EStatus.PROCESSING;
            case 2 -> EStatus.DONE;
            default -> EStatus.ERROR;
        };

        if (currentStatus == EStatus.ERROR)
        {
            return "redirect:/request/" + id;
        }

        requestService.updateStatus(id, currentStatus);
        return "redirect:/request/" + id;
    }
}

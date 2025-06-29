package com.hus.hpms.controller;

import com.hus.hpms.constants.EOrderBy;
import com.hus.hpms.constants.ElementNames;
import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.domain.Comment;
import com.hus.hpms.domain.Department;
import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.department.DepTypeElement;
import com.hus.hpms.dto.department.DepartmentPerformance;
import com.hus.hpms.dto.department.DepartmentSession;
import com.hus.hpms.dto.request.RequestUpdateGetParam;
import com.hus.hpms.service.CommentService;
import com.hus.hpms.service.DepartmentService;
import com.hus.hpms.service.RequestService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController
{
    private final DepartmentService departmentService;
    private final RequestService requestService;
    private final CommentService commentService;
    private final DefaultConversionService conversionService;


    @GetMapping("/")
    public String home(Model model, @Nullable @RequestParam(name = "order", required = false) String order)
    {
        EOrderBy orderBy = EOrderBy.ASC;
        if (order != null && order.equals("desc"))
        {
            orderBy = EOrderBy.DESC;
        }

        List<DepartmentPerformance> depTypeDepartmentsPerformance = departmentService.findAllDepTypeDepartmentsPerformance(orderBy);
        model.addAttribute("departments", depTypeDepartmentsPerformance);

        return "index";
    }

    @GetMapping("/major")
    public String major(Model model, @Nullable @RequestParam(name = "order", required = false) String order)
    {
        EOrderBy orderBy = EOrderBy.ASC;
        if (order != null && order.equals("desc"))
        {
            orderBy = EOrderBy.DESC;
        }

        List<DepartmentPerformance> majorTypeDepartmentsPerformace = departmentService.findAllMajorTypeDepartmentsPerformace(orderBy);
        model.addAttribute("departments", majorTypeDepartmentsPerformace);
        return "major";
    }

    @GetMapping("/rank")
    public String rank(Model model, @RequestParam(name = "type") String type)
    {
        EOrderBy orderBy = EOrderBy.DESC;
        List<DepartmentPerformance> departmentPerformances;
        departmentPerformances = (type.equals("department")) ? departmentService.findAllDepTypeDepartmentsPerformance(orderBy) : departmentService.findAllMajorTypeDepartmentsPerformace(orderBy);
        model.addAttribute("departments", departmentPerformances);
        return "rank";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @GetMapping("/request/{id}")
    public String request(@PathVariable String id, Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)DepartmentSession departmentSession)
    {
        List<Request> requests = requestService.findById(id);
        List<Department> departments = new ArrayList<>();
        List<Comment> comments = commentService.findAllByRequestId(id);

        for (Request request : requests) {
            Long departmentId = request.getDepartmentId();
            Optional<Department> department = departmentService.findById(departmentId);
            department.ifPresent(departments::add);
        }

        model.addAttribute("departmentSession", departmentSession);
        model.addAttribute("departments", departments);
        model.addAttribute("requests", requests);
        model.addAttribute("comments", comments);
        return "request/request";
    }

    @GetMapping("/request/create")
    public String requestCreate(Model model)
    {
        SetRequestKeysAndValues(model);
        setDepartmentsAndMajorsInModel(model);

        return "request/createRequest";
    }

    @GetMapping("/request/update/{id}")
    public String requestUpdate(@PathVariable("id") String id, Model model)
    {
        setDepartmentsAndMajorsInModel(model);
        List<Request> requests = requestService.findById(id);

        RequestUpdateGetParam requestUpdateGetParam = conversionService.convert(requests, RequestUpdateGetParam.class);

        model.addAttribute("requests", requests);
        model.addAttribute("requestUpdateGetParam", requestUpdateGetParam);
        model.addAttribute("collegeNames", ElementNames.collegeNamesKor);

        SetRequestKeysAndValues(model);
        return "request/updateRequest";
    }

    @GetMapping("/master")
    public String master(Model model)
    {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("collegeNames", ElementNames.collegeNamesKor);
        return "master/master";
    }

    private void SetRequestKeysAndValues(Model model)
    {
        for (int index = 0; index < ElementNames.requestKeys.length; ++index)
        {
            model.addAttribute(ElementNames.requestKeys[index], ElementNames.requestValues[index]);
        }
    }

    private void setDepartmentsAndMajorsInModel(Model model)
    {
        List<DepTypeElement> depTypeElements = departmentService.findAllTypeDep();
        model.addAttribute("depTypeElements", depTypeElements);
        for (int index = 0; index < ElementNames.collegeNamesEng.length; ++index)
        {
            model.addAttribute(ElementNames.collegeNamesEng[index], departmentService.findAllTypeMajor(ElementNames.collegeNamesKor[index]));
            log.info("elementName: {}, findAllTypeMajor: {}", ElementNames.collegeNamesEng[index], departmentService.findAllTypeMajor(ElementNames.collegeNamesKor[index]));
        }
    }
}

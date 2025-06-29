package com.hus.hpms.controller;

import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.DepartmentLoginParam;
import com.hus.hpms.dto.department.DepartmentRegisterParam;
import com.hus.hpms.dto.department.DepartmentSession;
import com.hus.hpms.dto.department.DepartmentUpdateParam;
import com.hus.hpms.service.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController
{
    private final DepartmentService departmentService;
    private final DefaultConversionService conversionService;

    @PostMapping("/register")
    public String register(DepartmentRegisterParam departmentRegisterParam, RedirectAttributes redirectAttributes)
    {
        Department department = new Department();

        department.setLoginId(departmentRegisterParam.getLoginId());
        department.setLoginPw(departmentRegisterParam.getLoginPw());
        setFieldAndDetailField(department, departmentRegisterParam);

        Optional<Department> savedDepartment = departmentService.save(department);
        if (savedDepartment.isEmpty())
        {
            redirectAttributes.addFlashAttribute("saveFail", true);
            return "redirect:/register";
        }
        else
        {
            return "redirect:/login";
        }
    }

    @PostMapping("/login")
    public String login(DepartmentLoginParam departmentLoginParam, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        Optional<Department> department = departmentService.login(departmentLoginParam);

        if(department.isEmpty())
        {
            // 로그인 실패 + Validation 공부 후 더 자세히 구현하기
            redirectAttributes.addFlashAttribute("loginFail", true);
            return "redirect:/login";
        }
        else
        {
            // 로그인 성공
            DepartmentSession departmentSession = conversionService.convert(department.get(), DepartmentSession.class);
            HttpSession session = request.getSession();

            session.setAttribute(SessionConst.LOGIN_MEMBER, departmentSession);
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, DepartmentUpdateParam departmentUpdateParam)
    {
        departmentService.update(departmentUpdateParam, id);
        return "redirect:/master";
    }

    @PostMapping("/update/status/{id}")
    public String updateStatus(@PathVariable("id") Long id)
    {
        departmentService.updateStatus(id);
        return "redirect:/master";
    }

    private void setFieldAndDetailField(Department department, DepartmentRegisterParam departmentRegisterParam)
    {
        if (departmentRegisterParam.getDepartmentType().equals("department"))
        {
            department.setField(departmentRegisterParam.getField());
        }
        else
        {
            department.setField(departmentRegisterParam.getFieldType());
            department.setDetailField(departmentRegisterParam.getDetailField());
        }
    }
}

package com.hus.hpms.dto.request;

import com.hus.hpms.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RequestUpdateGetParam
{
    private List<Long> departmentIds;
    private List<Department> departments;
    private Integer area;
    private String detailArea;
    private String request;
    private String requestDate;
    private String expectedCompleteDate;

    public RequestUpdateGetParam()
    {
        this.departments = new ArrayList<>();
        this.departmentIds = new ArrayList<>();
    }
}

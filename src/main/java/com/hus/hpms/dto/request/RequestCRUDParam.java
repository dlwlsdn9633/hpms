package com.hus.hpms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RequestCRUDParam
{
    private String id;
    private Integer area;
    private List<Long> departmentIds;
    private List<String> detailArea;
    private String request;
    private String currentDate;
    private String expectedCompleteDate;

    public RequestCRUDParam()
    {
        this.departmentIds = new ArrayList<>();
        this.detailArea = new ArrayList<>();
    }
}

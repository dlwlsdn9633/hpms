package com.hus.hpms.dto.department;

import com.hus.hpms.domain.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentPerformance
{
    private Long id;
    private String field;
    private String detailField;
    private Long totalRequests;
    private Long readyRequests;
    private Long processingRequests;
    private Long doneRequests;
    private Double doneRatio;
    private List<Request> requests;
}

package com.hus.hpms.dto.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class DepartmentUpdateParam
{
    private String fieldType;
    private String depField;
    private String majorField;
    private String majorDetailField;
    private String detailField;
    private String masterOption;
    private String adminOption;

    public DepartmentUpdateParam() {}

    public DepartmentUpdateParam(
            String fieldType,
            String depField,
            String majorField,
            String majorDetailField,
            String detailField,
            String masterOption,
            String adminOption
    )
    {
        this.fieldType = fieldType;
        this.depField = depField;
        this.majorField = majorField;
        this.majorDetailField = majorDetailField;
        this.detailField = detailField;
        this.masterOption = masterOption;
        this.adminOption = adminOption;
    }
}

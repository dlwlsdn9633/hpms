package com.hus.hpms.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRegisterParam
{
    private String loginId;
    private String loginPw;
    private String departmentType;
    private String field;
    private String fieldType;
    private String detailField;
}

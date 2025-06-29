package com.hus.hpms.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentLoginParam
{
    private String loginId;
    private String loginPw;
}

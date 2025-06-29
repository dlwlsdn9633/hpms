package com.hus.hpms.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentSession
{
    private Long id;
    private String field;
    private String detailField;
    private Boolean commit;
    private Boolean master;
    private Boolean admin;
}

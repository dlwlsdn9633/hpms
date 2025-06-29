package com.hus.hpms.dto.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DepTypeElement
{
    private Long id;

    private String field;
    public DepTypeElement() {}
    public DepTypeElement(String field)
    {
        this.field = field;
    }
}

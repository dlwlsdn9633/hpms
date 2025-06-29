package com.hus.hpms.dto.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MajorTypeElement
{
    private Long id;
    private String field;
    private String detailField;

    public MajorTypeElement() {}
    public MajorTypeElement(String field, String detailField)
    {
        this.field = field;
        this.detailField = detailField;
    }
}

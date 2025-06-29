package com.hus.hpms.repository.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class DepartmentCommitUpdateParam
{
    private Long id;
    private Boolean commit;
    private LocalDateTime updateDate;

    public DepartmentCommitUpdateParam() {}

    public DepartmentCommitUpdateParam(Long id, Boolean commit)
    {
        this.id = id;
        this.commit = commit;
    }
}

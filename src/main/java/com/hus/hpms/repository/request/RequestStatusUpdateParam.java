package com.hus.hpms.repository.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class RequestStatusUpdateParam
{
    private String status;
    private LocalDateTime updateDate;

    public RequestStatusUpdateParam() {}

    public RequestStatusUpdateParam(String status, LocalDateTime updateDate)
    {
        this.status = status;
        this.updateDate = updateDate;
    }
}

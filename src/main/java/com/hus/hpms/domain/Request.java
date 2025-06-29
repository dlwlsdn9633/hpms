package com.hus.hpms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class Request
{
    // uuid로 사용하기
    private String id;
    private Long departmentId;

    private Integer area;
    private String detailArea;

    private String request;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completeDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedCompleteDate;

    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public Request() {}

    public Request(String id ,Long departmentId, String request, LocalDateTime requestDate, LocalDateTime completeDate, LocalDateTime expectedCompleteDate, String status)
    {
        this.id = id;
        this.departmentId = departmentId;
        this.request = request;
        this.requestDate = requestDate;
        this.completeDate = completeDate;
        this.expectedCompleteDate = expectedCompleteDate;
        this.status = status;
    }
}

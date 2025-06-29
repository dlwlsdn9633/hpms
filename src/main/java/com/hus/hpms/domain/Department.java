package com.hus.hpms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class Department
{
    private Long id;
    private String loginId;
    private String loginPw;
    private String field;
    private String detailField;
    private Boolean commit;
    private Boolean master;
    private Boolean admin;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public Department() {}

    public Department(String loginId, String loginPw, String field, String detailField, Boolean master, Boolean admin)
    {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.field = field;
        this.detailField = detailField;
        this.master = master;
        this.admin = admin;
    }
}

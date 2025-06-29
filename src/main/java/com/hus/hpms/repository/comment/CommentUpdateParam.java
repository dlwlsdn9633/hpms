package com.hus.hpms.repository.comment;

import com.hus.hpms.domain.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class CommentUpdateParam
{
    private Long id;
    private String comment;
    private LocalDateTime updateDate;

    public CommentUpdateParam() {}

    public CommentUpdateParam(String comment)
    {
        this.comment = comment;
    }
}

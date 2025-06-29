package com.hus.hpms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
public class Comment
{
    private Long id;
    private Long departmentId;
    private Department department;
    private String comment;
    private String requestId;
    private Long parentCommentId;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private List<CommentFile> commentFiles;
    private List<Comment> replys;

    public Comment() {}

    public Comment(Long departmentId, String comment, String requestId, Long parentCommentId)
    {
        this.departmentId = departmentId;
        this.comment = comment;
        this.requestId = requestId;
        this.parentCommentId = parentCommentId;
    }
}

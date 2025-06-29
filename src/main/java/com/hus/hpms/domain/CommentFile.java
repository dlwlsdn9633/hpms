package com.hus.hpms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class CommentFile
{
    private Long id;
    private Long commentId;
    private String originalName;
    private String storeFileName;
    private LocalDateTime insertDate;

    public CommentFile() {}

    public CommentFile(String originalName, String storeFileName)
    {
        this.originalName = originalName;
        this.storeFileName = storeFileName;
    }
}

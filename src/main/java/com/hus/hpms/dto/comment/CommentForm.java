package com.hus.hpms.dto.comment;

import com.hus.hpms.domain.CommentFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CommentForm
{
    private String comment;
    private List<MultipartFile> commentFiles;
    public CommentForm() {}
    public CommentForm(String comment, List<MultipartFile> commentFiles)
    {
        this.comment = comment;
        this.commentFiles = commentFiles;
    }
}

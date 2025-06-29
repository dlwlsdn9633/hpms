package com.hus.hpms.dto.comment;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReplyForm
{
    private Long parentCommentId;
    private String reply;
    private List<MultipartFile> replyFiles;
    public ReplyForm() {}
    public ReplyForm(String reply, List<MultipartFile> replyFiles)
    {
        this.reply = reply;
        this.replyFiles = replyFiles;
    }
}

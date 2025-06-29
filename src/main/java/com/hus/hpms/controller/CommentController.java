package com.hus.hpms.controller;

import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.domain.Comment;
import com.hus.hpms.domain.CommentFile;
import com.hus.hpms.dto.comment.CommentForm;
import com.hus.hpms.dto.comment.ReplyForm;
import com.hus.hpms.dto.department.DepartmentSession;
import com.hus.hpms.repository.comment.CommentUpdateParam;
import com.hus.hpms.service.CommentFileService;
import com.hus.hpms.service.CommentService;
import com.hus.hpms.util.FileStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController
{
    private final FileStore fileStore;
    private final CommentService commentService;
    private final CommentFileService commentFileService;
    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") String id, @ModelAttribute CommentForm commentForm, HttpServletRequest request) throws IOException {
        List<CommentFile> commentFiles = fileStore.storeFiles(commentForm.getCommentFiles());
        Long loginDepartmentId = getLoginDepartmentId(request);
        Comment createdComment = commentService.save(commentForm, loginDepartmentId, id);

        for (CommentFile commentFile : commentFiles) {
            commentFile.setCommentId(createdComment.getId());
            commentFileService.save(commentFile);
        }

        return "redirect:/request/" + id;
    }

    @PostMapping("/update/{id}/{requestId}")
    public String update(@PathVariable("id") Long id, @PathVariable("requestId") String requestId , CommentUpdateParam commentUpdateParam)
    {
        commentService.update(commentUpdateParam, id);
        return "redirect:/request/" + requestId;
    }

    @PostMapping("/delete/{id}/{requestId}")
    public String delete(@PathVariable("id") Long id, @PathVariable("requestId") String requestId)
    {
        commentService.delete(id);
        return "redirect:/request/" + requestId;
    }

    @PostMapping("/delete/reply/{id}/{requestId}")
    public String deleteReply(@PathVariable("id") Long id, @PathVariable("requestId") String requestId)
    {
        commentService.deleteReply(id);
        return "redirect:/request/" + requestId;
    }

    @PostMapping("/create/{requestId}/reply/{commentId}")
    public String createReply(@PathVariable("requestId") String requestId, @PathVariable("commentId") Long commentId, ReplyForm replyForm, HttpServletRequest request) throws IOException {
        List<CommentFile> commentFiles = fileStore.storeFiles(replyForm.getReplyFiles());
        Long loginDepartmentId = getLoginDepartmentId(request);
        Comment createdRely = commentService.saveReply(replyForm, loginDepartmentId, requestId, commentId);

        for (CommentFile commentFile : commentFiles) {
            commentFile.setCommentId(createdRely.getId());
            commentFileService.save(commentFile);
        }

        return "redirect:/request/" + requestId;
    }

    private Long getLoginDepartmentId(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        DepartmentSession loginDepartmentSession = (DepartmentSession) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return loginDepartmentSession.getId();
    }
}

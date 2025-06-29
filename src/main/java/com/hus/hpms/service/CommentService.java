package com.hus.hpms.service;

import com.hus.hpms.domain.Comment;
import com.hus.hpms.domain.CommentFile;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.comment.CommentForm;
import com.hus.hpms.dto.comment.ReplyForm;
import com.hus.hpms.repository.comment.CommentRepository;
import com.hus.hpms.repository.comment.CommentUpdateParam;
import com.hus.hpms.repository.commentFile.CommentFileRepository;
import com.hus.hpms.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService
{
    private final CommentRepository commentRepository;
    private final DepartmentRepository departmentRepository;
    private final CommentFileRepository commentFileRepository;

    public Comment save(CommentForm commentForm, Long departmentId, String requestId)
    {
        Comment comment = new Comment(departmentId, commentForm.getComment(), requestId, null);
        return commentRepository.save(comment);
    }

    public Comment saveReply(ReplyForm replyForm, Long departmentId, String requestId, Long parentCommentId)
    {
        Comment comment = new Comment(departmentId, replyForm.getReply(), requestId, parentCommentId);
        return commentRepository.save(comment);
    }

    public void update(CommentUpdateParam commentUpdateParam, Long id)
    {
        commentUpdateParam.setId(id);
        commentRepository.update(commentUpdateParam);
    }

    public void delete(Long id)
    {
        List<Comment> replys = commentRepository.findAllReplysByCommentId(id);
        for (Comment reply : replys) {
            commentRepository.delete(reply.getId());
        }
        commentRepository.delete(id);
    }

    public void deleteReply(Long id)
    {
        commentRepository.delete(id);
    }

    public List<Comment> findAllByRequestId(String requestId)
    {
        List<Comment> comments = commentRepository.findAllByRequestId(requestId);
        for (Comment comment : comments) {
            Long departmentId = comment.getDepartmentId();

            Optional<Department> department = departmentRepository.findById(departmentId);
            assert department.isPresent();

            comment.setDepartment(department.get());
            List<CommentFile> commentFiles = commentFileRepository.findByCommentId(comment.getId());
            comment.setCommentFiles(commentFiles);

            for(Comment reply : comment.getReplys())
            {
                Long replyDepartmentId = reply.getDepartmentId();
                Optional<Department> replyDepartment = departmentRepository.findById(replyDepartmentId);
                List<CommentFile> replyFiles = commentFileRepository.findByCommentId(reply.getId());

                assert replyDepartment.isPresent();
                reply.setDepartment(replyDepartment.get());
                reply.setCommentFiles(replyFiles);
            }
        }
        return comments;
    }
}

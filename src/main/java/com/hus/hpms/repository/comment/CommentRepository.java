package com.hus.hpms.repository.comment;

import com.hus.hpms.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository
{
    public Comment save(Comment comment);
    public Comment saveReply(Comment comment);
    public void update(CommentUpdateParam commentUpdateParam);
    public void delete(Long id);
    public List<Comment> findAllByRequestId(String requestId);
    public Optional<Comment> findById(Long id);
    public List<Comment> findAllReplysByCommentId(Long commentId);
}
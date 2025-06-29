package com.hus.hpms.repository.commentFile;

import com.hus.hpms.domain.CommentFile;

import java.util.List;
import java.util.Optional;

public interface CommentFileRepository
{
    public CommentFile save(CommentFile commentFile);
    public List<CommentFile> findByCommentId(Long commentId);
    public Optional<CommentFile> findById(Long id);

}

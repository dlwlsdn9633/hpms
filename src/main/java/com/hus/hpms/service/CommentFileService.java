package com.hus.hpms.service;

import com.hus.hpms.domain.CommentFile;
import com.hus.hpms.repository.commentFile.CommentFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentFileService
{
    private final CommentFileRepository commentFileRepository;

    public CommentFile save(CommentFile commentFile)
    {
        return commentFileRepository.save(commentFile);
    }
    public CommentFile findById(Long id)
    {
        Optional<CommentFile> commentFile = commentFileRepository.findById(id);
        return commentFile.orElse(null);
    }
}

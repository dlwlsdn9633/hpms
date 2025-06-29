package com.hus.hpms.controller;

import com.hus.hpms.domain.CommentFile;
import com.hus.hpms.service.CommentFileService;
import com.hus.hpms.util.FileStore;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping("/api/comment-file")
@RequiredArgsConstructor
public class CommentFileController
{
    private final FileStore fileStore;
    private final CommentFileService commentFileService;

    @ResponseBody
    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {

        CommentFile commentFile = commentFileService.findById(id);
        String storeFileName = commentFile.getStoreFileName();
        String originalName = commentFile.getOriginalName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        log.info("fullPath = {}", fileStore.getFullPath(storeFileName));
        log.info("storeFileName = {}", storeFileName);
        log.info("originalName = {}", originalName);

        String encodedUploadFileName = UriUtils.encode(originalName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}

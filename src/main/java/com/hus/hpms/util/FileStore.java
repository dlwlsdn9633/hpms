package com.hus.hpms.util;

import com.hus.hpms.domain.CommentFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileStore
{
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename)
    {
        return fileDir + filename;
    }
    public List<CommentFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException
    {
        List<CommentFile> commentFiles = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles)
        {
            if(!multipartFile.isEmpty())
            {
                commentFiles.add(storeFile(multipartFile));
            }
        }
        return commentFiles;
    }

    public CommentFile storeFile(MultipartFile multipartFile) throws IOException
    {
        if(multipartFile.isEmpty())
        {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new CommentFile(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName)
    {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName)
    {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}

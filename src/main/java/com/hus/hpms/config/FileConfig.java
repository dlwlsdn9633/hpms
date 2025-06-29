package com.hus.hpms.config;

import com.hus.hpms.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileConfig
{
    @Value("${file.dir}")
    private String fileDir;

    @Bean
    public FileStore fileStore()
    {
        File folder = new File(fileDir);
        if(!folder.exists())
        {
            try
            {
                folder.mkdir();
                log.info("[+] Success To Create");
            }
            catch(Exception e)
            {
                log.error("error", e);
            }
        }
        else
        {
            log.info("[-] Already Existed The File");
        }
        return new FileStore();
    }
}

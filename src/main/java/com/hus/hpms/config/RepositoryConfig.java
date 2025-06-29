package com.hus.hpms.config;

import com.hus.hpms.repository.comment.CommentRepository;
import com.hus.hpms.repository.comment.Impl.JdbcCommentRepository;
import com.hus.hpms.repository.commentFile.CommentFileRepository;
import com.hus.hpms.repository.commentFile.Impl.JdbcCommentFileRepository;
import com.hus.hpms.repository.department.DepartmentRepository;
import com.hus.hpms.repository.department.Impl.JdbcDepartmentRepositoryImpl;
import com.hus.hpms.repository.request.Impl.JdbcRequestRepository;
import com.hus.hpms.repository.request.RequestRepository;
import com.hus.hpms.service.CommentFileService;
import com.hus.hpms.service.CommentService;
import com.hus.hpms.service.DepartmentService;
import com.hus.hpms.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfig
{
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DepartmentRepository departmentRepository()
    {
        return new JdbcDepartmentRepositoryImpl(dataSource, passwordEncoder);
    }
    @Bean
    public CommentRepository commentRepository()
    {
        return new JdbcCommentRepository(dataSource);
    }

    @Bean
    public RequestRepository requestRepository()
    {
        return new JdbcRequestRepository(dataSource);
    }

    @Bean
    public CommentFileRepository commentFileRepository()
    {
        return new JdbcCommentFileRepository(dataSource);
    }
}

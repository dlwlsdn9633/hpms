package com.hus.hpms.config;

import com.hus.hpms.constants.InterceptorPatterns;
import com.hus.hpms.interceptor.AdminCheckInterceptor;
import com.hus.hpms.interceptor.CommitCheckInterceptor;
import com.hus.hpms.interceptor.LoginCheckInterceptor;
import com.hus.hpms.interceptor.MasterCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns(InterceptorPatterns.ALL_PATH_PATTERNS)
                .excludePathPatterns(InterceptorPatterns.AUTH_EXCLUDE_PATH_PATTERNS);

        registry.addInterceptor(new CommitCheckInterceptor())
                        .order(2)
                        .addPathPatterns(InterceptorPatterns.ALL_PATH_PATTERNS)
                        .excludePathPatterns(InterceptorPatterns.AUTH_EXCLUDE_PATH_PATTERNS);

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(3)
                .addPathPatterns(InterceptorPatterns.ADMIN_ADD_PATH_PATTERNS);

        registry.addInterceptor(new MasterCheckInterceptor())
                .order(4)
                .addPathPatterns(InterceptorPatterns.MASTER_ADD_PATH_PATTERNS);
    }
}

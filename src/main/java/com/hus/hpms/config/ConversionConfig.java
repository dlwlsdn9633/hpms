package com.hus.hpms.config;

import com.hus.hpms.converters.DepartmentToDepartmentSessionConverter;
import com.hus.hpms.converters.RequestCRUDParamToRequestsListConverter;
import com.hus.hpms.converters.RequestsListToRequestUpdateGetParamConverter;
import com.hus.hpms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@RequiredArgsConstructor
public class ConversionConfig
{
    private final DepartmentService departmentService;
    @Bean
    public DefaultConversionService defaultConversionService()
    {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new RequestCRUDParamToRequestsListConverter());
        conversionService.addConverter(new RequestsListToRequestUpdateGetParamConverter(departmentService));
        conversionService.addConverter(new DepartmentToDepartmentSessionConverter());

        return conversionService;
    }
}

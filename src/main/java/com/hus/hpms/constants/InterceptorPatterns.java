package com.hus.hpms.constants;

import java.util.ArrayList;
import java.util.List;

public interface InterceptorPatterns
{
    public String ALL_PATH_PATTERNS = "/**";

    public List<String> AUTH_EXCLUDE_PATH_PATTERNS = List.of(
        "/login", "/logout", "/css/**", "/*.ico", "/error/**", "/register", "/api/**"
    );
    public List<String> ADMIN_ADD_PATH_PATTERNS = List.of(
            "/master", "/request/update/**", "/api/request/**", "/request/create"
    );
    public List<String> MASTER_ADD_PATH_PATTERNS = List.of(
            "/master"
    );
}

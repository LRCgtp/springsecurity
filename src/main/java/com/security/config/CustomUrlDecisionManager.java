package com.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            //url请求需要的权限
            String needAuth = configAttribute.getAttribute();
            //判断登录
            if ("ROLE_LOGIN".equals(needAuth)){
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("还未登录，请先登录");
                }
                return;
            }

            //判断当前用户权限是否满足当前url请求权限
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (needAuth.equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员!!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

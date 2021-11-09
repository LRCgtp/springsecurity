package com.security.config;

import com.security.constant.SysConstants;
import com.security.model.SysMenu;
import com.security.model.SysRole;
import com.security.service.SysMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*    1.查询每一个资源的所有权限，拦截request，将requestUrl与资源url匹配

        将匹配到的资源对应的所有权限存入SpringSecurity管理

        未匹配到的资源，默认为登录即可访问的资源

        2.制定决策，获取当前登录用户，将用户的权限集合与之前存储的访问资源

        的权限集合进行匹配，如果有交集就说明当前用户拥有权限访问该资源*/
@Component
public class CustomFilter implements  FilterInvocationSecurityMetadataSource {

    private static Map<String,List<SysMenu>> menuMap=new ConcurrentHashMap<>();
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher=new AntPathMatcher();
        //获取当前访问的资源路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl().split("\\?")[0];
        //查询所有的菜单和以及菜单对应的权限
        List<SysMenu> menuTreeAndRoles = CustomFilter.menuMap.get(SysConstants.MENU_KEY);
        if (CollectionUtils.isEmpty(menuTreeAndRoles)){
            menuTreeAndRoles=sysMenuService.findMenuTreeAndRoles();
            CustomFilter.menuMap.put(SysConstants.MENU_KEY,menuTreeAndRoles);
        }
        for (SysMenu menu : menuTreeAndRoles) {
            if (antPathMatcher.match(menu.getUrl(),requestUrl.substring(1,requestUrl.length()))){
                //String[] roleArray = menu.getSysRoles().stream().map(SysRole::getRoleName).toArray(String[]::new);
                return SecurityConfig.createList(menu.getPerms());
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

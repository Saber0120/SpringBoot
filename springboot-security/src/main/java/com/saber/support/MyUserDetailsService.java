package com.saber.support;

import com.saber.entity.SysPermission;
import com.saber.entity.SysRole;
import com.saber.entity.SysUser;
import com.saber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载特定用户信息
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 返回用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole sysRole : sysUser.getRoleList()) {
            for (SysPermission sysPermission : sysRole.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}

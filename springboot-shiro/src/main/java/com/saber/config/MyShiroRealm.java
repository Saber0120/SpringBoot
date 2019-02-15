package com.saber.config;

import com.saber.dao.PermissionMapper;
import com.saber.dao.RoleMapper;
import com.saber.entity.Role;
import com.saber.entity.User;
import com.saber.service.UserService;
import com.saber.util.ByteSourceUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("权限配置： --> doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roleList = roleMapper.findRoleByUsername(user.getUsername());
        LOGGER.info("用户：{}，角色有{}个", user.getUsername(), roleList.size());
        roleList.stream().forEach(role -> {
            simpleAuthorizationInfo.addRole(role.getName());
            permissionMapper.findPermissionByRoleId(role.getId()).stream().forEach(permission -> {
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            });
        });
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("认证：--> doGetAuthenticationInfo()");
        // 用户账号
        String username = (String) token.getPrincipal();
        LOGGER.info("用户名：" + username);
        User user = userService.findUserByUsername(username);
        if (user != null) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    user, user.getPassword(), ByteSourceUtils.bytes(user.getCredentialsSalt()), getName());

            return simpleAuthenticationInfo;
        }
        return null;
    }
}

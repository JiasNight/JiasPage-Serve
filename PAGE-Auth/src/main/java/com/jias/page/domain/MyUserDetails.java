package com.jias.page.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author JSON
 * @date 2024/5/29
 * @description
 */
public class MyUserDetails implements UserDetails, Serializable {

    private String id;
    private String username;
    private String password;
    private Integer status;
    private Set<String> permissions;
    private  Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(SysUser sysUser) {
        this.id = sysUser.getUserId();
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 0;
    }
}

package com.example.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 10:25
 **/
@Data
public class User implements UserDetails {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String username;

    private String password;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    /**
     * 角色
     * ps:如果是角色必须以 <strong> ROLE_ </strong> 作为前缀
     * 权限不用加
     */
    private String roles;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

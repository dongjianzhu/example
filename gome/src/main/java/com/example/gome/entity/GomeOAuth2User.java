package com.example.gome.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商家信息
 * @author: dongjianzhu
 * @create: 2021-09-24 10:25
 **/
@Data
public class GomeOAuth2User implements OAuth2User, Serializable {

    private static final long serialVersionUID = 5585109542883481681L;

    /**
     * 权限OAuth2定义的，可以从接口获取
     * 统一USER角色
     * 如果有权限需要在设置
     * private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
     */
    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    /**
     * 额外的属性OAuth2定义的
     */
    @TableField(exist = false)
    private Map<String, Object> attributes;

    /**
     * 该属性是OAthu2必须有的
     * 由shopName映射到店铺名
     */
    @TableField("shopName")
    private String name;

    /**
     *
     */
    private String token;

    /**
     * token过期时间
     */
    private Date issuedAt;

    /**
     * token过期时间
     */
    private Date expiresAt;

    /**
     *
     */
    private String openName;

    /**
     * 店铺编号
     */
    @TableId
    private String venderId;

    /**
     * 店铺级别
     */
    private String shopLevel;

    /**
     * 店铺类型
     */
    private String shopType;

    /**
     * 店铺logo的地址
     */
    private String logo;

    /**
     * 店铺简介
     */
    private String description;

    /**
     * 开通时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    /**
     * 公司名称
     */
    private String corpName;

    /**
     * 公司所在城市
     */
    private String city;

    /**
     * 	公司所在地址
     */
    private String address;

    /**
     * 	负责人
     */
    private String undertacker;

    /**
     * 客服电话
     */
    private String phone;

    /**
     * 信息可信度
     */
    private Double credibilityScore;

    /**
     * 送货速度
     */
    private Double distributionScore;

    /**
     * 服务评分
     */
    private Double serviceScore;

    /**
     * 店铺分类Id
     */
    private String sellCtgy;

    /**
     * 	状态：0：已初始化；1：待初始化；2：冻结；3：休店：4：营业；5：关闭； 6：已发送；7：oa审核失败；8：atg审核失败
     */
    private Short status;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return name;
    }
}

package com.wangguang.model.log;

import com.wangguang.model.BaseEntity;
import com.wangguang.model.sys.User;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Entity - 管理员 登录日志
 *
 * @author xingkong1221
 * @since 2016-11-18
 */
@Entity
@Table(name = "sys_user_login_log")
public class AdminLogLogin extends BaseEntity {

    private static final long serialVersionUID = 6601175582161491080L;

    /**
     * 管理员编号
     */
    private Integer userId;

    /**
     * 管理员
     */
    private User user;

    /**
     * ip地址
     */
    private String ip;


    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

package io.critsu.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * 处理完的权限实体类<br>
 *     包含用户Id信息和权限的Map信息
 *     @see AuthorityOrigin
 */
public class Authority {

    private long userId;
    private Map authorities;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("authorities", authorities)
                .toString();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Map map) {
        this.authorities = map;
    }
}

package io.critsu.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 从权限表取出来的原始数据实体类<br>
 * 通常以集合取出来<br>
 * 经过处理之后变成 Authority 对象<br>
 *     @see Authority
 */
public class AuthorityOrigin {

    private long userId;
    private String authName;
    private String authValue;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("authName", authName)
                .append("authValue", authValue)
                .toString();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }
}

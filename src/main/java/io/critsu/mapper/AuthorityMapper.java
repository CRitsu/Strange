package io.critsu.mapper;

import io.critsu.entities.AuthorityOrigin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {

    /**
     * 拉取指定用户的权限
     * @param uid 指定用户
     * @return 权限map
     */
    List<AuthorityOrigin> getAuthority(long uid);

    /**
     * 保存多个权限<br>
     * 需要确保数据没有冲突<br>
     * 这个方法需要事务管理
     * @param authorityOrigins 保存的权限
     * @return 影响行数
     */
    int setAuthorities(List<AuthorityOrigin> authorityOrigins);

    /**
     * 删除用户信息时，删除用户的权限信息
     * @param uid 用户id
     * @return 影响行数
     */
    int removeAuthorityById(long uid);

    /**
     * 保存一个权限
     * @param authorityOrigin 保存的权限
     * @return 影响行数
     */
    int setAuthority(AuthorityOrigin authorityOrigin);

    /**
     * 删除指定权限
     * @param uid 用户id
     * @param authName 权限名
     * @return 影响行数
     */
    int removeAuthorityByName(@Param("userId") long uid, @Param("authName") String authName);

    /**
     * 更新指定的权限
     * @param authorityOrigin 指定权限
     * @return 影响行数
     */
    int updateAuthority(AuthorityOrigin authorityOrigin);

    /**
     * 获得指定的权限信息
     * @param uid 用户id
     * @param authName 获取的权限名
     * @return 权限信息
     */
    String getAuthorityByName(@Param("userId") long uid, @Param("authName") String authName);

}

package io.critsu.mapper;

import io.critsu.entities.Attribute;
import io.critsu.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员工具专用映射
 */
public interface ManagementMapper {


    /**
     * 获取全表用户
     * @param orderItem 排序项目
     * @param order 升序 ASC；降序 DESC
     * @param limit 分页过滤条数
     * @param size 分页大小（取条数）
     * @return 全用户信息
     */
    List<User> getAllUsers(@Param("orderItem") String orderItem, @Param("order") String order,
                           @Param("limit") Integer limit, @Param("pageSize") Integer size);

    /**
     * 获得所有的属性设定
     * @return 属性list
     */
    List<Attribute> getAllAttributes();

    /**
     * 添加属性
     * @param attribute 属性
     * @return 影响行数
     */
    int addAttribute(Attribute attribute);

    /**
     * 批量添加属性
     * @param attributes 属性list
     * @return 影响行数
     */
    int addMoreAttributes(List<Attribute> attributes);

    /**
     * 更新属性
     * @param attribute 属性
     * @return 影响行数
     */
    int updateAttribute(Attribute attribute);

    /**
     * 删除一个属性
     * @param attribute 属性名
     * @return 影响行数
     */
    int removeAttribute(String attribute);

    /**
     * 删除一堆属性
     * @param attributes 一堆属性名
     * @return 影响行数
     */
    int removeMoreAttributes(String[] attributes);

}

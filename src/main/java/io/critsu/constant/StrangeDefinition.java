package io.critsu.constant;

/**
 * 常数定义
 */
public class StrangeDefinition {

    /**
     * 对所有0,1 flag定义
     * 1 = true
     */
    public static final int FLAG_ENABLE = 1;

    /**
     * 对所有0,1 flag定义
     * 0 = false
     */
    public static final int FLAG_DISABLE = 0;

    /**
     * 删除flag 真 1
     */
    public static final int DELETE_FLAG_TRUE = 1;

    /**
     * 删除flag 假 0
     */
    public static final int DELETE_FLAG_FALSE = 0;

    /**
     * insert 执行单行插入正常时
     * delete 执行单行删除正常时
     * update 执行单行更新正常时
     * 返回行数为1
     */
    public static final int SINGLE_SUCCEED = 1;

    /**
     * insert 执行单行插入失败时
     * delete 执行单行删除失败时
     * update 执行单行更新失败时
     * 返回行数为1
     */
    public static final int SINGLE_FAILED = 0;

    /**
     * 存在Flag
     * 存在 = 1
     */
    public static final int FLAG_EXISTS = 1;

    /**
     * 存在Flag
     * 不存在 = 0
     */
    public static final int FLAG_NOT_EXISTS = 0;


    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

}

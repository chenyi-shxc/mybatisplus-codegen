package com.sinosoft.common.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BaseQueryObject
 * @Description 查询参数基类, 用来封装统一的查询参数, 主要包括:分页参数,时间范围查询,数据时点
 * @Author Chenyi
 * @Date 2020/5/09 11:10
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQueryObject implements Serializable {
    /**
     * 当前页码,默认为1
     */
    private Long pageNum = 1L;
    /**
     * 分页大小,默认为10
     */
    private Long pageSize = 10L;
    /**
     * 时间范围筛选对应数据库字段名
     * 说明:由于不同对象有不同的时间查询字段,因此由前端传参来控制
     * 示例:REG_DATE
     */
    private String timeFieldName;
    /**
     * 开始时间
     * 说明:可以为空
     */
    private String startTime;
    /**
     * 截止时间
     * 说明:可以为空
     */
    private String endTime;
    /**
     * 排序语句
     * 说明: 支持","分隔
     * 示例: REG_DATE DESC,ENTRY_DATE ASC
     */
    private String orderByStr;
    /**
     * 数据时点
     */
    private String dataDate;
}

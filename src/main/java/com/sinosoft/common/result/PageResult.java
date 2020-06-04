package com.sinosoft.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页查询返回结果
 *
 * @param <T>
 * @author Chenyi
 */
@Data
@ApiModel(value = "通用分页查询返回结果", description = "通用查询返回结果")
public class PageResult<T> implements Serializable {
    /**
     * 操作是否成功:true;false
     */
    @ApiModelProperty(value = "操作是否成功")
    private Boolean success;
    /**
     * 返回状态代码
     */
    @ApiModelProperty(value = "返回状态代码")
    private Integer code;
    /**
     * 返回提示信息
     */
    @ApiModelProperty(value = "返回提示信息")
    private String message;
    /**
     * 返回数据结果
     */
    @ApiModelProperty(value = "返回数据结果")
    private List<T> records;
    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private Long total;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long totalPage;

    private PageResult() {
    }

    private PageResult(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private PageResult(Boolean success, Integer code, String message, List<T> records, Long total, Long pageSize) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.records = records;
        this.total = total;
        this.totalPage = (total - 1) / pageSize + 1;
    }

    /**
     * 返回默认失败
     */
    public static <T> PageResult<T> defaultFailure() {
        return new PageResult<T>(false, ResultCode.ERROR, "系统内部异常");
    }

    /**
     * 自定义失败一
     */
    public static <T> PageResult<T> failure(Integer code, String message) {
        return new PageResult<T>(false, code, message);
    }

    /**
     * 默认成功
     *
     * @param records  结果集
     * @param total    总条数
     * @param pageSize 分页大小
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> defaultSuccess(List<T> records, Long total, Long pageSize) {
        return new PageResult<T>(true, ResultCode.SUCCESS, "成功", records, total, pageSize);
    }

    /**
     * 查询结果为空
     *
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> zeroSuccess() {
        return new PageResult<T>(true, ResultCode.SUCCESS, "成功", null, 0L, 10L);
    }

}

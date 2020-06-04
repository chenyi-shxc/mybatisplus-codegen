package com.sinosoft.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用单体查询返回结果
 *
 * @param <T>
 * @author Chenyi
 */
@Data
@ApiModel(value = "通用单体查询返回结果", description = "通用单体查询返回结果")
public class Result<T> implements Serializable {
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
    private T data;

    private Result() {
    }

    private Result(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private Result(T data, Boolean success, Integer code, String message) {
        this.data = data;
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 返回失败，code码和msg自定义
     */
    public static <T> Result<T> newInstance() {
        return new Result<T>();
    }

    /**
     * 调用默认成功
     */
    public static <T> Result<T> defaultSuccess(T data) {
        return new Result<T>(data, true, ResultCode.SUCCESS, "成功");
    }

    /**
     * 返回默认失败
     */
    public static <T> Result<T> defaultFailure() {
        return new Result<T>(false, ResultCode.ERROR, "系统内部异常");
    }

    /**
     * 自定义失败一
     */
    public static <T> Result<T> failure(T data, Integer code, String message) {
        return new Result<T>(data, false, code, message);
    }

    /**
     * 自定义失败二
     */
    public static <T> Result<T> failure(Integer code, String message) {
        return new Result<T>(false, code, message);
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Boolean isSuccess() {
        return success;
    }

    public Result<T> success(Boolean success) {
        this.success = success;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }

}

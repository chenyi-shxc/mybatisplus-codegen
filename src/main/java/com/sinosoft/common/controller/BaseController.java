package com.sinosoft.common.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinosoft.common.result.PageResult;
import com.sinosoft.common.result.Result;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @ClassName BaseController
 * @Description Controller 基类
 * @Author Chenyi
 * @Date 2020/5/13 11:44
 * @Version 1.0
 **/
public class BaseController {

    /**
     * 处理结果,带有返回值
     *
     * @param t
     * @return
     */
    protected <T> Result<T> getResult(T t) {
        return isEmpty(t) ? Result.defaultFailure() : Result.defaultSuccess(t);
    }

    /**
     * 处理结果,针对无返回值的操作提示
     *
     * @param bool
     * @return
     */
    protected Result getResult(Boolean bool) {
        return bool ? Result.defaultSuccess(null) : Result.defaultFailure();
    }

    /**
     * 处理分页结果集
     *
     * @param result
     * @return
     */
    protected <T> PageResult<T> getPageResult(Page<T> result) {
        return 0 == result.getTotal() ? PageResult.zeroSuccess() : PageResult
                .defaultSuccess(result.getRecords(), result.getTotal(), result.getSize());
    }
}

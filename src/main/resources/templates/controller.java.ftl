package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${package.Service+'.'+table.serviceName};
import ${package.Entity+'.'+entity};
import com.sinosoft.common.result.*;
import ${queryPackage+'.'+entity+'QueryObject'};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version 1.0
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(value = "${table.comment!} API接口", tags = "${table.comment!} API接口")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    <#assign service = table.serviceName[1..]?uncap_first />
    @Autowired
    private ${table.serviceName} ${service};

    /**
    * ${table.comment!}-添加
    *
    * @return
    */
    @PostMapping
    @ApiOperation(value = "${table.comment!}-添加")
    public Result<${entity}> insert(@RequestBody ${entity} entity) {
        return getResult(${service}.save(entity));
    }

    /**
    * ${table.comment!}-修改
    *
    * @return
    */
    @PatchMapping("/{id}")
    @ApiOperation(value = "${table.comment!}-修改")
    public Result<${entity}> update(@PathVariable Long id, @RequestBody ${entity} entity) {
        return getResult(${service}.updateById(entity));
    }

    /**
    * ${table.comment!}-删除
    *
    * @return
    */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "${table.comment!}-删除")
    public Result<${entity}> delete(@PathVariable Long id) {
        return getResult(${service}.removeById(id));
    }

    /**
    * ${entity}-全列表(不分页)
    *
    * @return
    */
    @GetMapping("/list")
    @ApiOperation(value = "${entity}-全列表(不分页)")
    public Result<List<${entity}>> list() {
        List<${entity}> list = ${service}.list();
        return getResult(list);
    }

    /**
    * ${entity}-条件查询(带分页)
    *
    * @return
    */
    @GetMapping
    @ApiOperation(value = "${entity}-条件查询(带分页)")
    public PageResult<${entity}> pageQuery(${entity+'QueryObject'} queryObject) {
        return getPageResult(${service}.pageQuery(queryObject));
    }
}
</#if>

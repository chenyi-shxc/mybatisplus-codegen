package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${queryPackage+'.'+entity+'QueryObject'};

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
   @Override
   public Page<${entity}> pageQuery(${entity+'QueryObject'} queryObject) {
    Page<${entity}> page = new Page<>(queryObject.getPageNum(), queryObject.getPageSize());
    QueryWrapper<${entity}> query = new QueryWrapper();
    // 根据参数拼装条件
    return page(page, query);
   }

}
</#if>

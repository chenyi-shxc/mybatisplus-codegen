package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${queryPackage+'.'+entity+'QueryObject'};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * <p>
 * ${table.comment!} 服务接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
   /**
    * 分页条件查询
    *
    * @param queryObject 查询参数对象
    * @return
    */
    Page<${entity}> pageQuery(${entity+'QueryObject'} queryObject);

}
</#if>

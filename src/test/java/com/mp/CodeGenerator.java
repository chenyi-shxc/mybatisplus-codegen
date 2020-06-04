package com.mp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName aa
 * @Description TODO
 * @Author Chenyi
 * @Date 2020/5/19 17:38
 * @Version 1.0
 **/
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    // 数据库配置
    private static String url = "jdbc:mysql://192.168.3.100:3306/oas?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "oas";
    private static String password = "oas";

    // 公司名,例如:org.spring
    private static String parentName = "cn.com.cdb";
    // 模块名,例如:demo,注意文件名中不能有"-",否则idea会报错
    private static String moduleName = "oas";
    // 作者姓名
    private static String author = "Chenyi";

    // 要生成的表名
    private static String[] tableNames = new String[]{"zkr_user_collection"};

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setIdType(IdType.AUTO);
        gc.setAuthor(author);
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentName);
        pc.setModuleName(moduleName);
        // 实体类的包名
        pc.setEntity("domain");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }

            @Override
            public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
                // 给freemarker的自定义参数
                // 使用Excel-Boot导出excel
                objectMap.put("excelBoot", true);

                // 设置子类QueryObject路径
                objectMap.put("queryPackage", pc.getParent() + ".query");

                return objectMap;
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        // 自定义Query对象
        String templatePathQuery = "/templates/query.java.ftl";
        focList.add(new FileOutConfig(templatePathQuery) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.getEntityName();
                String domainName = tableInfo.getEntityName();
                return projectPath + "/src/main/java/" + pc.getParent()
                                                           .replace(".", "/") + "/query/" + domainName + "QueryObject" + StringPool.DOT_JAVA;
            }
        });

        // 自定义MapperXML文件路径配置
        String templatePathMapperXML = "/templates/mapper.xml.ftl";
        focList.add(new FileOutConfig(templatePathMapperXML) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // MapperXML由自定义配置
        templateConfig.setXml(null);
        // 其他配置按默认配置
//        templateConfig.setEntity("/templates/entity.java");
//        templateConfig.setXml("/templates/mapper.xml");
//        templateConfig.setMapper("/templates/mapper.java");
//        templateConfig.setService("/templates/service.java");
//        templateConfig.setServiceImpl("/templates/serviceImpl.java");
//        templateConfig.setController("/templates/controller.java");

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        // Lombok
        strategy.setEntityLombokModel(true);
        // builder模式
        strategy.setEntityBuilderModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");

        strategy.setInclude(tableNames);
//        strategy.setControllerMappingHyphenStyle(true);
        // 设置表名前缀,会从实体中删除对应前缀
        strategy.setTablePrefix(pc.getModuleName() + "_", "zkr_");
        // 公共父类
        strategy.setSuperControllerClass("com.sinosoft.common.controller.BaseController");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}

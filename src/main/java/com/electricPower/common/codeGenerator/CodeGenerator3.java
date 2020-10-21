package com.electricPower.common.codeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator3 {

    /**
     *与数据库相关的都改成自己的
     *
     *
     * */
    private static String url = "jdbc:postgresql://10.12.6.169:5432/electricity";
    private static String user = "postgres";
    private static String password = "guowushi";
    private static String driverName = "org.postgresql.Driver";
    private static String author = "";//这里是开发者署名
    private static String outputDir = "/src/main/java/";//新建的Java项目的目录结构
    private static String packageName = "com.electricPower.project";//生成的东西放在这个包里
    private static String tablePrefix = ""; //表前缀
    private static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {

        //数据库连接
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.POSTGRE_SQL)//这里注意后缀.POSTGRE_SQL这个是数据库，用的什么数据库就写什么
                .setUrl(url)
                .setUsername(user)
                .setPassword(password)
                .setDriverName(driverName);

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)   //// 开启 activeRecord 模式
                .setEnableCache(false)  //二级缓存
                .setSwagger2(true) //实体属性 Swagger2 注解
                .setAuthor(author)  //作者
                .setOutputDir(projectPath + outputDir)  //输出目录
                .setFileOverride(false)  //覆盖文件
                .setServiceName("I%sService"); // 自定义文件命名，注意 %s 会自动填充表实体属性！
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper");

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            //代码生成器的模板，通过读取这个模板生成entity、mapper 、service 、implService、xml
            // "/mybatis/mapper.xml.ftl"
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)   // 全局大写命名
//                .setDbColumnUnderline(true)   // 全局下划线命名
                .setEntityLombokModel(true)     //Lombok
                .setNaming(NamingStrategy.underline_to_camel)   // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)     //字段名生成策略
                .setRestControllerStyle(true) //RestController注解
                .setControllerMappingHyphenStyle(true)          //驼峰转连字符
                .setTablePrefix(tablePrefix)  //表前缀
                .setInclude("alarm_info"); // 需要生成的表,默认全部
//                //.setExclude(new String[]{"test"}) // 排除生成的表
//                .setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity") //自定义实体父类
//                .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")   // 自定义 mapper 父类
//                .setSuperServiceClass("com.baomidou.demo.base.BsBaseService") // 自定义 service 父类
//                .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl") // 自定义 service 实现类父类
//                .setSuperControllerClass("com.baomidou.demo.TestController")// 自定义 controller 父类
//                .setEntityColumnConstant(true)    //是否生成字段常量（默认 false）
//                .setEntityBooleanColumnRemoveIsPrefix(true);  // Boolean类型字段是否移除is前缀处理

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setCfg(new InjectionConfig() {
                            @Override
                            public void initMap() {
                            }
                        }.setFileOutConfigList(focList)
                )
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig()
                        .setParent(packageName)
                        .setController("controller")
                        .setEntity("entity")
                )
                //.setTemplate(new TemplateConfig().setXml(null)) //关闭默认的XML生成
                .setTemplateEngine(new FreemarkerTemplateEngine())  //模板引擎
                .execute(); //执行
    }


}


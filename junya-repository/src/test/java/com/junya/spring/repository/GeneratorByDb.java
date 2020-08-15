package com.junya.spring.repository;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Author Junya
 * Date 2020-08-14 11:22
 * Description: 通过数据库生成 bean,service,dao,mapper,controller
 */
public class GeneratorByDb {

    public static void main(String[] args) throws IOException {
        List<String> tables = new ArrayList<String>() {
            {
                add("junya_user");
            }

        };
        GeneratorByDb.generator(tables);
    }

    public static void generator(List<String> tables) throws IOException {
        Path path = Paths.get("D:", "junyaMapper");
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .peek(System.out::println)
                    .forEach(File::deleteOnExit);
        }

        String author = "Junya";
        String tablePreFix = "t_";// 生产的时候会忽略该前缀
        String packageName = "com.junya.spring.repository";
        String projectPath = "D:\\junyaMapper";
        String url="jdbc:mysql://localhost:3306/junya_study?useUnicode=true&characterEncoding=utf8&useAffectedRows=true&serverTimezone=GMT%2B8&autoReconnect=true";
        String userName="root";
        String password="root";
        String driverName="com.mysql.cj.jdbc.Driver";


        // String moduleName="sys";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setEnableCache(false);// 开启二级缓存
        gc.setEntityName("%sBean");
        gc.setIdType(IdType.NONE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName(moduleName);
        pc.setParent(packageName);
        pc.setMapper("dao");
        pc.setEntity("bean");

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String[] split = packageName.split("\\.");
                StringBuilder sBuilder = new StringBuilder();
                for (String str : split) {
                    sBuilder.append(str).append("/");
                }

                return projectPath + "/" + sBuilder.toString() + tableInfo
                        .getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();


        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setTablePrefix(tablePreFix);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//		strategy.setSuperEntityColumns("id");
        strategy.setInclude(tables.toArray(new String[tables.size()]));
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

package cn.oneplustow.mc.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * Author lwj
 * Date 2022/5/28
 * Description TODO
 * BVersion 1.0
 **/
public class myBatisPlus {
    private static final String dbUrl="jdbc:mysql://172.16.51.75:6053/redex_center_message?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&serverTimezone=Hongkong&useSSL=false&allowPublicKeyRetrieval=true&useLocalSessionState=true";
    private static final String dbName="redex";
    private static final String dbPwd="17P0fTdwBnUeZ767";
    public static void main(String[] args) {
        FastAutoGenerator.create(dbUrl, dbName, dbPwd)
                .globalConfig(builder -> {
                    builder.author("jet") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.jet") // 设置父包名
                            .moduleName("eo") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("m_message_inside") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

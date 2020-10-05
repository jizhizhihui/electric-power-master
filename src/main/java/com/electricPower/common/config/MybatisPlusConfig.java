package com.electricPower.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.electricPower.project.mapper")
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        //设置分页数据库类型
        page.setDbType(DbType.POSTGRE_SQL);
        page.setDialect(new MySqlDialect());
        //优化count sql
        page.setCountSqlParser(new JsqlParserCountOptimize(true));
        //设置每页最大值
        page.setLimit(999L);
        return page;
    }
}

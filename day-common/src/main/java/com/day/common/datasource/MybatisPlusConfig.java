package com.day.common.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * Explain:mtbatis-plus的配置信息
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 11:50
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {
        "com.day.*.persistence.mapper",
})
public class MybatisPlusConfig {

}

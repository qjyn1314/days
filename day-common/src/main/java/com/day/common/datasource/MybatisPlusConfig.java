package com.day.common.datasource;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class MybatisPlusConfig {

    @PostConstruct
    public void dataSourcesToString(){
        //参考与：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
        final List<Map<String, Object>> dataSourceStatDataList = DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
        log.info("数据源信息是：{}", dataSourceStatDataList);
    }
}

package com.day.common.datasource;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * <p>
 * Explain:配置seata分布式事务，
 * </p >
 *
 * @author wangjunming
 * @since 2020-10-28 15:54
 */
@Slf4j
@Configuration
//密码加密工具注解
@EnableEncryptableProperties
public class DataSourceConfig {

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    /**
     * 在classpath后面的 * 必不可少，缺少型号的话后面的通配符不起作用。**表示可以表示任意多级目录。
     * 用于寻找mapper类所对应的xml文件，
     *
     * @since 2020/10/28 17:38
     */
    private static final String COMMON_MAPPER_LOCATION = "classpath*:com/day/**/*/mapper/xml/*Mapper.xml";

    /**
     * 手动配置mybatis-plus的数据源事务管理配置
     *
     * @author wangjunming
     * @since 2020/10/28 15:47
     */
    @Bean
    public MybatisSqlSessionFactoryBean mysqlSessionFactory(DataSourceProxy dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(COMMON_MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resources);
        // 解决此异常：org.springframework.dao.TransientDataAccessResourceException: SqlSessionFactory must be using a SpringManagedTransactionFactory in order to use Spring transaction synchronization
        //必须使用  SpringManagedTransactionFactory 事务管理器
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        sqlSessionFactoryBean.setPlugins(plusInterceptor());
        return sqlSessionFactoryBean;
    }

    /**
     * 配置mybatis插件
     *
     * @author wangjunming
     * @since 2020/10/28 17:31
     */
    @Bean
    public MybatisPlusInterceptor plusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页配置
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    private static final String STETA_APPLICATION_ID = "days_seata_application";
    private static final String STETA_TX_SERVICE_GROUP = "days_seata_tx_service_group";

//    @Bean
//    public GlobalTransactionScanner globalTransactionScanner() {
//        return new GlobalTransactionScanner(STETA_APPLICATION_ID, STETA_TX_SERVICE_GROUP);
//    }

}

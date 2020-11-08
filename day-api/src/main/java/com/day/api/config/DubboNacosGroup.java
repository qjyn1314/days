package com.day.api.config;

/**
 * <p>
 * explain: 用于声明dubbo服务在nacos中的分组名称
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/27 16:03
 */
public interface DubboNacosGroup {

    /**
     * today-DUBBO服务在nacos中的分组名称
     */
    String TODAY_DUBBO_NACOS = "today-dubbo-nacos";
    /**
     * yesday-DUBBO服务在nacos中的分组名称
     */
    String YESTERDAY_DUBBO_NACOS = "yesday-dubbo-nacos";


}

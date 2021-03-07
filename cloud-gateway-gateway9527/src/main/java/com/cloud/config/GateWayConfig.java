package com.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author zy
 * @version 1.0.0
 * @ClassName GateWayConfig.java
 * @Description  编码形式配置gateway
 * @CreateDate 2021-02-09  17:41:08
 */


@Configuration
public class GateWayConfig {

    /**
     * 访问百度国内新闻
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_rote_nation", r ->
                r.path("/guonei")    //当前系统访问的地址 http://localhost:9527/guonei
                    .uri("http://news.baidu.com/guonei")).build();  //跳转的地址
        return routes.build();
    }

    /**
     * 访问百度国际新闻
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_rote_nation", r ->
                r.path("/guoji")    //当前系统访问的地址 http://localhost:9527/guoji
                        .uri("http://news.baidu.com/guoji")).build();  //跳转的地址
        return routes.build();
    }
}
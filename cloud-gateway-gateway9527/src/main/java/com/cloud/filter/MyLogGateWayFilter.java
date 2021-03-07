package com.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName MyLogGateWayFilter.java
 * @Description 自定义全局GlobalFilter
 * @CreateDate 2021-03-07  13:48:09
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("*********come in MyLogGateWayFilter: "+new Date());
        //http://localhost:9527/payment/port?username=zy
        String username = exchange.getRequest().getQueryParams().getFirst("username");  //请求参数需要携带username的参数
        if(StringUtils.isEmpty(username)){
            log.info("*****用户名为Null 非法用户,(┬＿┬)");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//给人家一个回应
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);  //过滤链放行
    }

    @Override
    public int getOrder() {   //返回值越小，执行优先级越高
        return 0;
    }
}

package com.cloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker   //开启服务熔断或者服务降级
public class PaymentHystrixMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8005.class,args);
    }

    /**

     *
     *  http://localhost:9001/hystrix   图形化访问地址
     *
     *  监控地址：
     *    低版本直接启动即可使用 http://ip:port/hystrix.stream 查看监控信息
     *    高版本需要添加本方法方可使用 http://ip:port/hystix.stream 查看监控信息
     *    http://localhost:8005/hystrix.stream
     *  此配置是为服务监控图形化界面服务
     *  ServletRegistrationBean因为springboot的默认路径不是“/hystrix.stream”，
     *  所以需要早自己的项目里配置一下servlet就可以了
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
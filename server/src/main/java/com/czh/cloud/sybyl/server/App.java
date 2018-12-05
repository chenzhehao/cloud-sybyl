package com.czh.cloud.sybyl.server;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.czh.cloud")
@EnableCircuitBreaker// 启动断路器
@EnableHystrixDashboard
// 开启dashboard，通过图形化的方式监控: 查看 http://localhost:10030/archetype2/hystrix http://127.0.0.1:12082/archetype2/hystrix.stream
@EnableFeignClients(basePackages = "com.czh.cloud")
@EnableSwagger2
@MapperScan("com.czh.cloud.sybyl.server.mapper")
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            System.setProperty("log.path", "D:/chenzhehao/workspace/czh");
        } else {
            System.setProperty("log.path", "/opt");
        }
        System.setProperty("context.name", "cloud-sybyl-server");

        SpringApplication.run(App.class, args);
    }

    /**
     * 功能描述: hystrix配置入口信息
     * @author: zhehao.chen
     * @version: V1.0
     * @date: 2018/9/13 13:33
     * @param: []
     * @return: org.springframework.boot.web.servlet.ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);  //系统启动时加载顺序
        registrationBean.addUrlMappings("/hystrix.stream");//路径
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
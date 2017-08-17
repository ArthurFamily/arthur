package com.cloud.arthur;

import com.cloud.arthur.tokenFilter.GatewayFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网关路由
 * Created by chenzhen on 2018/8/4.
 */
@SpringCloudApplication
@EnableZuulProxy
public class GateWay {

    private static final Logger LOG = LoggerFactory.getLogger(GateWay.class.getName());

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(GateWay.class);
        //DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        LOG.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{} \n\t" +
                        "External: \thttp://{}:{} \n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name")+"("+env.getProperty("version")+")",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("spring.profiles.active"));
    }

    @Bean
    public GatewayFilter gatewayFilter() {
        return new GatewayFilter();
    }
}

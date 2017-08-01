package com.cloud.arthur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by chenzhen on 2017/7/26.
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigCenter {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigCenter.class.getName());

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ConfigCenter.class);
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
}

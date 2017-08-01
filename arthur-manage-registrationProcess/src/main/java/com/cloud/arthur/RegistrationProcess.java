package com.cloud.arthur;

import com.cloud.arthur.config.WebMvcConfig;
import com.cloud.arthur.config.mybatis.BaseMapper;
import com.cloud.arthur.filter.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理之迎新流程管理.
 * Created by chenzhen on 2017/7/27.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud"})
@Import({WebMvcConfig.class})
@EnableScheduling
@ServletComponentScan
@RestController
@MapperScan(value = "**.mapper", markerInterface = BaseMapper.class)
@EnableDiscoveryClient
// 断路器使用 http://localhost:10003/monitor/hystrix.stream
@EnableHystrix
public class RegistrationProcess {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationProcess.class.getName());

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(RegistrationProcess.class);
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

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("--------from spring.datasource.maxWait: "
                + env.getProperty("spring.datasource.maxWait"));
    }

    @Value("${spring.datasource.maxWait}")
    private String appName;

    @RequestMapping("/test")
    public String getAppName() {
        return appName;
    }

    /**
     * 服务器直接运行执行jar无法跳转登录，so
     * @param httpServletResponse
     * @param httpServletRequest
     * @throws Exception
     */
/*    @RequestMapping("/")
    public void jarUrlPath(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception{
        LOG.info("***小样你来了***");
        HttpSession session = httpServletRequest.getSession();
        if (StringUtils.isNotEmpty((String) session.getAttribute("account"))) {
            httpServletResponse.sendRedirect("/index.html");
        } else {
            httpServletResponse.sendRedirect("/login.html");
        }

    }*/

    // 过滤器 配置
    //@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LoginFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("*.html");
        urlPatterns.add("/mvc/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}

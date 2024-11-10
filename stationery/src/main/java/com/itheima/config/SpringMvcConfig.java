package com.itheima.config;


import com.itheima.interceptor.ResourcesInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Properties;

@Configuration
@PropertySource("classpath:ignoreUrl.properties")
//等同于<context:component-scan base-package="com.itheima.controller"/>
@ComponentScan({"com.itheima.controller"})
/*@Import({MyWebMvcConfig.class})*/
@EnableWebMvc
public class SpringMvcConfig  implements WebMvcConfigurer {
  @Value("#{'${ignoreUrl}'.split(',')}")
    private List<String> ignoreUrl;
    @Bean
    public ResourcesInterceptor resourcesInterceptor(){
        return new ResourcesInterceptor(ignoreUrl);
    }
    /*
     * 在注册的拦截器类中添加自定义拦截器
     * addPathPatterns()方法设置拦截的路径
     * excludePathPatterns()方法设置不拦截的路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( resourcesInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**","/img/**");
    }

    /*
     *开启对静态资源的访问
     * 类似在Spring MVC的配置文件中设置<mvc:default-servlet-handler/>元素
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/admin/",".jsp");
    }

    @Bean  
    public HandlerExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
  
        // 定义需要特殊处理的异常映射  
        Properties exceptionMappings = new Properties();
        exceptionMappings.setProperty("java.lang.NullPointerException", "nullPointerExp");  
        exceptionMappings.setProperty("java.io.IOException", "IOExp");  
        resolver.setExceptionMappings(exceptionMappings);  
  
        // 为所有异常定义默认的异常处理页面  
        resolver.setDefaultErrorView("error");  
  
        // 定义在异常处理页面中获取异常信息的变量名  
        resolver.setExceptionAttribute("exp");  
  
        return resolver;  
    }  

}





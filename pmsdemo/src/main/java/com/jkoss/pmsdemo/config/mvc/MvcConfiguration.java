package com.jkoss.pmsdemo.config.mvc;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jkoss.common.util.Constant;

/**
 * MVC配置类
 * 
 * @Author chair
 * @Version 1.0, 2017年8月22日
 * @See
 * @Since com.jk.bestbaby.config
 * @Description: TODO
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * 多媒体上传
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(1024L * 1024L * 1024L));
		return factory.createMultipartConfig();
	}

//	/**
//	 * 
//	 * 自定义映射路径
//	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
//		registry.addResourceHandler(Constant.IMAGES_URL + "/**").addResourceLocations("file:" + Constant.IMAGES_PATH);
//		super.addResourceHandlers(registry);
//	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new
		// LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin",
		// "/toNoAuth", "/login", "/logout", "/toTimeOut", "/error", "/image/**",
		// "/exec","/listTSubjectByKcmdelid","/listTCoursemodelByKcid","/listTCourseByKcid","/toInsert");
		// registry.addInterceptor(new
		// AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin",
		// "/toNoAuth", "/login", "/logout", "/toTimeOut", "/error", "/image/**",
		// "/exec","/listTSubjectByKcmdelid","/listTCoursemodelByKcid","/listTCourseByKcid","/toInsert");
		// super.addInterceptors(registry);
	}

}

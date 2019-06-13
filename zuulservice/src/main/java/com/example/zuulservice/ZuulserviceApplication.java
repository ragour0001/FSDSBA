package com.example.zuulservice;

import com.example.zuulservice.filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

  @Bean
  public FilterRegistrationBean jwtFilter() {
    final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new JWTFilter());
    registrationBean.addUrlPatterns("/favouriteservice/api/v1/favouriteservice/user/*");
    //registrationBean.addUrlPatterns("/articleRecommendationService/api/v1/recommendedservice/*");

    return registrationBean;
  }

}

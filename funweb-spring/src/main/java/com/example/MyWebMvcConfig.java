package com.example;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor.AjaxLoginCheckInterceptor;
import com.example.interceptor.MemberLoginCheckInterceptor;
import com.example.interceptor.MemberStayLoggedInInterceptor;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private  MemberLoginCheckInterceptor memberLoginCheckInterceptor;
	@Autowired
	private MemberStayLoggedInInterceptor memberStayLoggedInInterceptor;
	@Autowired
	private AjaxLoginCheckInterceptor ajaxLoginCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 회원 로그인 확인 인터셉터 등록하기
		InterceptorRegistration registration = registry.addInterceptor(memberLoginCheckInterceptor);
		// 인터셉터가 수행될 URL 주소 경로 추가
		//registration.addPathPatterns("/notice/write");
		registration.addPathPatterns("/notice/*");
		registration.addPathPatterns("/fileNotice/*");
		// 인터셉터 수행에서 제외할 URL 주소 경로 추가
		registration.excludePathPatterns("/notice/list", "/notice/content");
		registration.excludePathPatterns("/fileNotice/list", "/fileNotice/content");
		
		// Ajax용 회원 로그인 확인 인터셉터 등록하기
		registry.addInterceptor(ajaxLoginCheckInterceptor)
		.addPathPatterns("/comment/*")
		.excludePathPatterns("/comment/one/*", "/comment/pages/*");
		
		// 회원 로그인 상태유지 처리 인터셉터 등록하기
		registry.addInterceptor(memberStayLoggedInInterceptor)
		.addPathPatterns("/*");
	} // addInterceptors
	
	
    // 스프링이 @Configuration 클래스의 @Bean 메소드를 자동호출해서
	// 메소드로부터 리턴받은 객체를 스프링 빈으로 등록해줌.
	// 기본생성자 호출로 준비할수 없는 스프링 빈들은 이 방식으로 준비함. 
	@Bean
	public Timer timer() {
		return new Timer(true);
	}
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}




package site.gongtong.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.gongtong.security.interceptor.JwtTokenInterceptor;


/**
 * SSR(Server Side Rendering)전용 WebConfig 설정
 */

@Slf4j
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**") // 모든 URL에 대해 JWT 토큰 검사를 적용
                .excludePathPatterns("/member/login", "/login", "/main/rootPage"); // 로그인 페이지는 JWT 토큰 검사에서 제외
    }

    /**
     * 정적 리소스가 위치할 수 있는 경로를 나열하기
     */ //추후 수정
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/"
    };

    /**
     * 여기서는 루트 URL("/")에 접근했을 때 "/login"으로 리다이렉트하도록 설정
     * => 추후 수정
     */ //추후 수정
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("[+] WebConfig Start !!! ");
        registry.addRedirectViewController("/", "/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 정적 리소스를 처리하는 핸들러를 추가하는데 사용.
     * 여기서는 모든 요청("/**")에 대해 CLASSPATH_RESOURCE_LOCATIONS에 지정된 경로에서 리소스를 찾도록 설정하고 있다.
     */ //추후 수정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

}

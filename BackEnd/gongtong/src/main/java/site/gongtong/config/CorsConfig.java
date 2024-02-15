package site.gongtong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   // 모든 경로를 설정
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS") // 모든 http Method 허용
                .allowedOrigins("https://www.boarda.site:3000", "http://localhost:3000", "https://www.boarda.site")
                .allowedHeaders("jwt")
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3600);
        //.allowedOrigins("http://localhost:3000");
    }
}
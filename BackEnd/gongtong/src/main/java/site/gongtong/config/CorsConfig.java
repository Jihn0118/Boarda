package site.gongtong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")   // 모든 경로를 설정
                .allowedMethods("*")            // 모든 http Method 허용
                .allowedOrigins("https://www.boarda.site:3000", "http://localhost:3000");
    }
}
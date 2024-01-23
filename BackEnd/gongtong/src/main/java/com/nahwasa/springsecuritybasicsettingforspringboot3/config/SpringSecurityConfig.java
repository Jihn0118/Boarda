package com.nahwasa.springsecuritybasicsettingforspringboot3.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/status", "/images/**", "/view/join", "/auth/join").permitAll() //로긴 안 해도 이건 봐야지
//                        .requestMatchers("/view/setting/admin").hasRole("ADMIN") //어드민인 애만 갈 수 있음
//                        .requestMatchers("/view/setting/user").hasRole("USER") //유저인 애만 갈 수 있음
                                .anyRequest().authenticated() //어떤 요청이라도 인증 필요(어딜 가든 please sign in 페이지 뜸)
                )
                .formLogin(login -> login //form방식 로그인 사용
                        .loginPage("/view/login") // [a] 커스텀 로긴 페이지 지정
                        .loginProcessingUrl("/login-process") //[b] 서밋 받을 url
                        .usernameParameter("userid") //[c]서밋할 아이디 (userid로만 ok되나)
                        .passwordParameter("pw") //[d] 서밋할 비번 (아하 변수인듯)
                        .defaultSuccessUrl("/view/dashboard", true) //성공시 dashboard로(스프링부트 시큐리티 자체적인 페이지)
                        .permitAll() //대시보드 이동이 막히면 안되므로 얘는 허용
                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // 로그아웃 URL을 지정 (기본값은 /logout)
//                        .logoutSuccessUrl("/view/join") // 로그아웃 성공 시 이동할 URL
//                        .permitAll()
//                ); //로그아웃 시, 회원가입 페이지로 이동하는 방법.
                .logout(withDefaults()); // 로그아웃은 기본설정으로 (/logout으로 인증해제)
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
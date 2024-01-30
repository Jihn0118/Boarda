package site.gongtong.member.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .csrf((csrfConfigurer) ->
                        csrfConfigurer.disable()
                )
                .authorizeHttpRequests(authorize -> authorize
//                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/member/**").permitAll()
//                                .requestMatchers("/mem/**").authenticated()
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
//                        .usernameParameter("id")
//                        .passwordParameter("password")
//                        .loginPage("/member/login").permitAll()
//                        .defaultSuccessUrl("/main") //항상 리다이렉트 수행
//                        .failureUrl("/member/login")
                                .disable()
                )
                .rememberMe(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout").permitAll()
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .logout(Customizer.withDefaults());
                .exceptionHandling() //예외 처리2
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/member/login")) //1. 로그인 아닌 사용자가 보호된 리소스에 액세스하려고 할 때 -> 로그인 페이지로 이동
                .accessDeniedHandler(new AccessDeniedHandlerImpl()); //2. 권한 없음 -> 화이트라벨 ;
        return http.build();


//                //oauth로그인
//                .and()
//                .oauth2Login()
//                    .loginPage("/")
//                    .defaultSuccessUrl("/")
//                    .userInfoEndpoint()
//                        .userService(principalOauth2UserService)
//        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
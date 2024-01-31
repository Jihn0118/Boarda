package site.gongtong.member.config;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.gongtong.member.filter.LoginAuthenticationFilter;

/* 검색: Filter Chain 확인하는 방법 */

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableMethodSecurity
public class SecurityConfig {

    
    boolean securityDebug;
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{

        //필터 관련
        AuthenticationManagerBuilder shareObject = http.getSharedObject(AuthenticationManagerBuilder.class); //생성된 토큰으로 인증 task 담당하는 곳 - 경비원 건설업자?
        AuthenticationManager authenticationManager = shareObject.build(); //'사용자 인증 처리'하는 객체가 생성되어 반환 - 경비원 고용
        http.authenticationManager(authenticationManager);

        http
//                .csrf((csrfConfigurer) ->
//                        csrfConfigurer.disable()
//                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
//                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/member/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN") //행동에 대해서도 권한설정 가능
//                                .requestMatchers("/mem/**").authenticated()
                                .anyRequest().authenticated()
                )
                //필터 관련 (addFilter, header)
                .addFilterAt(
                        this.abstractAuthenticationProcessingFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class //이 필터 있는 자리에 추가
                )
                .headers(
                        headersConfig -> headersConfig
                                .frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::sameOrigin //같은 베이스는 허용
                                )
                                .contentSecurityPolicy(
                                        policyConfig -> policyConfig
                                                .policyDirectives(
                                                        "script-src 'self'; " //스크립트는 동일한 출처에서만 로딩 가능
                                                        + "img-src 'self'; " //이미지는 동일한 출처에서만 로딩 가능
                                                        + "font-src 'self' data:; " //폰트는 동일한 출처 및 data: 프로토콜에서 로딩 가능
                                                        + "default-src 'self'; " //그 외 자원은 동일한 출처에서만 로딩 가능
                                                        + "frame-src 'self'" //프레임은 동일한 출처에서만 로딩 가능
                                                )
                                )
                )
                .formLogin(login -> login
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/main") //항상 리다이렉트 수행
                        .failureUrl("/member/login")
                                .disable()
                )
//                .rememberMe(Customizer.withDefaults())
//                .logout(logout -> logout
//                        .logoutUrl("/logout").permitAll()
//                        .logoutSuccessUrl("/")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .logout(Customizer.withDefaults());
                .exceptionHandling() //예외 처리2
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/member/login")) //1. 로그인 아닌 사용자가 보호된 리소스에 액세스하려고 할 때 -> 로그인 페이지로 이동
                .accessDeniedHandler(new AccessDeniedHandlerImpl()); //2. 권한 없음 -> 화이트라벨
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
//                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationfilter 전에 넣기
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

    //필터 관련 (로그인 처리 제일 전면부)
    private AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(final AuthenticationManager authenticationManager) {
        return new LoginAuthenticationFilter(
//                "/member/login",
                "/login",
                authenticationManager
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

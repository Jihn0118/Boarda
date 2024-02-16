package site.gongtong.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import site.gongtong.member.service.CustomMemberDetailsService;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.security.filter.CustomAuthenticationFilter;
import site.gongtong.security.filter.JwtAuthorizationFilter;
import site.gongtong.security.handler.CustomAuthFailureHandler;
import site.gongtong.security.handler.CustomAuthSuccessHandler;
import site.gongtong.security.handler.CustomAuthenticationProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

/* 검색: Filter Chain 확인하는 방법 */

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration //(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    /**
     * 이 메서드는 정적 자원에 대해 보안을 적용하지 않도록 설정
     * 이들에 대해 보안을 적용하지 않음으로써 '성능을 향상'시킬 수 있음
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //정적 리소스 보안 적용 해제
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() { //여기서 cors 커스텀 가능
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*")); //,   "https://www.boarda.site:3000", "http://localhost:3000"));
        configuration.setAllowedOrigins(Arrays.asList("https://www.boarda.site:3000", "http://localhost:3000", "https://www.boarda.site", "https://boarda.site")); //,   "https://www.boarda.site:3000", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Content-Type", "Authorization", "X-XSRF-token", "jwt"));
        configuration.setExposedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); //1시간

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Spring Security 설정을 정의하는 메서드.
     * <ul>
     *     <li>CSRF 방지 기능 비활성화</li>
     *     <li>CORS 설정 적용</li>
     *     <li>어디 경로 접근 허용?</li>
     *     <li>나머지 요청은 인증된 사용자만 접근 허용</li>
     *     <li>헤더의 JWT 토큰을 추출하고 검증하는 `JwtAuthorizationFilter` 적용</li>
     *     <li>세션을 상태 없이 관리</li>
     *     <li>폼 로그인 사용 안 함</li>
     *     <li>사용자 이름과 비밀번호 인증을 위한 `CustomAuthenticationFilter` 적용</li>
     * </ul>
     *
     * @param http                       Spring Security의 HttpSecurity 객체
     * @param customAuthenticationFilter 사용자 정의 인증 필터
     * @param jwtAuthorizationFilter     JWT 인증 필터
     * @return 구성된 SecurityFilterChain 객체
     * @throws Exception 설정 중 발생할 수 있는 예외
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomAuthenticationFilter customAuthenticationFilter,
                                           JwtAuthorizationFilter jwtAuthorizationFilter //헤더의 JWT 토큰을 추출하고 검증
    ) throws Exception {
        log.debug("[+] WebSecurityConfig Start !!! ");
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                //이후에 허용할 페이지, 막을 페이지 나누기
//                .authorizeHttpRequests(authorize -> authorize
//                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                                .requestMatchers("/member/**", "/mypage/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN") //행동에 대해서도 권한설정 가능
//                                .requestMatchers("/mem/**").authenticated()
//                                .anyRequest().permitAll()
//                )
                //BasicAuthenticationFilter:
                // 사용자 이름과 비밀번호를 요청 헤더에 Base64로 인코딩하여 보내는 간단한 인증 방식

                // 1. 먼저, JwtAuthorizationFilter가 실행되어 요청 헤더에서 JWT 토큰을 추출하고 이 토큰을 검증
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 2. CustomAuthenticationFilter가 실행되어 사용자 이름과 비밀번호를 사용하여 인증을 수행
                // 이 필터는 주로 로그인 요청을 처리하는 데 사용된다.
//                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * 1. 커스텀을 수행한 '인증' 필터로
     * 접근 URL, 데이터 전달방식(form) 등 인증 과정 및 인증 후 처리에 대한 설정을 구성하는 메서드
     * 이 메서드는 사용자 정의 인증 필터를 생성한다. 이 필터는 로그인 요청을 처리하고, 인증 성공/실패 핸들러를 설정한다.
     *
     * @return CustomAuthenticationFilter
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(
            AuthenticationManager authenticationManager,
            CustomAuthSuccessHandler customAuthSuccessHandler,
            CustomAuthFailureHandler customAuthFailureHandler
    ) {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        // "/user/login" 엔드포인트로 들어오는 요청을 CustomAuthenticationFilter에서 처리하도록 지정한다.
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler);    // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthFailureHandler);    // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    /**
     * 2. authenticate 의 인증 메서드를 제공하는 매니져로'Provider'의 인터페이스를 의미
     * 이 메서드는 인증 매니저를 생성. 인증 매니저는 인증 과정을 처리하는 역할
     * 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     */
    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
        return new ProviderManager(Collections.singletonList(customAuthenticationProvider));
    }

    /**
     * 3. '인증' 제공자로 사용자의 이름과 비밀번호가 요구된다.
     * 이 메서드는 사용자 정의 인증 제공자를 생성한다. 인증 제공자는 사용자 이름과 비밀번호를 사용하여 인증을 수행한다.
     * 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(MemberDetailsService memberDetailsService) {
        return new CustomAuthenticationProvider(null);
    }

    /**
     * 4. Spring Security 기반의
     * 사용자의 정보가 맞을 경우 수행. 결과값을 리턴해주는 Handler
     * customLoginSuccessHandler: 이 메서드는 인증 성공 핸들러를 생성. 인증 성공 핸들러는 인증 성공 시 수행할 작업을 정의
     */
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    /**
     * 5. Spring Security 기반의 사용자의 정보가 맞지 않을 경우 수행이 되며 결과값을 리턴해주는 Handler
     * customLoginFailureHandler: 이 메서드는 인증 실패 핸들러를 생성한다. 인증 실패 핸들러는 인증 실패시 수행할 작업을 정의한다.
     */
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    /**
     * "JWT 토큰을 통하여서 사용자를 인증한다." -> 이 메서드는 JWT 인증 필터를 생성한다.
     * JWT 인증 필터는 요청 헤더의 JWT 토큰을 검증하고,
     * 토큰이 유효하면 토큰에서 사용자의 정보와 권한을 추출하여 SecurityContext에 저장한다.
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(CustomMemberDetailsService memberDetailsService) {
        return new JwtAuthorizationFilter();
    }

    /**
     * Supplier<Authentication>와
     * RequestAuthorizationContext를 인자로 받아서
     * "USER" 역할을 가진 사용자인지 확인 = 로그인했는지 확인
     * <p>
     * 만약 사용자가 "USER" 역할을 가지고 있다면, AuthorizationDecision 객체는 true를 반환하고,
     * 그렇지 않다면 false를 반환
     */
    private AuthorizationDecision isAdmin(
            Supplier<Authentication> authenticationSupplier, //현재 사용자의 인증 정보를 가져옴
            RequestAuthorizationContext requestAuthorizationContext //현재 요청에 대한 인가(context) 정보를 제공
    ) {
        return new AuthorizationDecision(
                authenticationSupplier.get()
                        .getAuthorities()
                        .contains(new SimpleGrantedAuthority("USER"))
        );
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
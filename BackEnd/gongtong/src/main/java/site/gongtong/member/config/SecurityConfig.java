package site.gongtong.member.config;


// 거의 페이지 이동 관련인데 -> 이건 프론트에서 처리....


//import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
////@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/view/**", "/member/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/member/login")
                )
                .rememberMe(Customizer.withDefaults());
        return http.build();

//                .authorizeHttpRequests()
//                    .requestMatchers("/").authenticated() //인증
//    //                .requestMatchers("").hasAnyAuthority(Use) //인가 (마이페이지?)
//                    .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                    .usernameParameter("id")
//                    .passwordParameter("password")
//                    .loginPage("//")
//                    .defaultSuccessUrl("/")
//                    .failureUrl("")
//                .and()
//                .logout()
//                    .logoutUrl("/")
//                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
//                //oauth로그인
//                .and()
//                .oauth2Login()
//                    .loginPage("/")
//                    .defaultSuccessUrl("/")
//                    .userInfoEndpoint()
//                        .userService(principalOauth2UserService)
//        ;
//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
//                .accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
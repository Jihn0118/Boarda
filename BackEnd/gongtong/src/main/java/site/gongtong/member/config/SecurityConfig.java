//package site.gongtong.member.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//

// 거의 페이지 이동 관련인데 -> 이건 프론트에서 처리....


//@Configuration
//@EnableWebSecurity
////@EnableMethodSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    @Bean
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/security-login/info").authenticated()
//                    .antMatchers("/security-login/admin/**").hasAuthority(UserRole.ADMIN.name())
//                    .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                    .usernameParameter("loginId")
//                    .passwordParameter("password")
//                    .loginPage("/security-login/login")
//                    .defaultSuccessUrl("/security-login")
//                    .failureUrl("/security-login/login")
//                .and()
//                .logout()
//                    .logoutUrl("/security-login/logout")
//                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");
//    }
//
//}
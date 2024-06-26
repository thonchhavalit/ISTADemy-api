//package co.istad.istademy.api.SocialRegister;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//public class GitGoogle extends WebSecurityConfigurerAdapter {
//
//    @RequestMapping("/student")
//    public Map<String, Object> student(@AuthenticationPrincipal OAuth2User principal){
//        return Collections.singletonMap("name", principal.getAttribute("name"));
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests(a->a
//                        .antMatchers("/", "/error", "/webjars/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(e->e
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
//                .csrf(c->c
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
//                .logout(l->l
//                        .logoutSuccessUrl("/").permitAll()
//                )
//                .oauth2Login();
//    }
//
//}

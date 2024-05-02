package co.istad.istademy.security;

import co.istad.istademy.util.KeyUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final KeyUtil keyUtil;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean(name = "jwtRefreshTokenAuthProvider")
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtRefreshTokenDecoder());
        provider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
        return provider;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers("/api/v1/files/**").permitAll();
            auth.requestMatchers("/api/v1/certificates/**").permitAll();
            auth.requestMatchers("/api/v1/comments/**").permitAll();
            auth.requestMatchers("/api/v1/courses/**").permitAll();
            auth.requestMatchers("/api/v1/sections/**").permitAll();
            auth.requestMatchers("/api/v1/quizzes/**").permitAll();
            auth.requestMatchers("/api/v1/feedbacks/**").permitAll();
            auth.requestMatchers("/api/v1/commentLike/**").permitAll();
            auth.requestMatchers("/api/v1/contents/**").permitAll();
            auth.requestMatchers("/api/v1/courseUser/**").permitAll();
            auth.requestMatchers("/api/v1/examples/**").permitAll();
            auth.requestMatchers("/api/v1/exercises/**").permitAll();
            auth.requestMatchers("/api/v1/exerciseAnswer/**").permitAll();
            auth.requestMatchers("/api/v1/image/**").permitAll();
            auth.requestMatchers("/api/v1/lessons/**").permitAll();
            auth.requestMatchers("/api/v1/level/**").permitAll();
            auth.requestMatchers("/api/v1/quizOption/**").permitAll();
            auth.requestMatchers("/api/v1/quizUserAnswer/**").permitAll();
            auth.requestMatchers("/api/v1/user/**").permitAll();
            auth.requestMatchers("/api/v1/userProgress/**").permitAll();
            auth.requestMatchers("/files/**").permitAll();
            auth.requestMatchers("/api/v1/contentProgresses/**").permitAll();
            auth.requestMatchers("/api/v1/quizProgresses/**").permitAll();
//            auth.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("SCOPE_user:read");
//            auth.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAuthority("SCOPE_user:write");
//            auth.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("SCOPE_user:delete");
//            auth.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAuthority("SCOPE_user:update");
            auth.anyRequest().authenticated();
//        http.authorizeHttpRequests(auth->
//        {
//            auth.anyRequest().permitAll();
        });
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(
                        jwtAuthenticationConverter()
                )
        ));

        http.sessionManagement(session
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(ex
                -> ex.authenticationEntryPoint(customAuthenticationEntryPoint));
        http.cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @Primary
    public JwtDecoder jwtAccessTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtil.getAccessTokenPublicKey()).build();
    }

    @Bean(name = "jwtRefreshTokenDecoder")
    public JwtDecoder jwtRefreshTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtil.getRefreshTokenPublicKey()).build();
    }

    @Bean
    @Primary
    public JwtEncoder jwtAccessTokenEncoder() {

        JWK jwk = new RSAKey.Builder(keyUtil.getAccessTokenPublicKey())
                .privateKey(keyUtil.getAccessTokenPrivateKey())
                .build();

        JWKSet jwkSet = new JWKSet(jwk);
        return new NimbusJwtEncoder((jwkSelector, context)
                -> jwkSelector.select(jwkSet));
    }

    @Bean(name = "jwtRefreshTokenEncoder")
    public JwtEncoder jwtRefreshTokenEncoder() {

        JWK jwk = new RSAKey.Builder(keyUtil.getRefreshTokenPublicKey())
                .privateKey(keyUtil.getRefreshTokenPrivateKey())
                .build();

        JWKSet jwkSet = new JWKSet(jwk);


        return new NimbusJwtEncoder((jwkSelector, context)
                -> jwkSelector.select(jwkSet));
    }
}

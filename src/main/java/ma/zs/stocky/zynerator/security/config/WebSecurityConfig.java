package ma.zs.stocky.zynerator.security.config;

import ma.zs.stocky.zynerator.security.common.AuthoritiesConstants;
import ma.zs.stocky.zynerator.security.jwt.AuthEntryPointJwt;
import ma.zs.stocky.zynerator.security.jwt.AuthTokenFilter;
import ma.zs.stocky.zynerator.security.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityContext((securityContext) -> securityContext.requireExplicitSave(false));
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/actuator/health").permitAll()
                                .requestMatchers("/actuator/info").permitAll()
                                .requestMatchers("/api/open/**").permitAll()
                                .requestMatchers("/api/user/**").permitAll()
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permit access to Swagger UI and API docs
                                .requestMatchers("/api/admin/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/etudiant/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/encadrantinterne/login").permitAll()
                                .requestMatchers("/api/admin/**").hasAnyAuthority(AuthoritiesConstants.ADMIN)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/etudiant/**").hasAnyAuthority(AuthoritiesConstants.ETUDIANT)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                                .requestMatchers("/api/encadrantinterne/**").hasAnyAuthority(AuthoritiesConstants.ENCADRANTINTERNE)
                               .anyRequest().authenticated()

                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

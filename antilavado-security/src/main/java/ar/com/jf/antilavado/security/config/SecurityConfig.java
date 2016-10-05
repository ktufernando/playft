package ar.com.jf.antilavado.security.config;

import ar.com.jf.antilavado.security.checker.CustomUserDetailChecker;
import ar.com.jf.antilavado.security.handler.CustomAuthenticationSuccessHandler;
import ar.com.jf.antilavado.security.util.HeaderUtil;
import ar.com.jf.antilavado.security.filter.HeaderAuthenticationFilter;
import ar.com.jf.antilavado.security.handler.CustomAccessDeniedHandler;
import ar.com.jf.antilavado.security.handler.CustomAuthenticationEntryPoint;
import ar.com.jf.antilavado.service.license.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.servlet.Filter;

/**
 * SecurityConfig.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 25/03/2015.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HeaderUtil headerUtil;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private LicenseService licenseService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setPreAuthenticationChecks(new CustomUserDetailChecker(licenseService));
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler(headerUtil);

        http.
                addFilterBefore(authenticationFilter(), LogoutFilter.class).

                csrf().disable().

                formLogin().successHandler(successHandler).
                loginProcessingUrl("/login").

                and().

                logout().
                logoutSuccessUrl("/logout").

                and().

                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).

                and().

                exceptionHandling().
                accessDeniedHandler(new CustomAccessDeniedHandler(messageSource)).
                authenticationEntryPoint(new CustomAuthenticationEntryPoint(messageSource)).

                and().

                authorizeRequests().
                antMatchers(HttpMethod.POST, "/login").permitAll().
                antMatchers(HttpMethod.POST, "/logout").authenticated().
                antMatchers(HttpMethod.GET, "/anonymous/**").permitAll().
                antMatchers(HttpMethod.POST, "/anonymous/**").permitAll().
                antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN").
                antMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN").
                antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN").
                anyRequest().authenticated();


    }

    private Filter authenticationFilter() {
        HeaderAuthenticationFilter headerAuthenticationFilter = new HeaderAuthenticationFilter();
        headerAuthenticationFilter.userDetailsService(userDetailsService);
        headerAuthenticationFilter.headerUtil(headerUtil);
        return headerAuthenticationFilter;
    }

}



package ru.ineb.pub.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by Dmitry.Erohin dim777@ya.ru on 07.03.2017.
 * Copyright (C) 2017 - present by <a href="https://www.ineb.ru/">Ineb Inc</a>.
 * Please see distribution for license.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/unauth.html", "/").permitAll()
                .anyRequest().hasAuthority("TRADER")
                .and()
                .csrf().disable();
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.authorizeRequests().antMatchers("/**").hasRole("TRADER")
                .anyRequest().authenticated();
    }
}

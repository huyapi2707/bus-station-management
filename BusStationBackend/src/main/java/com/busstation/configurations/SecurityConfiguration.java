package com.busstation.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.busstation.controllers",
        "com.busstation.repositories",
        "com.busstation.services",
        "com.busstation.configurations"
})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OncePerRequestFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/", true)
                .failureUrl("/login?error")
                .and()
                .logout().logoutSuccessUrl("/login");


        http.authorizeRequests()
                .antMatchers("/payment-result/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/payment-method/**").permitAll()
                .antMatchers("/api/v1/transportation_company/**").permitAll()
                .antMatchers("/api/v1/route/**").permitAll()
                .antMatchers("/api/v1/trip/**").permitAll()
                .antMatchers("/api/v1/ticket/cart/**").permitAll()
                .antMatchers("/api/v1/stations/**").permitAll()
                .antMatchers("/api/v1/cars/**").permitAll()
                .anyRequest().authenticated();




    }




    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

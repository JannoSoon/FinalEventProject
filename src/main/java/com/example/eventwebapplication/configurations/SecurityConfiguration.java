package com.example.eventwebapplication.configurations;

import com.example.eventwebapplication.services.implemetation.CustomUserDetailService;
import com.example.eventwebapplication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String role = "ROLE_";
        String admin = Constants.Security.AUTHORITY_ADMIN.replace(role, "");
        String user = Constants.Security.AUTHORITY_USER.replace(role, "");
        String organizer = Constants.Security.AUTHORITY_ORGANIZER.replace(role, "");


        http.authorizeRequests()
                .antMatchers("/", "/signup")
                .permitAll()
                .antMatchers("/user/**")
                .hasRole(admin)
                .antMatchers("/event/**")
                .hasAnyRole(user, organizer, admin)
                .antMatchers("/organizer/**")
                .hasAnyRole(organizer, admin)
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout().permitAll(false).logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }
}

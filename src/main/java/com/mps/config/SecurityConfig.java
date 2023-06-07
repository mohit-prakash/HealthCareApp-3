package com.mps.config;

import com.mps.constants.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Deprecated
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/pat/add","/pat/save").permitAll()
                .antMatchers("/pat/all").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/doc/**").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/app/add","/app/save").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/app/search").hasAnyAuthority(UserRoles.PATIENT.name(),UserRoles.ADMIN.name())
                .antMatchers("/user/login","/login").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/pat/add")
                .failureUrl("/user/login?error=true")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/user/login?logout=true");
    }
}

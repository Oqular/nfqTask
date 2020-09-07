package com.example.nfqtask.Config

import com.example.nfqtask.Service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var customUserDetailsService: CustomUserDetailsService

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/", "/add", "/css/**", "/bookAppointment",
                "/departmentScreen", "/checkReservation", "/showTime").permitAll().anyRequest().authenticated()
                .and().formLogin()  //login configuration
                .loginPage("/login").permitAll()
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .and().logout()
                .logoutSuccessUrl("/")
                .and().exceptionHandling()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        val passwordEncoder = BCryptPasswordEncoder()
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder)
    }
}
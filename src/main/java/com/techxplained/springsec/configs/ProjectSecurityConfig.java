package com.techxplained.springsec.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myaccount").authenticated()
                .requestMatchers("/notifications","/contactUs", "/error").permitAll());
//        http.formLogin((flc) -> flc.disable()); to disable form style login's
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults()); // like postman
        return http.build();
    }

    /*
    when we want to use do in memory storage of user data
    @Bean
    public UserDetailsService userDetailsService(){
       UserDetails user =  User.withUsername("user").password("{noop}12345").authorities("read").build();
       UserDetails admin =  User.withUsername("admin").password("{bcrypt}$2a$12$336Jz.hXE09k2yPEKkcGAe8rrXzM.O7fOjSaDsHsrCGk/n9vf5s6e").authorities("admin").build();
       //techxplained@54321
       //return object of one of the Implementations of userdetailsService -> as we are storing in memory
       //therefore we use InMemoryUserDetailsManager implementation.
       return new InMemoryUserDetailsManager(user, admin);
    }
    /*
    JdbcUserDetailsManager for storing user details into the database
    */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    /*
    encode passwords which have preset as bcrypt or any other encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*
    check if password thar is being used has been compromised in previous breaches
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}

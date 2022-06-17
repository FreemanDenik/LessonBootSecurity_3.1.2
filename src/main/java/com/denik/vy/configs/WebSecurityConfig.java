package com.denik.vy.configs;

import com.denik.vy.models.Role;
import com.denik.vy.models.User;
import com.denik.vy.repositories.RoleRepository;
import com.denik.vy.repositories.UserRepository;
import com.denik.vy.services.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Set;

@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final SuccessLoginHandler successLoginHandler;
    final UserDetailService userDetailService;
    WebSecurityConfig(SuccessLoginHandler successLoginHandler, UserDetailService userDetailService){
        this.successLoginHandler = successLoginHandler;
        this.userDetailService = userDetailService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successLoginHandler).permitAll()
                .and()
                .logout().permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
    @Bean
    public String InitAdmin(UserRepository repository, RoleRepository roleRepository){

        Role roleAdmin = new Role("ROLE_ADMIN");

        Role roleUser = new Role("ROLE_USER");

        User userAdmin = new User("admin", "admin@em.ru", passwordEncoder().encode("12345"), Set.of(roleUser,roleAdmin));
        User userUser = new User("user", "user@em.ru", passwordEncoder().encode("12345"), Set.of(roleUser));


        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        repository.save(userAdmin);
        repository.save(userUser);

        return null;
    }
}

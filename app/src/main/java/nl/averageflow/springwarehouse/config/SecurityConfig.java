package nl.averageflow.springwarehouse.config;

import nl.averageflow.springwarehouse.enums.UserRole;
import nl.averageflow.springwarehouse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(UserRole.ADMIN)
                .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(UserRole.ADMIN)
                .antMatchers(HttpMethod.PATCH, "/api/**").hasAuthority(UserRole.ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(UserRole.ADMIN)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService)
                .passwordEncoder(passwordEncoder());
    }
}

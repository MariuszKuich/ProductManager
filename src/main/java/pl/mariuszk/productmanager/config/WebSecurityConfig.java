package pl.mariuszk.productmanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final EncoderConfig encoderConfig;

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                    .antMatchers("/api/**", "/swagger-ui", "/swagger-ui.html")
                    .hasRole("DEV")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("a").password(encoderConfig.passwordEncoder().encode("a")).roles("DEV")
                .and()
                .withUser("andjur001").password(encoderConfig.passwordEncoder().encode("3ypDTV")).roles("USER")
                .and()
                .withUser("kampol002").password(encoderConfig.passwordEncoder().encode("ML2KxW")).roles("USER");
    }
}
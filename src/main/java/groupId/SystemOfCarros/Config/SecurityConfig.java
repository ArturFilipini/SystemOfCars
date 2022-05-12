package groupId.SystemOfCarros.Config;

import groupId.SystemOfCarros.filter.CustomAuthenticationFilter;
import groupId.SystemOfCarros.services.CarroUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CarroUserDetailsService carroUserDetailsService;

    public SecurityConfig(CarroUserDetailsService carroUserDetailsService) {
        this.carroUserDetailsService = carroUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
       log.info("Password encoded {}", passwordEncoder.encode("ferrari"));
       auth.userDetailsService(carroUserDetailsService)
               .passwordEncoder(passwordEncoder);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}

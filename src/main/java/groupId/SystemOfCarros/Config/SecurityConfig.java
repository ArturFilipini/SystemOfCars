package groupId.SystemOfCarros.Config;

import groupId.SystemOfCarros.filter.JWTAuthenticationFilter;
import groupId.SystemOfCarros.filter.JWTAuthorizationFilter;
import groupId.SystemOfCarros.services.CarroUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CarroUserDetailsService carroUserDetailsService;


    public SecurityConfig(CarroUserDetailsService carroUserDetailsService) {
        this.carroUserDetailsService = carroUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/carro/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), carroUserDetailsService));



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
       log.info("Password encoded {}", passwordEncoder.encode("ferrari"));
       auth.userDetailsService(carroUserDetailsService)
               .passwordEncoder(passwordEncoder);
    }

}

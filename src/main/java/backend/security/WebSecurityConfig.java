package backend.security;

import backend.service.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static backend.security.SecurityConstants.SIGN_IN_URL;
import static backend.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AppUserServiceImpl appUserService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers( HttpMethod.POST, SIGN_UP_URL )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .addFilter( getAuthenticationFilter() )
                .addFilter( new AuthorizationFilter( authenticationManager() ) )
                .sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS );
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.authenticationProvider( daoAuthenticationProvider() );
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter( authenticationManager() );
        filter.setFilterProcessesUrl( SIGN_IN_URL );
        return filter;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder( this.passwordEncoder );
        provider.setUserDetailsService( this.appUserService );
        return provider;
    }
}

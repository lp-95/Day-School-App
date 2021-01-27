package backend.security;

import backend.dto.SignInRequest;
import backend.exception.BadRequestException;
import backend.model.AppUser;
import backend.service.AppUserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static backend.security.SecurityConstants.*;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request,
                                                 HttpServletResponse response ) throws AuthenticationException {
        try {
            SignInRequest signIn = new ObjectMapper().readValue( request.getInputStream(), SignInRequest.class );
            return this.authenticationManager
                    .authenticate( new UsernamePasswordAuthenticationToken(
                            signIn.getEmail(), signIn.getPassword(), new ArrayList<>() ));
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain chain,
                                             Authentication authResult ) throws IOException, ServletException {
        String email = ( ( User ) authResult.getPrincipal() ).getUsername();
        String token = Jwts.builder()
                .setSubject( email )
                .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME  ) )
                .signWith( SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret() )
                .compact();
        AppUserServiceImpl appUserService =
                ( AppUserServiceImpl ) ApplicationContextImpl.getBean( "appUserService" );
        AppUser appUser;
        try {
            appUser = ( AppUser ) appUserService.loadUserByUsername( email );
            response.addHeader( TOKEN_HEADER, TOKEN_PREFIX + token );
            response.addHeader( "app-user", appUser.getId().toString() );
        } catch ( UsernameNotFoundException e ) {
            e.printStackTrace();
        }
    }
}

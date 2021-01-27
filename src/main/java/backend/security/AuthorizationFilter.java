package backend.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static backend.security.SecurityConstants.TOKEN_HEADER;
import static backend.security.SecurityConstants.TOKEN_PREFIX;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter( AuthenticationManager authenticationManager ) {
        super( authenticationManager );
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain chain ) throws IOException, ServletException {
        String header = request.getHeader( TOKEN_HEADER );
        if ( header == null || !header.startsWith( TOKEN_PREFIX ) ) {
            chain.doFilter( request, response );
        }
        UsernamePasswordAuthenticationToken token = getAuthentication( request );
        SecurityContextHolder.getContext().setAuthentication( token );
        chain.doFilter( request, response );
    }

    private UsernamePasswordAuthenticationToken getAuthentication( HttpServletRequest request ) {
        String header = request.getHeader( TOKEN_HEADER );
        if ( header != null ) {
            header = header.replaceAll( TOKEN_PREFIX, "" );
            String token = Jwts.parser()
                    .setSigningKey( SecurityConstants.getTokenSecret() )
                    .parseClaimsJws( header )
                    .getBody()
                    .getSubject();
            if ( token != null ) {
                return new UsernamePasswordAuthenticationToken( token, null, new ArrayList<>() );
            }
        }
        return null;
    }
}
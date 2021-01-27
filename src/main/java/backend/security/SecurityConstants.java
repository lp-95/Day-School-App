package backend.security;

public class SecurityConstants {
    public static final String TOKEN_HEADER = "Authentication";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String SIGN_IN_URL = "/app-user/sign-in";
    public static final String SIGN_UP_URL = "app-user";
    public static final Integer EXPIRATION_TIME = 900_000;

    public static String getTokenSecret() {
        ApplicationProperties applicationProperties =
                ( ApplicationProperties ) ApplicationContextImpl.getBean( "ApplicationProperties" );
        return applicationProperties.getTokenSecret();
    }
}

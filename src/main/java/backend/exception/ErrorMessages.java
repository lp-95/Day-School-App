package backend.exception;

public enum ErrorMessages {
    ID_NOT_FOUND( "The item with given id is not found" ),
    USERNAME_NOT_FOUND( "The username \"%s\" is not found" ),
    EMAIL_NOT_VALID( "The email \"%s\" is not valid" ),
    EMAIL_ALREADY_EXIST( "The email \"%s\" is already exist" ),
    PASSWORD_NOT_CONFIRMED( "Password ain't matches" ),
    TOKEN_NOT_FOUND( "Token is not fund" ),
    EMAIL_SEND_FAILED( "Email send failed" ),
    INCORRECT_DATE("date must be added in format \"from-to\"");
    String errorMessage;

    ErrorMessages( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

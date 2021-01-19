package backend.exception;

public enum ErrorMessages {
    ID_NOT_FOUND( "The item with given id is not found" ),
    INCORRECT_DATE("date must be added in format \"from-to\"");
    String errorMessage;

    ErrorMessages( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

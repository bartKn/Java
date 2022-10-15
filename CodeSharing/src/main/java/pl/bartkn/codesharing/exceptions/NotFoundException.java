package pl.bartkn.codesharing.exceptions;

public class NotFoundException extends RuntimeException {

    private String error;

    public NotFoundException() {
    }

    public NotFoundException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

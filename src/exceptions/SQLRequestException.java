package exceptions;

public class SQLRequestException extends RuntimeException{

    public SQLRequestException(Throwable cause) {
        super(cause);
    }
}

package movie.advice;

import static java.lang.String.format;

public class FileParseException extends RuntimeException {

    private static final String MSG = "Could not parse the file.";

    public FileParseException() {

        super(format(MSG));
    }

    public FileParseException(String message, Throwable cause) {

        super(message,cause);
    }
}

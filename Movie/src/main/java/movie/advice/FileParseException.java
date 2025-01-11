package movie.advice;

import org.json.simple.parser.ParseException;

import static java.lang.String.format;

/**
 * Custom exception to handle file parsing errors.
 */
public class FileParseException extends RuntimeException {

    private static final String MSG = "Could not parse the file.";


    /**
     * Constructs a new {@code FileParseException} with the default message.
     */
    public FileParseException() {
        super(format(MSG));
    }

    /**
     * Constructs a new {@code FileParseException} with a custom message and the cause of the exception.
     *
     * @param message A detailed message about the parsing error.
     * @param cause   The underlying cause of the exception, e.g., {@link ParseException}.
     */
    public FileParseException(String message, Throwable cause) {
        super(message, cause);
    }

}

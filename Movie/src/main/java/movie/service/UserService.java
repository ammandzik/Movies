package movie.service;

import java.util.Scanner;

/**
 * Service responsible for interacting with the user and collecting input data.
 */
public class UserService {

    private UserService() {}

    /**
     * The Scanner resource is automatically closed after the method finishes executing.
     *
     * @return The value entered by the user.
     */
    public static Scanner askForUserInput() {
        return new Scanner(System.in);
    }

}
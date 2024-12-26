package movie.service;

import java.util.Scanner;

public class UserService {

    private UserService(){

    }

    public static Scanner askForUserInput() {

        return new Scanner(System.in);

    }

}

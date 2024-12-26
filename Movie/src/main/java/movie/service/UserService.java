package movie.service;

import java.util.Scanner;

class UserService {

    private UserService(){

    }

    public static Scanner askForUserInput() {

        return new Scanner(System.in);

    }

}

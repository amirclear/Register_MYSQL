package aut.ap;

import User.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.print("[L]ogin, [S]ign up: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("l") || choice.equals("login")) {
            userService.login();
        } else if (choice.equals("s") || choice.equals("sign up") || choice.equals("signup")) {
            userService.signUp();
        } else {
            System.out.println("Invalid input");
        }
    }
}

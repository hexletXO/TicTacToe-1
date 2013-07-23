package com.hexlet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Other.printLogo();

        input.nextLine();

        Other.printHelp();

        Other.startMenu(input);
    }
}

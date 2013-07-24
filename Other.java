package com.hexlet;

import java.util.Scanner;
import java.util.Random;

public class Other {

    private static final String APOLOGIZE = "Sorry, but this mode still in development! Coming soon...";

    public static void printLogo() {
        System.out.print("************************************************\n" +
                         "| Welcome to Tic Tac Toe game! Press Enter key |\n" +
                         "| for print help information...                |\n" +
                         "************************************************");
    }

    public static void printHelp() {
        System.out.println("Commands:\n" +
                "\t1 | Human vs. Computer (local, stupid mode)\n" +
                "\t2 | Human vs. Computer (local, smart mode)\n" +
                "\t3 | Human vs. Human (local)\n" +
                "\t4 | Human vs. Human (server mode)\n" +
                "\t5 | Human vs. Computer (server mode, smart mode)\n" +
                "\t9 | Print command codes\n" +
                "\t0 | Exit -->");
    }

    public static void startMenu(Scanner inputFlow) {

        Field gameField = new Field();


        int command = -1;

        while (true) {
            System.out.print("command >: ");        // во время запуска немного сбивала с толку вот такая комбинация >:
            command = inputFlow.nextInt();          // хотя это мелочи

            if (command == 0) break;

            switch (command) {
                case 1:
                    System.out.println("Human vs. Computer (local, stupid mode)");
                    humanVsComputerStupidMode(inputFlow, gameField);
                    break;
                case 2:
                    System.out.println("Human vs. Computer (local, smart mode)");
                    System.out.println(APOLOGIZE);   // после этого сообщения неплохо бы написать что то типа "повторите команду", а то непонятно
                    break;
                case 3:
                    System.out.println("Human vs. Human (local)");
                    System.out.println(APOLOGIZE);
                    break;
                case 4:
                    System.out.println("Human vs. Human (server mode)");
                    System.out.println(APOLOGIZE);
                    break;
                case 5:
                    System.out.println("Human vs. Computer (server mode, smart mode)");
                    System.out.println(APOLOGIZE);
                    break;
                case 9:
                    printHelp();
                    break;
                default:
                    System.out.println("Sorry, unknown command code... Try again!");
                    break;
            }
        }
        System.out.println("See ya!");
    }

    public static void humanVsComputerStupidMode(Scanner input, Field field) {
        int x, y;
        Random generator = new Random();
        while (!field.gameOver) {                       // это плохо, получение таких переменных из других объектов должно осуществляться с помощью геттеров, сама переменная должна быть private
            System.out.print("Enter x >: ");            // плюс тут наверное баг, при втором прогоне после выбора режима ничего не происходит, т.к. нужно сбрасывать field.gameOver в 0, ну или создавать field заново
            x = input.nextInt();
            System.out.print("Enter y >: ");            // наверное вместо х и у лучше писать ряд и строка, так понятнее
            y = input.nextInt();                        // тут как то все странно, при втором прогоне не работает ничего
            while (!field.makeStep(x, y, field.FIRST_PLAYER)) {     // видимо это связано с тем что переменные класса Field не сбрасываются после окончания первой игры
                System.out.println("Incorrect input, try again!");
                System.out.print("Enter x >: ");
                x = input.nextInt();
                System.out.print("Enter y >: ");
                y = input.nextInt();
            }
            if (field.checkEndTheGame(field.FIRST_PLAYER)) break;

            x = field.FIELD_SIZE - generator.nextInt(field.FIELD_SIZE);
            y = field.FIELD_SIZE - generator.nextInt(field.FIELD_SIZE);
            while (!field.makeStep(x, y, field.SECOND_PLAYER)) {
                x = field.FIELD_SIZE - generator.nextInt(field.FIELD_SIZE);
                y = field.FIELD_SIZE - generator.nextInt(field.FIELD_SIZE);
            }
            field.printField();
            if (field.checkEndTheGame(field.SECOND_PLAYER));                    // чета я тут не понял, это зачем
        }
    }
}

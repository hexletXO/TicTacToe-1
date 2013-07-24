package com.hexlet;

public class Field {

    private static final char DEFAULT_CELL_VALUE = ' ';
    public final char FIRST_PLAYER = 'X';
    public final char SECOND_PLAYER = 'O';

    public boolean gameOver = false;
    public boolean firstPlayerWin = false;
    public boolean draw = false;

    public final int FIELD_SIZE = 3;

    private char[][] gameField = new char[FIELD_SIZE][FIELD_SIZE];

    public Field() {
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                gameField[x][y] = DEFAULT_CELL_VALUE;
            }
        }
    }

    public void printField() {
        System.out.println("Actual field is:");
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                System.out.print("[" + gameField[x][y] + "]");
            }
            System.out.print("\n");
        }
    }

    public boolean permissibleStep(int x, int y) {
        if (gameField[y-1][x-1] == DEFAULT_CELL_VALUE) return true;
        return false;
    }

    public boolean makeStep(int x, int y, char playerSymbol) {
        if (x > 0 && x <= FIELD_SIZE && y > 0 && y <= FIELD_SIZE) {
            if (permissibleStep(x, y)) {
                gameField[y-1][x-1] = playerSymbol;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkWinner(char playerSymbol) {

            if (gameField[0][0] == playerSymbol && gameField[0][1] == playerSymbol && gameField[0][2] == playerSymbol ||
                gameField[1][0] == playerSymbol && gameField[1][1] == playerSymbol && gameField[1][2] == playerSymbol ||
                gameField[2][0] == playerSymbol && gameField[2][1] == playerSymbol && gameField[2][2] == playerSymbol ||
                gameField[0][0] == playerSymbol && gameField[1][0] == playerSymbol && gameField[2][0] == playerSymbol ||
                gameField[0][1] == playerSymbol && gameField[1][1] == playerSymbol && gameField[2][1] == playerSymbol ||
                gameField[0][2] == playerSymbol && gameField[1][2] == playerSymbol && gameField[2][2] == playerSymbol ||
                gameField[0][0] == playerSymbol && gameField[1][1] == playerSymbol && gameField[2][2] == playerSymbol ||
                gameField[2][0] == playerSymbol && gameField[1][1] == playerSymbol && gameField[0][2] == playerSymbol) {
            System.out.println("GameOver!");
            gameOver = true;
            if (playerSymbol == FIRST_PLAYER) firstPlayerWin = true;
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                if (gameField[x][y] == ' ') {
                    return false;
                }
            }
        }
        gameOver = true;
        draw = true;
        return true;
    }

    public boolean checkEndTheGame(char playerSymbol) {
        checkWinner(playerSymbol);
        checkDraw();

        if (gameOver) {
            if (firstPlayerWin) {
                System.out.println("Human destroy the computer! You are win!!!11one");
                return true;
            } else if (!firstPlayerWin && !draw) {
                System.out.println("You lose... Try again, cobber!");
                return true;
            } else {
                System.out.println("Game over... Draw!");
                return true;
            }
        }

        return false;
    }

}

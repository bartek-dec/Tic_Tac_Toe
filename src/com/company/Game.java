package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private final int size = 3;

    private Result xWins = Result.X_WINS;
    private Result oWins = Result.O_WINS;
    private Result draw = Result.DRAW;

    private String[][] board;
    private int counter;
    private String coordinate;
    private String result;

    public Game() {
        this.board = new String[size][size];
        this.counter = 1;
        prepareBoard();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public String getResult() {
        return result;
    }

    public void displayBoard() {
        System.out.println("---------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");

            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }

            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public void play() {
        checkBoard(coordinate);
        counter++;
        setResult();
    }

    public boolean processCoordinates(String s) {
        int x = 0;
        int y = 0;
        try {
            x = Integer.parseInt(Character.toString(s.charAt(0)));
            y = Integer.parseInt(Character.toString(s.charAt(2)));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (((x <= 0) || (x > 3)) || ((y <= 0) || (y > 3))) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else {
            String coordinate = Integer.toString(x) + y;
            if (!checkBoard(coordinate)) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                this.coordinate = coordinate;
                return true;
            }
        }
    }

    private boolean checkBoard(String s) {
        String mark = counter % 2 == 1 ? "X" : "O";

        switch (s) {
            case "13":
                if (board[0][0].equals(" ")) {
                    board[0][0] = mark;
                    return true;
                } else {
                    return false;
                }
            case "23":
                if (board[0][1].equals(" ")) {
                    board[0][1] = mark;
                    return true;
                } else {
                    return false;
                }
            case "33":
                if (board[0][2].equals(" ")) {
                    board[0][2] = mark;
                    return true;
                } else {
                    return false;
                }
            case "12":
                if (board[1][0].equals(" ")) {
                    board[1][0] = mark;
                    return true;
                } else {
                    return false;
                }
            case "22":
                if (board[1][1].equals(" ")) {
                    board[1][1] = mark;
                    return true;
                } else {
                    return false;
                }
            case "32":
                if (board[1][2].equals(" ")) {
                    board[1][2] = mark;
                    return true;
                } else {
                    return false;
                }
            case "11":
                if (board[2][0].equals(" ")) {
                    board[2][0] = mark;
                    return true;
                } else {
                    return false;
                }
            case "21":
                if (board[2][1].equals(" ")) {
                    board[2][1] = mark;
                    return true;
                } else {
                    return false;
                }
            case "31":
                if (board[2][2].equals(" ")) {
                    board[2][2] = mark;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    private void prepareBoard() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(board[i], " ");
        }
    }

    private void setResult() {
        if (isDiagonal() > 0) {
            if (isDiagonal() == 1) {
                this.result = xWins.getResult();
            } else if (isDiagonal() == 2) {
                this.result = xWins.getResult();
            } else if (isDiagonal() == 3) {
                this.result = oWins.getResult();
            } else if (isDiagonal() == 4) {
                this.result = oWins.getResult();
            }
        } else if (isRow()) {
            if (checkRows("X")) {
                this.result = xWins.getResult();
            } else if (checkRows("O")) {
                this.result = oWins.getResult();
            }
        } else if (isColumn()) {
            if (checkColumns("X")) {
                this.result = xWins.getResult();
            } else if (checkColumns("O")) {
                this.result = oWins.getResult();
            }
        } else if (counter == 10) {
            this.result = draw.getResult();
        } else {
            this.result = null;
        }
    }

    private int isDiagonal() {
        if (checkMainDiagonal("X")) {
            return 1;
        } else if (checkCounterDiagonal("X")) {
            return 2;
        } else if (checkMainDiagonal("O")) {
            return 3;
        } else if (checkCounterDiagonal("O")) {
            return 4;
        } else {
            return -1;
        }
    }

    private boolean checkMainDiagonal(String s) {
        boolean flag = true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == j) && (!board[i][j].equals(s))) {
                    flag = false;
                    break;
                }
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }

    private boolean checkCounterDiagonal(String s) {
        boolean flag = true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((j == size - 1 - i) && (!board[i][j].equals(s))) {
                    flag = false;
                    break;
                }
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }

    private boolean isRow() {
        if (checkRows("X")) {
            return true;
        } else if (checkRows("O")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRows(String s) {
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flag = true;
                if (!board[i][j].equals(s)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    private boolean isColumn() {
        if (checkColumns("X")) {
            return true;
        } else if (checkColumns("O")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkColumns(String s) {
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            flag = true;
            if ((!board[0][i].equals(s) || (!board[1][i].equals(s)) || (!board[2][i].equals(s)))) {
                flag = false;
            }

            if (flag) {
                break;
            }
        }
        return flag;
    }
}

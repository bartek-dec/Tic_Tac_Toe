package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        Scanner scanner = Game.getScanner();

        game.displayBoard();

        do {

            System.out.print("Enter the coordinates: ");
            String coordinate = scanner.nextLine();

            if (game.processCoordinates(coordinate)) {
                game.play();
                game.displayBoard();
            }

        } while (game.getResult() == null);

        System.out.println(game.getResult());
    }
}

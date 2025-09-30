package com.keyin;

/*
  Description: QAP 1 - Movie Theater Seat Reservation Program
  Author: Joseph Gallant
  Date: Sept. 30, 2025
 */

import java.util.Scanner;

public class SeatReservationMain {
    // ANSI escape codes for colours
    private final static String ANSI_GREEN = "\u001B[32m";
    private final static String ANSI_RED = "\u001B[31m";
    private final static String ANSI_RESET = "\u001B[0m";

    private final static int SEAT_ROWS = 12;
    private final static int SEAT_COLUMNS = 20;

    private final static char FREE_SEAT = '*';
    private final static char RESERVED_SEAT = '@';

    private final static char[][] seats = new char[SEAT_ROWS][SEAT_COLUMNS];

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        initializeSeats();

        do {
            System.out.println("\nWelcome to the Movie Theater Seat reservation system");
            System.out.println("Please, make a selection:\n");

            System.out.println("1. Display the current seating chart");
            System.out.println("2. Reserve a seat");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Quit");

            System.out.print("\nSelect an option: ");

            while (true) {
                if (scanner.hasNextInt()){
                    option = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    scanner.nextLine();
                    System.out.print("Invalid option. Please enter a number: ");
                }
            }

            switch (option) {
                case 1:
                    displaySeats();
                    enterToContinue();
                    break;
                case 2:
                    displaySeats();
                    reserveSeat();
                    enterToContinue();
                    break;
                case 3:
                    displaySeats();
                    cancelReservation();
                    enterToContinue();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Movie Theater Seat Reservation system. Have a good day!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        } while (option != 4);
    }

    private static void enterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void initializeSeats(){
        for (int row = 0; row < seats.length; row++){
            for (int column = 0; column < seats[row].length; column++){
                seats[row][column] = FREE_SEAT;
            }
        }
    }

    private static void displaySeats(){
        final String freeSeatFormatted = String.format("%s[%s]%s", ANSI_GREEN, FREE_SEAT, ANSI_RESET);
        final String reservedSeatFormatted = String.format("%s[%s]%s", ANSI_RED, RESERVED_SEAT, ANSI_RESET);

        System.out.println("\nThe current seats arrangement:");
        System.out.print("\n   ");
        for (int column = 0; column < seats[0].length; column++){
            System.out.printf("%2d  ", column + 1);
        }

        System.out.println();

        for (int row = 0; row < seats.length; row++){
            System.out.printf("%2d ", row + 1);
            for (int column = 0; column < seats[row].length; column++){
                if (seats[row][column] == FREE_SEAT) {
                    System.out.print(freeSeatFormatted);
                } else {
                    System.out.print(reservedSeatFormatted);
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.printf("\nLegend:\nFree seat     = %s\nReserved seat = %s\n", freeSeatFormatted, reservedSeatFormatted);
    }

    private static void reserveSeat() {
        int row;
        int column;

        do {
            System.out.println("\nSelect a seat you would like to reserve (-1 to quit):\n");

            System.out.print("Row: ");

            while (true) {
                if (scanner.hasNextInt()){
                    row = scanner.nextInt();
                    scanner.nextLine();

                    if (row == -1) {
                        return;
                    }
                    else if (row < 1 || row > seats.length) {
                        System.out.printf("Invalid option. Please enter a number between 1 and %d: ", seats.length);
                    } else {
                        break;
                    }
                } else {
                    scanner.nextLine();
                    System.out.print("Invalid option. Please enter a valid positive number: ");
                }
            }

            System.out.print("Column: ");

            while (true) {
                if (scanner.hasNextInt()){
                    column = scanner.nextInt();
                    scanner.nextLine();

                    if (column == -1) {
                        return;
                    }
                    else if (column < 1 || column > seats[row - 1].length) {
                        System.out.printf("Invalid option. Please enter a number between 1 and %d: ", seats[row - 1].length);
                    } else {
                        break;
                    }
                } else {
                    scanner.nextLine();
                    System.out.print("Invalid option. Please enter a valid positive number: ");
                }
            }

            // Check if the seat is available. If it's not, recommend the next available seat.
            if (seats[row - 1][column - 1] == FREE_SEAT) {
                seats[row - 1][column - 1] = RESERVED_SEAT;
                System.out.printf("\nThe seat on row %d and column %d has been reserved successfully!\n", row, column);

                enterToContinue();
                displaySeats();
                break;
            } else {
                System.out.println("\nThat seat is currently unavailable.");

                // Find the next available seat
                System.out.println(getRecommendedSeat(row, column));
            }

        } while (true);
    }

    private static String getRecommendedSeat(int row, int column) {
        // Check for next available seat on the current row (going up)
        for (int columnIter = column; columnIter < seats[row - 1].length; columnIter++) {
            if (seats[row - 1][columnIter] == FREE_SEAT) {
                return recommendedSeatMessage(row, columnIter + 1);
            }
        }

        // If none are found, check for next available seat on the current row (going down)
        for (int columnIter = column - 2; columnIter >= 0; columnIter--) {
            if (seats[row - 1][columnIter] == FREE_SEAT) {
                return recommendedSeatMessage(row, columnIter + 1);
            }
        }

        // Check all the other rows (going up)
        for (int rowIter = row; rowIter < seats.length; rowIter++) {
            for (int columnIter = 0; columnIter < seats[rowIter].length; columnIter++){
                if (seats[rowIter][columnIter] == FREE_SEAT) {
                    return recommendedSeatMessage(rowIter + 1, columnIter + 1);
                }
            }
        }

        // Check all the other rows (going down)
        for (int rowIter = seats.length - 1; rowIter >= 0; rowIter--) {
            for (int columnIter = seats[rowIter].length - 1; columnIter >= 0; columnIter--){
                if (seats[rowIter][columnIter] == FREE_SEAT) {
                    return recommendedSeatMessage(rowIter + 1, columnIter + 1);
                }
            }
        }

        return "\nThere are no free seats available.";
    }

    private static String recommendedSeatMessage(int row, int column) {
        return String.format("Recommended seat\nRow: %d\nColumn: %d\n", row, column);
    }

    private static void cancelReservation() {
        int row;
        int column;

        do {
            System.out.println("\nSelect a seat reservation you would like to cancel (-1 to quit):\n");

            System.out.print("Row: ");

            while (true) {
                if (scanner.hasNextInt()){
                    row = scanner.nextInt();
                    scanner.nextLine();

                    if (row == -1) {
                        return;
                    }
                    else if (row < 1 || row > seats.length) {
                        System.out.printf("Invalid option. Please enter a number between 1 and %d: ", seats.length);
                    } else {
                        break;
                    }
                } else {
                    scanner.nextLine();
                    System.out.print("Invalid option. Please enter a valid positive number: ");
                }
            }

            System.out.print("Column: ");

            while (true) {
                if (scanner.hasNextInt()){
                    column = scanner.nextInt();
                    scanner.nextLine();

                    if (column == -1) {
                        return;
                    }
                    else if (column < 1 || column > seats[row - 1].length) {
                        System.out.printf("Invalid option. Please enter a number between 1 and %d: ", seats[row - 1].length);
                    } else {
                        break;
                    }
                } else {
                    scanner.nextLine();
                    System.out.print("Invalid option. Please enter a valid positive number: ");
                }
            }

            if (seats[row - 1][column - 1] == RESERVED_SEAT) {
                seats[row - 1][column - 1] = FREE_SEAT;
                System.out.printf("\nThe seat reservation on row %d and column %d has been cancelled successfully!\n", row, column);

                enterToContinue();
                displaySeats();
                break;
            } else {
                System.out.println("\nThat seat is already available.");
            }
        } while (true);
    }
}
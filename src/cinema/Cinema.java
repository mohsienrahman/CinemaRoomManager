package cinema;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = sc.nextInt();

        char[][] seats = new char[rows][columns];
        inputseats(seats);


        while (true) {
            Menu();
            int menunumber = sc.nextInt();
            if (menunumber == 0) {
                break; // Exit the loop when menunumber is 0
            }
            while (menunumber != 0) {
                if (menunumber == 1) {
                    printseats(seats);
                } else if (menunumber == 2) {
                    while (true) {
                        System.out.println("\nEnter a row number:");
                        int rownum = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatnum = sc.nextInt();
                        if (rownum < 1 || rownum > rows || seatnum < 1 || seatnum > columns) {
                            System.out.println("\nWrong input!");
                        } else if (seats[rownum - 1][seatnum - 1] == 'B') {
                            System.out.println("\nThat ticket has already been purchased!");
                        } else {
                            bookingseats(seats, rownum, seatnum);
                            break; // Exit the loop when a valid seat is booked
                        }
                    }
                } else if (menunumber == 3) {
                    statistics(seats, rows, columns);
                }
                Menu(); // Display the menu again
                menunumber = sc.nextInt(); // Get the next menu choice
            }
            break;
        }


    }
    public static void printseats(char[][] seats) {

        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 0; i <= seats.length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int u = 0; u < seats[i].length; u++) {
                System.out.print(seats[i][u] + " ");
            }
            System.out.println();
        }
    }

    public static void inputseats(char[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int u = 0; u < seats[i].length; u++) {
                seats[i][u] = 'S';
            }
        }
    }

    public static void statistics(char[][] seats, int rows, int columns) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        int count = 0;
        int Total = rows * columns;
        int Currentincome = 0;
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col] == 'B' && row + 1 <=rows/2) {
                    count++;
                    Currentincome = Currentincome + 10;
                } else if (seats[row][col] == 'B' && row + 1 > rows/2) {
                    count++;
                    Currentincome = Currentincome + 8;
                }
            }
        }
        System.out.println("\nNumber of purchased tickets: " + count);

        double percentage = ((double) count/Total) * 100;
        System.out.println("Percentage: " + decimalFormat.format(percentage) +"%");

        if (Total < 60) {
            Currentincome = count * 10;
            System.out.println("Current income: $" + Currentincome);
        } else if (Total > 60) {
            System.out.println("Current income: $" + Currentincome);
        }
        Totalseats(rows, columns);
    }


    public static void tickets(int rows, int columns, int rownum) {
        int Total = rows * columns;
        int frow = rows / 2;
        int brow = (rows / 2) + 1;
        if (Total < 60) {
            System.out.print("\nTicket price: $10");
        } else if (Total > 60) {
            if (rownum <= frow) {
                System.out.println("\nTicket price: $10");
            } else if (rownum >= brow) {
                System.out.println("\nTicket price: $8");
            }
        }
        System.out.println();
    }

    public static void bookingseats(char[][] seats, int rownum, int seatnum) {
        seats[rownum - 1][seatnum - 1] = 'B';
        tickets(seats.length, seats[0].length, rownum);
    }

    public static void Menu() {
        System.out.println("\n" + "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void Totalseats(int rows, int columns) {
        int Total = rows * columns;
        int frow = (rows / 2) * columns;
        int brow = ((rows / 2) + 1) * columns;
        if (Total < 60) {
            System.out.println("Total income: $" + Total*10);
        } else if (Total > 60) {
            Total = (frow * 10) + (brow * 8);
            System.out.println("Total income: $" + Total);
        }
    }
}
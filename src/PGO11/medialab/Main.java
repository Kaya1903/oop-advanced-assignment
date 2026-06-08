package PGO11.medialab;

import PGO11.medialab.model.LoyaltyDiscountPolicy;
import PGO11.medialab.service.ReservationService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReservationService service = new ReservationService(new LoyaltyDiscountPolicy());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n================ MEDIA LAB MENU ================");
            System.out.println("1. Display students");
            System.out.println("2. Display equipment");
            System.out.println("3. Create reservation");
            System.out.println("4. Return equipment");
            System.out.println("5. Show active reservations");
            System.out.println("6. Show final report");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    service.printStudents();
                    break;
                case "2":
                    service.printEquipment();
                    break;
                case "3":
                    System.out.print("Enter student id: ");
                    String sId = scanner.nextLine();
                    System.out.print("Enter equipment id: ");
                    String eId = scanner.nextLine();
                    System.out.print("Enter number of days: ");
                    try {
                        int days = Integer.parseInt(scanner.nextLine());
                        service.createReservation(sId, eId, days);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number format for days.");
                    }
                    break;
                case "4":
                    System.out.print("Enter reservation id: ");
                    String rId = scanner.nextLine();
                    service.returnEquipment(rId);
                    break;
                case "5":
                    service.printActiveReservations();
                    break;
                case "6":
                    service.printReport();
                    break;
                case "0":
                    System.out.println("Exiting MediaLab system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
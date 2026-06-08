package PGO11.medialab.service;

import PGO11.medialab.interfaces.DiscountPolicy;
import PGO11.medialab.model.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final List<Student> students = new ArrayList<>();
    private final List<Equipment> equipments = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final DiscountPolicy discountPolicy;
    private int reservationCounter = 1;

    public ReservationService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        initSampleData();
    }

    private void initSampleData() {
        students.add(new Student("S001", "Anna Kowalska", "12c", 120));
        students.add(new Student("S002", "Marek Nowak", "12c", 40));
        students.add(new Student("S003", "Julia Zielinska", "13a", 0));

        equipments.add(new LaptopSet("E001", "Lenovo ThinkPad Lab", 80, 32, true));
        equipments.add(new LaptopSet("E002", "Dell XPS Demo", 100, 16, false));
        equipments.add(new CameraKit("E003", "Sony Content Kit", 90, 3, true));
        equipments.add(new CameraKit("E004", "Canon Interview Kit", 70, 1, true));
    }

    public void printStudents() {
        System.out.println("\n--- Students List ---");
        for (Student s : students) {
            System.out.printf("ID: %s | Name: %s | Group: %s | Points: %d\n", s.getId(), s.getFullName(), s.getGroupName(), s.getLoyaltyPoints());
        }
    }

    public void printEquipment() {
        System.out.println("\n--- Equipment List ---");
        for (Equipment e : equipments) {
            System.out.println(e.getDisplayText());
        }
    }

    public void createReservation(String studentId, String equipmentId, int days) {
        Student student = students.stream().filter(s -> s.getId().equalsIgnoreCase(studentId)).findFirst().orElse(null);
        Equipment equipment = equipments.stream().filter(e -> e.getId().equalsIgnoreCase(equipmentId)).findFirst().orElse(null);

        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        if (equipment == null) {
            System.out.println("Error: Equipment not found!");
            return;
        }
        if (!equipment.isAvailable()) {
            System.out.println("Error: Equipment is not available.");
            return;
        }
        if (days < 1 || days > 14) {
            System.out.println("Error: Rental days must be between 1 and 14.");
            return;
        }

        String resId = String.format("R%03d", reservationCounter++);
        Reservation reservation = new Reservation(resId, student, equipment, days, discountPolicy);
        equipment.setAvailable(false);
        reservations.add(reservation);

        System.out.println("\nReservation " + resId + " created successfully.");
        System.out.println("Equipment: " + equipment.getName());
        System.out.printf("Cost: %.2f PLN\n", reservation.getTotalCost());
    }

    public void returnEquipment(String reservationId) {
        Reservation res = reservations.stream().filter(r -> r.getId().equalsIgnoreCase(reservationId)).findFirst().orElse(null);

        if (res == null) {
            System.out.println("Error: Reservation not found.");
            return;
        }
        if (res.getStatus() != ReservationStatus.ACTIVE) {
            System.out.println("Error: This reservation is already closed or cancelled.");
            return;
        }

        res.setStatus(ReservationStatus.RETURNED);
        res.getEquipment().setAvailable(true);

        int earnedPoints = (int) (res.getTotalCost() / 10);
        res.getStudent().addLoyaltyPoints(earnedPoints);

        System.out.println("\nEquipment returned. The student received " + earnedPoints + " loyalty points.");
    }

    public void printActiveReservations() {
        System.out.println("\n--- Active Reservations ---");
        boolean hasActive = false;
        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                System.out.println(r.getDisplayText());
                hasActive = true;
            }
        }
        if (!hasActive) System.out.println("No active reservations found.");
    }

    public void printReport() {
        System.out.println("\n--- Final Business Report ---");
        double totalRevenue = 0;
        System.out.println("Completed Reservations:");
        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.RETURNED) {
                System.out.println(" - " + r.getDisplayText());
                totalRevenue += r.getTotalCost();
            }
        }
        System.out.printf("Total Revenue from Completed Rentals: %.2f PLN\n", totalRevenue);

        Student topStudent = students.stream().max((s1, s2) -> Integer.compare(s1.getLoyaltyPoints(), s2.getLoyaltyPoints())).orElse(null);
        if (topStudent != null) {
            System.out.println("Student with highest loyalty points: " + topStudent.getFullName() + " (" + topStudent.getLoyaltyPoints() + " points)");
        }
    }
}
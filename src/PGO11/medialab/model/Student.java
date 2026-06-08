package PGO11.medialab.model;

public class Student {
    private String id;
    private String fullName;
    private String groupName;
    private int loyaltyPoints;

    public Student(String id, String fullName, String groupName, int loyaltyPoints) {
        this.id = id;
        this.fullName = fullName;
        this.groupName = groupName;
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getGroupName() { return groupName; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
}
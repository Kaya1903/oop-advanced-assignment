package PGO11.medialab.interfaces;

import PGO11.medialab.model.Student;

public interface DiscountPolicy {
    double applyDiscount(Student student, double price);
}
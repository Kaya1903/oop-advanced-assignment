package PGO9;

@FunctionalInterface
public interface PriceStrategy {
    double calculate(ServiceOrder order);
}
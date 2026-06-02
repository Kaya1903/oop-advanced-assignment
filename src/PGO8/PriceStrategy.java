package PGO8;

@FunctionalInterface
public interface PriceStrategy {
    double calculate(ServiceOrder order);
}
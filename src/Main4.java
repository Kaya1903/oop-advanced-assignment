public class Main4 {
    public static void main(String[] args) {
        LibraryAccount account = new LibraryAccount("John Smith", 3, 5);
        LibraryAccount.FineCalculator calculator = account.new FineCalculator();

        double fine = calculator.calculate();

        MessagePrinter printer = message -> System.out.println("[LIBRARY] " + message);
        printer.print("Reader: " + account.getReaderName() + ", fine: " + fine + " PLN");
    }
}
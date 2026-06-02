package PGO8;

public class Main1 {
    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        UserForm form = new UserForm("anna@example.com", "secure123", 20);
        System.out.println("Is form valid? " + validator.isValid(form));
    }
}
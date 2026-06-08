package PGO9;

public record UserForm(String email, String password, int age) {
    public UserForm {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank!");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank!");
        }
    }
}
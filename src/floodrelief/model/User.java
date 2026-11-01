 

package floodrelief.model;

public class User {

    private int userId;
    private String username;
    private String password;
    private String role;

    public User(int userId, String username, String password, String role) {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (!role.equalsIgnoreCase("Admin")
                && !role.equalsIgnoreCase("Relief Manager")
                && !role.equalsIgnoreCase("Volunteer")) {
            throw new IllegalArgumentException("Invalid user role.");
        }

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

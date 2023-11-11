```java
package services;

import java.util.HashMap;
import java.util.UUID;

/**
 * UserService handles user-related operations such as registration,
 * authentication, and profile management within Elysium OS.
 */
public class UserService {

    private HashMap<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    /**
     * Registers a new user in the system.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return the unique ID of the newly created user
     */
    public String registerUser(String username, String password) {
        if (username == null || password == null || users.containsKey(username)) {
            throw new IllegalArgumentException("Invalid username or password, or user already exists.");
        }
        String userId = UUID.randomUUID().toString();
        User newUser = new User(userId, username, password);
        users.put(username, newUser);
        return userId;
    }

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the unique ID of the user if authentication is successful
     */
    public String authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user.getUserId();
        }
        throw new SecurityException("Authentication failed.");
    }

    /**
     * Retrieves a user's profile information.
     *
     * @param userId the unique ID of the user
     * @return the user's profile information
     */
    public UserProfile getUserProfile(String userId) {
        for (User user : users.values()) {
            if (user.getUserId().equals(userId)) {
                return user.getProfile();
            }
        }
        throw new IllegalArgumentException("User not found.");
    }

    /**
     * Updates a user's profile information.
     *
     * @param userId the unique ID of the user
     * @param newProfile the new profile information for the user
     */
    public void updateUserProfile(String userId, UserProfile newProfile) {
        for (User user : users.values()) {
            if (user.getUserId().equals(userId)) {
                user.setProfile(newProfile);
                return;
            }
        }
        throw new IllegalArgumentException("User not found.");
    }

    // Inner class to represent a user
    private static class User {
        private String userId;
        private String username;
        private String password;
        private UserProfile profile;

        public User(String userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.profile = new UserProfile(); // Initialize with default profile
        }

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public UserProfile getProfile() {
            return profile;
        }

        public void setProfile(UserProfile profile) {
            this.profile = profile;
        }
    }

    // Inner class to represent a user's profile
    private static class UserProfile {
        // Profile fields like avatar, bio, preferences, etc.
        // Placeholder for actual profile implementation
    }
}
```
```java
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    private final Map<String, String> userCredentials = new HashMap<>();

    public boolean registerUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return false; // User already exists
        }
        String hashedPassword = hashPassword(password);
        userCredentials.put(username, hashedPassword);
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(userCredentials.get(username));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
```
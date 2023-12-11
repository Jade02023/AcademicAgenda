package com.example.academicagendav1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    private String userId;
    private String email;
    private final String hashedPassword;
    private final byte[] salt;

    // Constructor
    public User(String email, String password) {
        this.userId = generateUserId();  // generate a unique user ID
        this.email = email;
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, salt);
    }

       private String generateUserId() {
        //  generate a unique user ID
        return "some_unique_id";
    }

    // Method to generate a random salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Method to hash the password with the provided salt
    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to check if the entered password matches the stored hashed password
    public boolean isPasswordCorrect(String enteredPassword) {
        String hashedEnteredPassword = hashPassword(enteredPassword, this.salt);
        return hashedEnteredPassword != null && hashedEnteredPassword.equals(this.hashedPassword);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
package com.example.academicagendav1.data;

import com.example.academicagendav1.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 * @noinspection ALL
 */
public class LoginDataSource {

    public Result login(String username, String password) {

        try {
            /* TODO: handle loggedInUser authentication */
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            Result.Success<LoggedInUser> loggedInUserSuccess = new Result.Success<>(fakeUser);
            return loggedInUserSuccess;
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
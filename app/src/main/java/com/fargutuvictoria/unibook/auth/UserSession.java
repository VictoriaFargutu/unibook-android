package com.fargutuvictoria.unibook.auth;

import com.fargutuvictoria.commons.model.User;

public class UserSession {

    private static UserSession INSTANCE;

    private User loggedInUser;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserSession();
        }
        return INSTANCE;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}

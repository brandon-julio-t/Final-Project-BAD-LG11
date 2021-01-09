package facades;

import models.abstracts.User;

public class Authentication {
    private static User user = null;

    public static void login(User user) {
        Authentication.user = user;
    }

    public static void logout() {
        user = null;
    }

    public static User getUser() {
        return user;
    }
}

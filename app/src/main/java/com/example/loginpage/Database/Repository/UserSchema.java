package com.example.loginpage.Database.Repository;

public class UserSchema {
    public static final String  NAME="user.db";
    public static final int VERSION=1;

    public static final class User{
        public static final String NAME="userTable";

        public static final class UserColumn{
            public static final String ID="id";
            public static final String UUID="uuid";
            public static final String USER_NAME="username";
            public static final String PASSWORD="password";
        }
    }
}

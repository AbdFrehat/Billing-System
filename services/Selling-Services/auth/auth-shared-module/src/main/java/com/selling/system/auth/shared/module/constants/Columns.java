package com.selling.system.auth.shared.module.constants;

public class Columns {

    public static class Profile {
        public static final String PROFILE_ID = "profile_id";
        public static final String PROFILE_NAME = "profile_name";
    }

    public static class Authority {
        public static final String AUTHORITY_ID = "authority_id";
        public static final String AUTHORITY_NAME = "authority_name";
    }

    public static class Group {
        public static final String GROUP_ID = "group_id";
        public static final String GROUP_NAME = "group_name";
    }

    public class User {
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "username";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PHONE = "phone";
        public static final String PROFILE_ID = "profile_id";
        public static final String CREATED_AT = "create_at";
        public static final String IS_ENABLED = "is_enabled";
        public static final String IS_ACCOUNT_EXPIRED = "is_account_expired";
        public static final String IS_CREDENTIAL_EXPIRED = "is_credential_expired";
        public static final String IS_ACCOUNT_LOCKED= "is_account_locked";
        public static final String LAST_PASSWORD_CHANGED = "last_password_changed";
        public static final String COUNTRY = "country";
        public static final String CITY = "city";
        public static final String STREET = "street";
    }
}

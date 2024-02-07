package com.selling.system.auth.shared.module.constants;

public final class Columns {

    public static final class Profile {
        public static final String PROFILE_ID = "profile_id";
        public static final String PROFILE_NAME = "profile_name";
    }

    public static final class Authority {
        public static final String AUTHORITY_ID = "authority_id";
        public static final String AUTHORITY_NAME = "authority_name";
    }

    public static final class Group {
        public static final String GROUP_ID = "group_id";
        public static final String GROUP_NAME = "group_name";
    }

    public static class User {
        public static final String USER_ID = "user_id";
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PHONE = "phone";
        public static final String CREATED_AT = "create_at";
        public static final String IS_ENABLED = "is_enabled";
        public static final String IS_ACCOUNT_EXPIRED = "is_account_expired";
        public static final String IS_CREDENTIAL_EXPIRED = "is_credential_expired";
        public static final String IS_ACCOUNT_LOCKED = "is_account_locked";
        public static final String LAST_PASSWORD_CHANGED = "last_password_changed";
        public static final String COUNTRY = "country";
        public static final String CITY = "city";
        public static final String STREET = "street";
    }

    public static class GrantType {
        public static final String GRANT_ID = "grant_id";
        public static final String GRANT_TYPE = "grant_type";
    }

    public static final class Bind {
        public static final String UPDATED_PROFILE_NAME = "updated_profile_name";
        public static final String UPDATED_USERNAME = "updated_username";
    }

    public static final class Fields {
        public static final String COUNT = "count";
    }
}

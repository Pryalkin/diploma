package com.pryalkin.portal.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read", "user:create" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:create", "user:block", "user:delete" };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:block", "user:delete",
            "user:appoint", "user:access" };
}

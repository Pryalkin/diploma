package com.pryalkin.portal.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read", "user:create", "user:edit", "user:update", "user:change" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:create", "user:block", "user:edit", "user:delete_message",
            "user:correct", "user:update", "user:change" };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:block", "user:delete_message",
            "user:delete_user", "user:correct", "user:edit", "user:appoint", "user:access", "user:update", "user:change" };
}

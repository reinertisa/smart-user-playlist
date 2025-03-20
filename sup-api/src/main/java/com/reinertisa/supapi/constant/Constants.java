package com.reinertisa.supapi.constant;

public class Constants {
    public static final String FILE_STORAGE = System.getProperty("user.home") + "/Downloads/uploads/";
    public static final String[] PUBLIC_URLS = { "/api/v1/users/resetpassword/reset/**", "/api/v1/users/verify/resetpassword/**", "/api/v1/users/resetpassword/**", "/api/v1/users/verify/qrcode/**", "/api/v1/users/login/**", "/api/v1/users/verify/account/**", "/api/v1/users/register/**", "/api/v1/users/new/password/**", "/api/v1/users/verify/**", "/api/v1/users/resetpassword/**", "/api/v1/users/image/**", "/api/v1/users/verify/password/**" };
    public static final int NINETY_DAYS = 90;
    public static final int STRENGTH = 12;
    public static final String BASE_PATH = "/**";
    public static final String FILE_NAME = "File-Name";
    public static final String LOGIN_PATH = "/api/v1/users/login";

    public static final String[] PUBLIC_ROUTES = { "/api/v1/users/resetpassword/reset", "/api/v1/users/verify/resetpassword", "/api/v1/users/resetpassword", "/api/v1/users/verify/qrcode", "/api/v1/users/stream", "/api/v1/users/id", "/api/v1/users/login", "/api/v1/users/register", "/api/v1/users/new/password", "/api/v1/users/verify", "/api/v1/users/refresh/token", "/api/v1/users/resetpassword", "/api/v1/users/image", "/api/v1/users/verify/account", "/api/v1/users/verify/password", "/api/v1/users/verify/code"};
    public static final String AUTHORITIES = "authorities";
    public static final String GET_ARRAYS_LLC = "GET_ARRAYS_LLC";
    public static final String EMPTY_VALUE = "empty";
    public static final String ROLE = "role";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_DELIMITER = ",";
    public static final String USER_AUTHORITIES = "document:create,document:read,document:update,document:delete";
    public static final String ADMIN_AUTHORITIES = "user:create,user:read,user:update,document:create,document:update,document:delete";
    public static final String SUPER_ADMIN_AUTHORITIES = "user:create,user:read,user:update,user:delete,document:create,document:update,document:delete";
    public static final String MANAGER_AUTHORITIES = "document:create,document:read,document:update,document:delete";
}

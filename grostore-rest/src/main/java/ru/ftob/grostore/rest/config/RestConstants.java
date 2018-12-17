package ru.ftob.grostore.rest.config;

public class RestConstants {

    public static final String REST_APPLICATION_PROPERTIES_FILE = "file:/var/lib/grostore/conf/application.yml";
    public static final String REST_OAUTH_SECURITY_PROPERTIES_FILE = "file:/var/lib/grostore/conf/oauth_security.properties";
    public static final String REST_DB_CONNECTION_PROPERTIES_FILE = "file:/var/lib/grostore/conf/db_connection.properties";

    public static final String LOGIN_URL_PATTERN = "/login**";
    public static final String ERROR_URL_PATTERN = "/error**";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGOUT_SUCCESS_URL = "/";

    public static final String J_SESSION_ID = "JSESSIONID";

    public static final String GOOGLE_LOGIN_URL = "/login/google";
    public static final String GOOGLE_CLIENT_KEY = "google.client";
    public static final String GOOGLE_RESOURCE_KEY = "google.resource";

    public static final String UCOZ_API_KEY = "ucoz";

}

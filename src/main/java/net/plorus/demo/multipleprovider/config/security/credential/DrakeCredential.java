package net.plorus.demo.multipleprovider.config.security.credential;

public class DrakeCredential {
    private final String cookie;

    public DrakeCredential(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }
}

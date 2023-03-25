package net.plorus.demo.multipleprovider.platform.amanda.controller.client.dto;

public class AmandaInternalResAuthentication {
    private final String name;
    private final String uid;
    private final Boolean isAdult;
    private final String expired;
    private final String refreshToken;

    public AmandaInternalResAuthentication(String name, String uid, Boolean isAdult, String expired, String refreshToken) {
        this.name = name;
        this.uid = uid;
        this.isAdult = isAdult;
        this.expired = expired;
        this.refreshToken = refreshToken;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public String getExpired() {
        return expired;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

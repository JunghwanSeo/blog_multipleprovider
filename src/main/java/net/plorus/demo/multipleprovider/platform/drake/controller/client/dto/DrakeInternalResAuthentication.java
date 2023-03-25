package net.plorus.demo.multipleprovider.platform.drake.controller.client.dto;

public class DrakeInternalResAuthentication {
    private final String name;
    private final Long uid;
    private final String issuer;
    private final String expired;

    public DrakeInternalResAuthentication(String name, Long uid, String issuer, String expired) {
        this.name = name;
        this.uid = uid;
        this.issuer = issuer;
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public Long getUid() {
        return uid;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getExpired() {
        return expired;
    }
}

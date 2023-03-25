package net.plorus.demo.multipleprovider.config.security.principal;

public class DrakePrincipal {
    private final String name;
    private final Long uid;

    public DrakePrincipal(String name, Long uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public Long getUid() {
        return uid;
    }
}

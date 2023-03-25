package net.plorus.demo.multipleprovider.config.security.principal;

public class AmandaPrincipal {
    private final String name;
    private final String uid;
    private final Boolean isAdult;

    public AmandaPrincipal(String name, String uid, Boolean isAdult) {
        this.name = name;
        this.uid = uid;
        this.isAdult = isAdult;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public Boolean isAdult() {
        return this.isAdult != null && this.isAdult;
    }
}

package com.drgn.common.model.enums;

public enum Server {
    Consumer("consumer", "consumer", "/consumer"),
    Provider("provider", "provider", "/provider"),
    Hystrix("hystrix", "hystrix", "/hystrix"),
    Astronaut("astronaut", "astronaut", "/astronaut"),
    Drgnmon("drgnmon", "drgnmon", "/drgnmon");

    public final String name;
    public final String value;
    public final String contextPath;

    Server(String name, String value, String contextPath) {
        this.name = name;
        this.value = value;
        this.contextPath = contextPath;
    }

    public static Server initFromName(String name) {
        for (Server s : Server.values()) {
            if (name.equals(s.name)) {
                return s;
            }
        }
        return null;
    }

    public static Server initFromValue(String value) {
        for (Server s : Server.values()) {
            if (value.equals(s.value)) {
                return s;
            }
        }
        return null;
    }

    public static Server initFromContextPath(String contextPath) {
        for (Server s : Server.values()) {
            if (contextPath.equals(s.contextPath)) {
                return s;
            }
        }
        return null;
    }
}

package com.bsx.mq.enums;

/**
 * 路由
 */
public enum RoutingKeyEnum {
    SJ_TEST("routingkey.superloan.test", "TEST"),
    SJ_DEV("routingkey.superloan.dev", "DEV");


    private String code;
    private String name;

    RoutingKeyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

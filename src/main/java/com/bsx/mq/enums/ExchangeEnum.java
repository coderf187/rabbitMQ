package com.bsx.mq.enums;

/**
 * MQ交换机
 */
public enum ExchangeEnum {
    SJ_TEST("exchange.superloan.test", "TEST"),
    SJ_DEV("exchange,superloan.dev","DEV");

    private String code;
    private String name;

    ExchangeEnum(String code, String name) {
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

   
package com.myproject.dsdeliver.dto;

public enum OrderStatusDTO {

    PENDING(0), DELIVERED(1);    

    private int code;

    private OrderStatusDTO(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static OrderStatusDTO valueOf(int code) {
        for (OrderStatusDTO value : OrderStatusDTO.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
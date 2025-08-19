package com.demoAI.spring_ai_.dto;

public record BillItem(
        String itemName,
        String unit,
        Integer quantity,
        Double price,
        Double subTotal
) {
}

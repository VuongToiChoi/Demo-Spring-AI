package com.demoAI.spring_ai_.dto;

public record ExpenseInfo(
        String category,
        String itemName,
        Double amount
) {
}

package com.example.movies.common;

public enum ReviewType {
    NEGATIVE("!Негативный"),
    NEUTRAL("Нейтральный"),
    POSITIVE("Позитивный");

    private final String description;

    ReviewType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

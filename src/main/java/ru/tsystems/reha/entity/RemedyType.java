package ru.tsystems.reha.entity;

public enum RemedyType {

    DRUG("Medicine"), PROCEDURE("Procedure"), SPECIAL("Гncountable");

    private final String typeName;

    RemedyType(String typeName) {
        this.typeName = typeName;
    }

    public String gerTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}

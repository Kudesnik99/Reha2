package ru.tsystems.reha.entity.enums;

public enum RemedyType {

    DRUG("Medicine"), PROCEDURE("Procedure"), SPECIAL("Uncountable");

    private final String typeName;

    RemedyType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}

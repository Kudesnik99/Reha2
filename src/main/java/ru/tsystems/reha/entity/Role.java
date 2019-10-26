package ru.tsystems.reha.entity;

public enum Role {

    ADMIN("Administrator"), DOCTOR("Doctor"), NURSE("Nurse");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String gerRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}

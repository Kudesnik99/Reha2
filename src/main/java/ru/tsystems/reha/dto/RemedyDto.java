package ru.tsystems.reha.dto;

import ru.tsystems.reha.entity.enums.RemedyType;

public class RemedyDto {

    private Long remedyId;

    private String name;

    private RemedyType type;

    private String unit;

    public Long getRemedyId() {
        return remedyId;
    }

    public void setRemedyId(Long remedyId) {
        this.remedyId = remedyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemedyType getType() {
        return type;
    }

    public void setType(RemedyType type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

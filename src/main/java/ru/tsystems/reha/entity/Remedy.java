package ru.tsystems.reha.entity;

import javax.persistence.*;

@Entity
public class Remedy {


    private int remedyId;
    private String name;
    private RemedyType type;
    private String unit;

    @Id
    @Column(name = "remedy_id")
    public int getRemedyId() {
        return remedyId;
    }
    public void setRemedyId(int remedyId) {
        this.remedyId = remedyId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public RemedyType getType() { return type; }
    public void setType(RemedyType type) { this.type = type; }

    @Column(name = "unit")
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remedy remedy = (Remedy) o;

        return remedyId == remedy.remedyId;
    }

    @Override
    public int hashCode() {
        int result = remedyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}



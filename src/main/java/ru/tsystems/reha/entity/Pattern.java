package ru.tsystems.reha.entity;

import javax.persistence.*;

@Entity
public class Pattern {
    private int patternId;
    private String timePattern;
    private String patternTemplate;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pattern_id")
    public int getPatternId() {
        return patternId;
    }
    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    @Column(name = "pattern_name")
    public String getTimePattern() {
        return timePattern;
    }
    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    @Column(name = "pattern_template")
    public String getPatternTemplate() {
        return patternTemplate;
    }
    public void setPatternTemplate(String patternTemplate) {
        this.patternTemplate = patternTemplate;
    }


}

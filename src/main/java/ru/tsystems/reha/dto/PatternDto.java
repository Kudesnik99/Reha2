package ru.tsystems.reha.dto;

public class PatternDto {
    private Long patternId;

    private String timePattern;

    private String patternTemplate;

    public Long getPatternId() {
        return patternId;
    }

    public void setPatternId(Long patternId) {
        this.patternId = patternId;
    }

    public String getTimePattern() {
        return timePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    public String getPatternTemplate() {
        return patternTemplate;
    }

    public void setPatternTemplate(String patternTemplate) {
        this.patternTemplate = patternTemplate;
    }
}

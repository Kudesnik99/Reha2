package ru.tsystems.reha.jms.messages;

import java.util.ArrayList;
import java.util.List;

public class UpdateEventsListMsg {
    private String action;
    private List<Long> eventIds;

    public UpdateEventsListMsg(String action) {
        this.action = action;
        this.eventIds = new ArrayList<Long>();
    }

    public List<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Long> eventIds) {
        this.eventIds = eventIds;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void addId(Long id) {
        eventIds.add(id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"action\": \"" + action + "\", "  +
                "\"eventIds\":" + eventIds +
                '}';
    }
}

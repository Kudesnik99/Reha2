package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Event;

import java.util.List;

public interface EventService {

    public List<Event> getEvents() throws ServiceException;
}

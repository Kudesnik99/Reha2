package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Remedy;

import java.util.List;

public interface RemedyService {
    public List<Remedy> getRemedies() throws ServiceException;

    public void saveRemedy(Remedy theRemedy) throws ServiceException;

    public Remedy getRemedy(int theId) throws ServiceException;

    public void deleteRemedy(int theId);
}

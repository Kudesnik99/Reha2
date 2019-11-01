package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Pattern;

import java.util.List;

public interface PatternService {

    List<Pattern> getPatterns() throws ServiceException;

    Pattern getPattern(int theId) throws ServiceException;

    void savePattern(Pattern pattern) throws ServiceException;

    public void deletePattern(int theId) throws ServiceException;
}

package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.PatternDto;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface PatternService {

    List<PatternDto> getPatterns() throws ServiceException;

    PatternDto getPattern(Long id) throws ServiceException;

    void savePattern(PatternDto pattern) throws ServiceException;

    void deletePattern(Long id) throws ServiceException;
}

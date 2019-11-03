package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.RemedyDto;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface RemedyService {
    List<RemedyDto> getRemedies() throws ServiceException;

    void saveRemedy(RemedyDto remedy) throws ServiceException;

    RemedyDto getRemedy(Long id) throws ServiceException;

    void deleteRemedy(Long id) throws ServiceException;
}

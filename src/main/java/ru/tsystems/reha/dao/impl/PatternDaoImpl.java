package ru.tsystems.reha.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.PatternDao;
import ru.tsystems.reha.entity.Pattern;

@Repository
public class PatternDaoImpl extends GenericDaoImpl<Pattern, Long> implements PatternDao {
}

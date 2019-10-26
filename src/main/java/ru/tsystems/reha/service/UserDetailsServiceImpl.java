package ru.tsystems.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.UserDao;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userDao.findByEmail(email);

        if (user != null) {
            return new UserDto(user);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }
}

package com.epam.mentoring.homework3.banking.service;

import com.epam.mentoring.homework3.banking.domain.User;
import com.epam.mentoring.homework3.banking.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User service.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User read(String s) {
        return userRepository.read(s);
    }

    @Override
    public User store(User data) {
        return userRepository.store(data.getUserId(), data);
    }

    @Override
    public User update(User data) {
        return userRepository.update(data.getUserId(), data);
    }

    @Override
    public User delete(String s) {
        return userRepository.delete(s);
    }

    @Override
    public List<User> readAll() {
        return Lists.newArrayList(userRepository.readAll());
    }
}

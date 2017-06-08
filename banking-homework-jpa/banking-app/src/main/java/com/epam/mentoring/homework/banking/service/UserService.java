package com.epam.mentoring.homework.banking.service;

import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.repository.embedded.api.IUserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private IUserRepository userRepository;

    @Override
    public User read(Integer id) {
        return userRepository.read(id);
    }

    @Override
    public User store(User data) {
        return userRepository.store(data);
    }

    @Override
    public User update(User data) {
        return userRepository.update(data);
    }

    @Override
    public User delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> readAll() {
        return Lists.newArrayList(userRepository.getAll());
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}

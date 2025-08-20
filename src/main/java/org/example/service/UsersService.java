package org.example.service;

import org.example.model.Users;
import org.example.repository.UsersRepository;

public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // user registration
    public boolean register(String username, String password) {
        if (usersRepository.getByUsername(username) != null) {
            return false; //user already exists
        }
        Users user = new Users(0, username, password);
        return usersRepository.insert(user);
    }

    //entrance
    public Users login(String username, String password) {
        Users user = usersRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public Users getUserById(int userId) {
        return usersRepository.getById(userId); // UsersRepository içinde getById olmalı
    }

}

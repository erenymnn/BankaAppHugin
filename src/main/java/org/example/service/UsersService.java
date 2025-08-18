package org.example.service;

import org.example.model.Users;
import org.example.repository.UsersRepository;

public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Kullanıcı kaydı
    public boolean register(String username, String password) {
        if (usersRepository.getByUsername(username) != null) {
            return false; // kullanıcı zaten var
        }
        Users user = new Users(0, username, password);
        return usersRepository.insert(user);
    }

    // Giriş
    public Users login(String username, String password) {
        Users user = usersRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

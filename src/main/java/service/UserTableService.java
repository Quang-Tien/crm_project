package service;

import model.UserModel;
import repository.UserRepository;

import java.util.List;

public class UserTableService {
    UserRepository userRepository = new UserRepository();

    public List<UserModel> getAllUsers() {
        return userRepository.getAll();
    }
}

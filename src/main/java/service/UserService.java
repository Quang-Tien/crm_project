package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepository = new UserRepository();
    RoleRepository roleRepository = new RoleRepository();
    public List<UserModel> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean insertUser(String email, String password, String fullname, int role_id) {
        return userRepository.insertUser(fullname, email, password, role_id);
    }

    public List<RoleModel> getAllRoles() {
        return roleRepository.getAll();
    }

    public boolean deleteById(int id) {
        return userRepository.deleteById(id);
    }
}

package service;

import model.UserModel;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    //tham số của function trong service luôn >= so với tham số của function trong repository
    private UserRepository userRepository = new UserRepository();
    public boolean checkLogin(String email, String password) {
        List<UserModel> list = userRepository.findByEmailAndPassword(email, password);
        return list.size() > 0;
    }
}

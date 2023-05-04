package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleTableService {
    RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRoles() {
        return roleRepository.getAll();
    }
}

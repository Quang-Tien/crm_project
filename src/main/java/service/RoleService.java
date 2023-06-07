package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRoles() {
        return roleRepository.getAll();
    }
    public boolean insertRole(String roleName, String roleDesc) {
        return roleRepository.insertRole(roleName, roleDesc);
    }

    public boolean deleteRole(int id) {
        return roleRepository.deleteRole(id);
    }
}

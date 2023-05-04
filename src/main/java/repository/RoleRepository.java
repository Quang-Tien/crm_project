package repository;
import config.MysqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    public List<RoleModel> getAll() {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();

        try {
            String sql = "select * from roles";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();

                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDesc(resultSet.getString("description"));

                roleModelList.add(roleModel);
            }

        } catch (Exception e) {
            System.out.println("Error getAll : " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error getAll : " + e.getMessage());
                }
            }
        }
        return roleModelList;
    }
}

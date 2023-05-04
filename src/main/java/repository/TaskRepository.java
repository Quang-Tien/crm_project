package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> getAll() {
        Connection connection = null;
        List<TaskModel> taskModelList = new ArrayList<>();

        try {
            String sql = "select * from tasks";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TaskModel taskModel = new TaskModel();

                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStart_date(resultSet.getDate("start_date"));
                taskModel.setEnd_date(resultSet.getDate("end_date"));
                taskModel.setJob_id(resultSet.getInt("job_id"));
                taskModel.setUser_id(resultSet.getInt("user_id"));
                taskModel.setStatus_id(resultSet.getInt("status_id"));

                taskModelList.add(taskModel);
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
        return taskModelList;
    }
}

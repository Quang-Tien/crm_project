package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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


    public boolean insertTask(String name, int jobId, int userId, Date startDate, Date endDate) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into tasks(name,job_id,user_id,start_date,end_date) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, jobId);
            statement.setInt(3, userId);
            statement.setDate(4, new java.sql.Date(startDate.getTime()));
            statement.setDate(5, new java.sql.Date(endDate.getTime()));

            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error query insertTask : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error insertTask : " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean deleteTask(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "delete from tasks t where t.id = ?";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleteTask: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error deleteTask: " + e.getMessage());
                }
            }
        }

        return isSuccess;
    }
}

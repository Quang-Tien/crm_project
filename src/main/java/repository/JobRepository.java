package repository;

import config.MysqlConfig;
import model.JobModel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobRepository {

    public List<JobModel> getAll() {
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();

        try {
            String sql = "select * from jobs";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                JobModel jobModel = new JobModel();

                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setStartDate(resultSet.getDate("start_date"));
                jobModel.setEndDate(resultSet.getDate("end_date"));
                jobModelList.add(jobModel);
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
        return jobModelList;
    }

    public boolean insertJob(String name, Date startDate, Date endDate) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into jobs(name,start_date,end_date) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setDate(3, new java.sql.Date(endDate.getTime()));

            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error query insertJob: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error insertJob : " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean deleteJob(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "delete from jobs j where j.id = ?";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleteJob: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Connection-close error deleteJob: " + e.getMessage());
                }
            }
        }

        return isSuccess;
    }
}

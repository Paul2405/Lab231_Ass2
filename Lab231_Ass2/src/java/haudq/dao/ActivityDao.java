/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dao;

import haudq.dto.ActivityDto;
import haudq.util.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haudq
 */
public class ActivityDao implements Serializable {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private void closeConnection() throws Exception {
        if (resultSet != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public List<ActivityDto> getActivityByUserId(int id) throws Exception {
        List<ActivityDto> result = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("select userId, byUserId, action, date, name, email from [dbo].[Active]  where userId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ActivityDto d = new ActivityDto();
                d.setUserId(resultSet.getInt("userId"));
                d.setByUserId(resultSet.getInt("byUserId"));
                d.setActivity(resultSet.getString("action"));
                d.setDate(resultSet.getString("date"));
                d.setName(resultSet.getString("name"));
                d.setEmail(resultSet.getString("email"));

                result.add(d);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertActivity(ActivityDto dto) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT into [dbo].[Active](userId, byUserId, action, name, email) values(?,?,?,?,?)");
            preparedStatement.setInt(1, dto.getUserId());
            preparedStatement.setInt(2, dto.getByUserId());
            preparedStatement.setString(3, dto.getActivity());
            preparedStatement.setString(4, dto.getName());
            preparedStatement.setString(5, dto.getEmail());
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}

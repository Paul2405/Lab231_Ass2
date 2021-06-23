/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dao;

import haudq.dto.GroupDto;
import haudq.dto.UserDto;
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
public class UserDao implements Serializable {

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
    
   
    public int getId() throws Exception{
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT TOP 1 id FROM [dbo].[User] ORDER BY id DESC");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        } finally{
            closeConnection();
        }
        return -1;
    }
    public GroupDto getGroupByUserId(int userId) throws Exception {
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[Group] as g join GroupUser as gr on g.Id = gr.groupId where userId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                GroupDto gr = new GroupDto(resultSet.getInt("id"), resultSet.getString("name"));
                return gr;
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean deletePromotionByUserId(List<Integer> userId) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            for (Integer id : userId) {
                preparedStatement = connection.prepareStatement("Delete [dbo].[Promotion] where userId = ?");
                preparedStatement.setInt(1, id);
                result = preparedStatement.executeUpdate() > 0;
                if (!result) {
                    return result;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRankByUserId(int id, int rank) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Update [dbo].[Promotion] set rank = ? where userId = ?");
            preparedStatement.setInt(1, rank);
            preparedStatement.setInt(2, id);
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDto> getPromotionList() throws Exception {
        List<UserDto> result = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("select * from [dbo].[User] as u join [dbo].[Promotion] as p on u.id = p.userId");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto dto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"), resultSet.getInt("rank"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkUserId(int userId) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("select id from [dbo].[Promotion] where userId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertUserIntoPromotion(int userId, int rank) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into [dbo].[Promotion](userId,rank) "
                    + "  values(?,?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, rank);
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertUser(UserDto dto) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into [dbo].[User](userId,password,role,email,phone,avatar,name,status) "
                    + "  values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, dto.getUserId());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setString(3, dto.getRole());
            preparedStatement.setString(4, dto.getEmail());
            preparedStatement.setString(5, dto.getPhone());
            preparedStatement.setString(6, dto.getAvatar());
            preparedStatement.setString(7, dto.getName());
            preparedStatement.setString(8, dto.getStatus());
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateUserById(UserDto dto) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("update [dbo].[User] set "
                    + "userId = ? , password = ?, role = ?, email = ?, phone = ?,avatar = ?, name =? "
                    + "where [id] = ?");
            preparedStatement.setString(1, dto.getUserId());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setString(3, dto.getRole());
            preparedStatement.setString(4, dto.getEmail());
            preparedStatement.setString(5, dto.getPhone());
            preparedStatement.setString(6, dto.getAvatar());
            preparedStatement.setString(7, dto.getName());
            preparedStatement.setInt(8, dto.getId());

            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkUserId(String userId) throws Exception {
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select id from [dbo].[User] where userId = ?");
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public UserDto getUserById(int id) throws Exception {
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User] where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"));
                return userDto;
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public UserDto loginByUserIdAndPassword(String userId, String password) throws Exception {
        UserDto userDto = new UserDto();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User] where userId = ? and password = ? and status = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "active");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDto.setId(resultSet.getInt("id"));
                userDto.setEmail(resultSet.getString("email"));
                userDto.setPhone(resultSet.getString("phone"));
                userDto.setAvatar(resultSet.getString("avatar"));
                userDto.setStatus(resultSet.getString("status"));
                userDto.setName(resultSet.getString("name").trim());
                userDto.setUserId(resultSet.getString("userId").trim());
                userDto.setPassword(resultSet.getString("password").trim());
                userDto.setRole(resultSet.getString("role").trim());
            }
        } finally {
            closeConnection();
        }
        return userDto;
    }

    public List<UserDto> getListUser() throws Exception {
        List<UserDto> listUser = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User]");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"));
                listUser.add(userDto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public List<UserDto> getListUserByRole(String role) throws Exception {
        List<UserDto> listUser = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User] where role = ?");
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"));
                listUser.add(userDto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public List<UserDto> getListUserByGroupId(int groupId) throws Exception {
        List<UserDto> listUser = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User] as u join GroupUser as gr on u.Id = gr.userId where gr.groupId = ?");
            preparedStatement.setInt(1, groupId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"));
                listUser.add(userDto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }
    
    public List<UserDto> getListUserByName(String name) throws Exception {
        List<UserDto> listUser = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("Select * from [dbo].[User] where name like ?");
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("userId"),
                        resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("phone"),
                        resultSet.getString("avatar"), resultSet.getString("status"));
                listUser.add(userDto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public boolean updateStatusById(List<Integer> ids, String status) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            for (Integer id : ids) {
                preparedStatement = connection.prepareStatement("update [dbo].[User] set [status] = ?  where [id] = ?");
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, id);
                result = preparedStatement.executeUpdate() > 0;
                if (!result) {
                    return result;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}

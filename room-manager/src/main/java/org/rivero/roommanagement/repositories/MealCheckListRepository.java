package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.dtos.MealCheckListDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class MealCheckListRepository {
    public void insert(Connection connection, MealCheckListDto list) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mealchecklist VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, list.month());
            preparedStatement.setString(3, list.checkList());
            preparedStatement.setString(4, list.consumerId());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MealCheckListDto> getList(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mealchecklist");
            ArrayList<MealCheckListDto> resultList = new ArrayList<MealCheckListDto>();
            while (rs.next()) {
                String id = rs.getString("id");
                String month = rs.getString("month");
                String consumerId = rs.getString("consumerid");
                ZonedDateTime time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault());
                String checkList = rs.getString("checklist");
                resultList.add(new MealCheckListDto(id, month, checkList, consumerId, time));
            }
            return resultList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MealCheckListDto getOne(Connection connection, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mealchecklist WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mealid = rs.getString("id");
                String month = rs.getString("month");
                String consumerid = rs.getString("consumerid");
                String checklist = rs.getString("checklist");
                ZonedDateTime time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault());
                return new MealCheckListDto(id, month, checklist, consumerid, time);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void updateOne(Connection connection, MealCheckListDto mealCheckList) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mealchecklist SET month = ?, consumerid = ?, checkList = ? WHERE id = ?");
            preparedStatement.setString(1, mealCheckList.month());
            preparedStatement.setString(2, mealCheckList.consumerId());
            preparedStatement.setString(3, mealCheckList.checkList());
            preparedStatement.setString(4, mealCheckList.id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
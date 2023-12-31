package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.dtos.MealCheckListDTO;
import org.rivero.roommanagement.entities.MealCheckList;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class MealCheckListRepository {
    public void insert(Connection connection, MealCheckListDTO list){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mealchecklist VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, list.month());
            preparedStatement.setArray(3,list.checkList() );
            preparedStatement.setString(4, list.consumerId());
            preparedStatement.setTimestamp(5,  new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MealCheckList> getList(Connection connection){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mealchecklist");
            ArrayList<MealCheckList> resultList = new ArrayList<MealCheckList>();
            while(rs.next()){
                String id = rs.getString("id");
                String month = rs.getString("month");
                String consumerId = rs.getString("consumerid");
                ZonedDateTime time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault());
                Array checkList = rs.getArray("checklist");
                resultList.add(new MealCheckList(id, month, checkList, consumerId, time));
            }
            return resultList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateOne(Connection connection, MealCheckList mealCheckList){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mealchecklist SET month = ?, consumerid = ?, checkList = ? WHERE id = ?");
            preparedStatement.setString(1, mealCheckList.getMonth());
            preparedStatement.setString(2, mealCheckList.getConsumerId());
            preparedStatement.setArray(3, mealCheckList.getCheckList());
            preparedStatement.setString(4, mealCheckList.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

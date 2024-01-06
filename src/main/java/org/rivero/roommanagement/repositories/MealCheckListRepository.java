package org.rivero.roommanagement.repositories;

import lombok.extern.slf4j.Slf4j;
import org.rivero.roommanagement.dtos.MealCheckListDTO;
import org.rivero.roommanagement.entities.MealCheckList;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
@Repository
@Slf4j
public class MealCheckListRepository {
    public void insert(Connection connection, MealCheckListDTO list){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mealchecklist VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, list.month());
            preparedStatement.setString(3,list.checkList() );
            preparedStatement.setString(4, list.consumerId());
            preparedStatement.setTimestamp(5,  new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MealCheckListDTO> getList(Connection connection){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mealchecklist");
            ArrayList<MealCheckListDTO> resultList = new ArrayList<MealCheckListDTO>();
            while(rs.next()){
                String id = rs.getString("id");
                String month = rs.getString("month");
                String consumerId = rs.getString("consumerid");
                ZonedDateTime time = rs.getTimestamp("created_date").toLocalDateTime().atZone(ZoneId.systemDefault());
                String checkList = rs.getString("checklist");
                resultList.add(new MealCheckListDTO(id, month, checkList, consumerId, time));
            }
            return resultList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MealCheckListDTO getOne(Connection connection, String id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mealchecklist WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String mealid = rs.getString("id");
                String month = rs.getString("month");
                String consumerid = rs.getString("consumerid");
                String checklist = rs.getString("checklist");
                ZonedDateTime time = rs.getTimestamp("created_date").toLocalDateTime().atZone(ZoneId.systemDefault());
                return new MealCheckListDTO(id, month, checklist, consumerid, time);
            }
            return null;
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public void updateOne(Connection connection, MealCheckListDTO mealCheckList){
        log.debug("{}", mealCheckList);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mealchecklist SET month = ?, consumerid = ?, checkList = ? WHERE id = ?");
            preparedStatement.setString(1, mealCheckList.month());
            preparedStatement.setString(2, mealCheckList.consumerId());
            preparedStatement.setString(3, mealCheckList.checkList());
            preparedStatement.setString(4, mealCheckList.id());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOne(Connection connection, String id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mealchecklist WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
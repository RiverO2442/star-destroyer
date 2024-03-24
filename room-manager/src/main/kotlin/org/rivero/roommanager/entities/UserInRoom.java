package org.rivero.roommanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Entity
@IdClass(UserInRoom.UserInRoomId.class)
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = PROTECTED)
public class UserInRoom {
    @Id
    String userId;
    @Id
    String roomId;

    @AllArgsConstructor
    public static class UserInRoomId implements Serializable {
        String userId;
        String roomId;
    }
}

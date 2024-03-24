package org.rivero.roommanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Table
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
    @EqualsAndHashCode
    public static class UserInRoomId implements Serializable {
        String userId;
        String roomId;
    }
}

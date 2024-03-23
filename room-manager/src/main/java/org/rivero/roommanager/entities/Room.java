package org.rivero.roommanager.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
}

package org.rivero.roommanagement.entities;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    BigDecimal moneyAmount;
    String buyerId;
    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    List<String> consumers = new ArrayList<>();
    String description;
    ZonedDateTime createdDate;

    public Receipt(String id, String name, BigDecimal moneyAmount, String buyerId, List<String> consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumers = consumerList;
        this.description = description;
        this.createdDate = createddate;
    }

    public Receipt(String id, String name, BigDecimal moneyAmount, String buyerId, String consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumers.add(consumerList);
        this.description = description;
        this.createdDate = createddate;
    }

    void accounting(List<User> userList) {
        userList.forEach(user -> {
            if (this.consumers.contains(user.getId())) {
                user.increaseUserBalance(this.moneyAmount.multiply(BigDecimal.valueOf(-1))
                        .divide(BigDecimal.valueOf(consumers.size()), RoundingMode.HALF_EVEN));
            }
        });
    }
}
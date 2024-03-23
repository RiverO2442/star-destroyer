package org.rivero.roommanager.entities;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    BigDecimal amount;
    String buyerId;
    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    String[] consumers;
    String description;
    @CreatedDate
    ZonedDateTime createdDate;
    @CreatedBy
    String createdBy;

//    public Receipt(String id, String name, BigDecimal moneyAmount, String buyerId, List<String> consumerList, String description, ZonedDateTime createddate) {
//        this.id = id;
//        this.name = name;
//        this.amount = moneyAmount;
//        this.buyerId = buyerId;
//        this.consumers = consumerList;
//        this.description = description;
//        this.createdDate = createddate;
//    }

    public Receipt(String id, String name, BigDecimal moneyAmount, String buyerId, String consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.amount = moneyAmount;
        this.buyerId = buyerId;
        this.description = description;
        this.createdDate = createddate;
    }

    void accounting(List<User> userList) {
//        userList.forEach(user -> {
//            if (this.consumers.contains(user.getId())) {
//                user.increaseUserBalance(this.amount.multiply(BigDecimal.valueOf(-1))
//                        .divide(BigDecimal.valueOf(consumers.size()), RoundingMode.HALF_EVEN));
//            }
//        });
    }
}
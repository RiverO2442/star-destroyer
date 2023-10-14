package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyConsumeEventRepository {
    void save(MoneyConsumeEvent entity);
}

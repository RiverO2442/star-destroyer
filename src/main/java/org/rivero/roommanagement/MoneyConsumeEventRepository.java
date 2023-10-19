package org.rivero.roommanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MoneyConsumeEventRepository extends JpaRepository<MoneyConsumeEvent, String> {
    MoneyConsumeEvent findByName(String name);
}

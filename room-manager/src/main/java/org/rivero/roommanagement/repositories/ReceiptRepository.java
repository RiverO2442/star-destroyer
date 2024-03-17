package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, String> {
}

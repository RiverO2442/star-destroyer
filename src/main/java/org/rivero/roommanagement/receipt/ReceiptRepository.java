package org.rivero.roommanagement.receipt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    Map<String, Receipt> receipts = new HashMap<>();

}

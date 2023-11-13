package org.rivero.roommanagement.receipt;

import java.util.HashMap;
import java.util.Map;

public interface ReceiptRepository {
    Map<String, Receipt> receipts = new HashMap<>();

}

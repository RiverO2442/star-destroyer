package org.rivero.roommanagement.receipt;


import java.math.BigDecimal;
import java.net.URL;
import java.util.Set;

public class Receipt {
    String creatorId;
    Set<String> consumerIds;
    URL imageUrl;
    BigDecimal amountToPay;
}

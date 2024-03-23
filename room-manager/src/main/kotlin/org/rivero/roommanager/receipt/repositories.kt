package org.rivero.roommanager.receipt

import org.rivero.roommanager.entities.Receipt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReceiptRepository : JpaRepository<Receipt?, String?>


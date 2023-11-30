package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class SaleOperation : OperationType() {


    override val type = "Sale"
    override fun realizeAction(operation: Operation) : Operation {

            var updateOperation : Operation = operation
        if (operation.userInterested != null) {
            updateOperation = (operation.userInterested?.makeTransfer(operation))!!
            println("Confirm reception")
        }
       return updateOperation
    }

}

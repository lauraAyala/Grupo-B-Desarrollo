package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class SaleOperation : OperationType() {


    val typeOperation = "Sale"
    override fun realizeAction(operation: Operation) : Operation {


        if (operation.userInterested != null) {
            operation.userInterested?.makeTransfer(operation.userCreated!!)
            println("Confirm reception")
        }
       return operation
    }

}

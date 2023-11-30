package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class BuyOperation : OperationType() {

    override val type = "Buy"
    override fun realizeAction(operation: Operation) : Operation{

          var operationUpdate = operation
        if (operation.userCreated != null) {
            operationUpdate= (operation.userCreated!!.confirmPurchase(operation))
            println("Make the transfer")

        }

       return operationUpdate
    }


}

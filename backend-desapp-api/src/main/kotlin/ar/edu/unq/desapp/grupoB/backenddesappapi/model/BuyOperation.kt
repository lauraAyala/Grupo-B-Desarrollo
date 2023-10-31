package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class BuyOperation : OperationType() {

    val typeOperation = "Buy"
    override fun realizeAction(operation: Operation) {

        var direction = operation.userCreated!!.directionWallet
        if (operation.userCreated != null) {
            operation.userCreated!!.updatReception()
            operation.updateDirection(direction!!)
            println("Make the transfer")
        }

    }


}

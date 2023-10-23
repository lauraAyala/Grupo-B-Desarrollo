package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class BuyOperation : OperationType() {

    val typeOperation = "Buy"
    override fun realizeAction(operation: Operation) {
        TODO("Not yet implemented")
    }

}

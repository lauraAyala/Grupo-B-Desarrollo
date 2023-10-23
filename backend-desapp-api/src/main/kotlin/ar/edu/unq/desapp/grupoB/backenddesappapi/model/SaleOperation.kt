package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity


class SaleOperation : OperationType() {


    val typeOperation = "Sale"
    override fun realizeAction(operation: Operation) {

        var cvuDirection = operation.userCreated?.cvu
        if (cvuDirection != null) {
            operation.updateDirection(cvuDirection)
        }
        operation.userCreated?.updatReception()
    }

}

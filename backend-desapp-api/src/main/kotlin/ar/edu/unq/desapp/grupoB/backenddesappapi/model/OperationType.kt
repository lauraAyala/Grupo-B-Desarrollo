package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

open class OperationType {

   open val type: String? =null
     open fun realizeAction(operation: Operation) : Operation {
         return operation
     }

}

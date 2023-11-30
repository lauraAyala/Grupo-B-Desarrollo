package ar.edu.unq.desapp.grupoB.backenddesappapi.model

class CancelOperation : OperationType() {

    override val type = "Cancel"
    override fun realizeAction(operation: Operation) : Operation {

        var operationCurrent = operation
        var user = operation.userCreated
        if(operation.userCreated != null){
            operationCurrent = user!!.sustractPoint(operation)!!
        }

        return  operationCurrent
    }

}

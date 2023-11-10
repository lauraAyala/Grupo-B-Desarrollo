package ar.edu.unq.desapp.grupoB.backenddesappapi.model

class CancelOperation : OperationType() {

    override val type = "Cancel"
    override fun realizeAction(operation: Operation) : Operation {

        var user = operation.userCreated


        return  user!!.sustractPoint(operation)
    }

}

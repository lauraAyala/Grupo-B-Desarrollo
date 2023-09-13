package model

class SaleOperation : OperationType() {

    override fun realizeAction(operation: Operation) {

        var cvuDirection = operation.userCreated?.cvu
        if (cvuDirection != null) {
            operation.updateDirection(cvuDirection)
        }
        operation.userCreated?.updatReception()
    }

}

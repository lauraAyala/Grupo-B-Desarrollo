package model

class Operation() {

    var userCreated: User? =null
    var cantNominal: Int?=null
    var crypto: Crypto? = null
    var amount: Double? = null
    var operationType: OperationType?=null
    var userInterested:User?= null
    var direction:String? =null

    constructor(user: User, cantNominal:Int, crypto: Crypto, amount:Double, operationType: OperationType ) :this(){

        this.userCreated=user
        this.cantNominal=cantNominal
        this.crypto = crypto
        this.amount = amount
        this.operationType = operationType

    }

    fun updateUserInterested(user: User){

        this.userInterested=user
    }

    fun processAction() {

        this.operationType?.realizeAction(this)
    }

    fun updateDirection(direction:String){

        this.direction = direction
    }
}

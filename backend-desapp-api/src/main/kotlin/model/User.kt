package model

import java.io.Serializable

open class User () : Serializable{

    var name:String? = null
    var lastName:String? = null
    var email:String? = null
    var direction:String?= null
    var password:String? = null
    var cvu:String? = null
    var directionWallet:String?= null
    var cryotoActives:ArrayList<Crypto> = ArrayList()
    var operation:ArrayList<Operation> = ArrayList()
    var countOperations = 0
    var reception = false

    constructor(nameU: String, lastNameU:String, emailU: String, directionU:String, passwordU:String, cvu:String, directionWalletU: String) : this(){

        this.name=nameU
        this.lastName =lastNameU
        this.email = emailU
        this.direction = directionU
        this.password = passwordU
        this.cvu = cvu
        this.directionWallet = directionWalletU

    }

    fun addCrypto(crypto: Crypto){

        this.cryotoActives.add(crypto)
    }


    fun saleCrypto(crypto:Crypto,cantNominal:Int, amount:Double):User{

        var operationType = SaleOperation()
        var operation = Operation(this,cantNominal,crypto,amount,operationType)
        this.operation.add(operation)
        //this.countOperations +=1
        operation.processAction()
        return this
    }

    fun buyCrypto(crypto:Crypto,cantNominal:Int, amount:Double):User{

        var operationType = BuyOperation()
        var operation = Operation(this,cantNominal,crypto,amount,operationType)
        this.operation.add(operation)
        this.countOperations +=1
        return this
    }

    fun canceledCrypto(crypto:Crypto,cantNominal:Int, amount:Double):User{

        var operationType = CancelOperation()
        var operation = Operation(this,cantNominal,crypto,amount,operationType)
        this.operation.add(operation)
        this.countOperations +=1
        return this
    }

    fun updatReception() {
        this.reception = true
        this.countOperations +=1
    }


}
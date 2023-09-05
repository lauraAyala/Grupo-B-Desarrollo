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




}
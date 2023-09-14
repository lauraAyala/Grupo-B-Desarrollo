package model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
open class User () : Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
    @Column
    var name:String? = null
    @Column
    var lastName:String? = null
    @Column
    var email:String? = null
    @Column
    var direction:String?= null
    @Column
    var password:String? = null
    @Column
    var cvu:String? = null
    @Column
    var directionWallet:String?= null
    @Transient
    var cryotoActives:ArrayList<Crypto> = ArrayList()
    @JsonIgnore
    @OneToMany( fetch = FetchType.EAGER , mappedBy = "userCreated", cascade = arrayOf(CascadeType.ALL))
    var operation:MutableList<Operation> = ArrayList()
    @Column
    var countOperations = 0
    @Transient
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
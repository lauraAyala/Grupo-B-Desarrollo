package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import net.bytebuddy.asm.Advice.AllArguments
import java.io.Serializable
import javax.persistence.*
import kotlin.jvm.Transient
@Entity
@Table(name="userT")
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
    var operations:MutableList<Operation> = ArrayList()
    @Column
    var countOperations = 0
    @Transient
    var reception = false
    @Transient
    var directionForTransfer:String?=null
    @Transient
    var point = 0


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


    fun saleCrypto(operation: Operation): Operation {


        this.operations.add(operation)
        //this.countOperations +=1

        return operation.processAction()
    }

    fun buyCrypto(operation: Operation): Operation{

        this.operations.add(operation)
        //this.countOperations +=1
        var operationUpdate = operation.updateUserCreated(this)
        return operationUpdate.processAction()
    }

    fun canceledCrypto(operation: Operation): Operation {

        this.operations.add(operation)
       // this.countOperations +=1
        return operation.processAction()
    }

    fun updatReception() {

        this.reception = true
        this.countOperations +=1
    }

    fun process(operation: Operation) {
        operation.processAction()
    }

    fun makeTransfer(user: User){
        user.updatReception()
        this.directionForTransfer = user.cvu

    }

    fun sustractPoint(operation: Operation): Operation {

        if(this.point != 0) {
            this.point -= 20
        }
        return operation.updateUserCreated(this)
    }
}

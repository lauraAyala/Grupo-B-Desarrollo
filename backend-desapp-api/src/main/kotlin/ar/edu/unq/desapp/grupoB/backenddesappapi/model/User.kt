package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
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
    var cryotoActives:ArrayList<CryptoQuote> = ArrayList()
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
    @Transient
    var dolarPrice =0.0


    constructor(nameU: String, lastNameU:String, emailU: String, directionU:String, passwordU:String, cvu:String, directionWalletU: String) : this(){

        this.name=nameU
        this.lastName =lastNameU
        this.email = emailU
        this.direction = directionU
        this.password = passwordU
        this.cvu = cvu
        this.directionWallet = directionWalletU

    }

    fun addCrypto(crypto: CryptoQuote){

        this.cryotoActives.add(crypto)
    }


    fun saleCrypto(operation: Operation): Operation {

        this.operations.add(operation)
        return operation.processAction()
    }

    fun buyCrypto(operation: Operation): Operation {

        this.operations.add(operation)
        var operationUpdate = operation.updateUserCreated(this)
        return operationUpdate!!.processAction()
    }

    fun canceledCrypto(operation: Operation): Operation {
        
        return operation.processAction()
    }

    fun updatReception() {

        this.reception = true
        this.countOperations +=1
    }

    fun process(operation: Operation) {
        operation.processAction()
    }

    fun makeTransfer(operation: Operation) : Operation {

        var  user = this.sumPoints(operation)
        this.directionForTransfer = user!!.cvu
        user.updatReception()
        var updateOperation : Operation = operation.updateUserCreated(user)
        return  updateOperation
    }


    fun sustractPoint(operation: Operation): Operation? {

        if(this.point != 0) {
            this.point -= 20
        }
        return operation.updateUserCreated(this)
    }

    private fun sumAmountArgOperations(operationsOfCrypto: ArrayList<Operation>): Double {

        var amountArg = 0.0
        for (operation in operationsOfCrypto){

            amountArg += operation.amount!!

        }

        return amountArg

    }

    private fun sumAmountDolarOperations(operationsOfCrypto: ArrayList<Operation>): Double {

        var amountDolar = 0.0
        for (operation in operationsOfCrypto){

            amountDolar += (operation.amount!! *  this.dolarPrice)

        }

        return amountDolar

    }

    private fun sumCantNominalCrypto(operationsOfCrypto: ArrayList<Operation>): Int {

        var cantNominal = 0
        for (operation in operationsOfCrypto){

            cantNominal += operation.cantNominal!!

        }

        return cantNominal

    }

    fun reportVolumOperatorOfCrypto(crypto: CryptoQuote, date1: LocalDate, date2 : LocalDate): ReportCrypto{

        var operationsOfCrypto : ArrayList<Operation> = ArrayList()
        for (operation in this.operations){

            if( (operation.operativeDate!!.toLocalDate() <= date1 || operation.operativeDate!!.toLocalDate() <= date2) && operation.isTypeCrypto(crypto.typeCrypto)){

                operationsOfCrypto.add(operation)
            }
        }

        var operativeDate = LocalDateTime.now()
        var amountArg:Double = this.sumAmountArgOperations(operationsOfCrypto)
        var amountDolar: Double = this.sumAmountDolarOperations(operationsOfCrypto)
        var cryptoType = crypto.typeCrypto
        var cantNominal = this.sumCantNominalCrypto(operationsOfCrypto)
        var currentQuote = 1000.00

        return ReportCrypto(operativeDate,amountDolar,amountArg,cryptoType!!,cantNominal,currentQuote,amountArg)

    }

    fun sumPoints(operation: Operation): User? {

        var  user = operation.userCreated
        var date = operation.operativeDate
        var currentDate: LocalDateTime = LocalDateTime.now()
        var minuteDifference = ChronoUnit.MINUTES.between(currentDate,date)
        if (date!!.toLocalDate() == currentDate.toLocalDate() && date.hour == currentDate.hour && minuteDifference <= 30) {

            user!!.point += 10
        } else {

            user!!.point += 5

        }
        return user
    }

    fun confirmPurchase(operation: Operation): Operation {

        var direction = operation.userCreated!!.directionWallet
        operation.userCreated!!.updatReception()
        operation.updateDirection(direction!!)
        var user :User? =  operation.userCreated!!.sumPoints(operation)
        operation.updateUserCreated(user!!)
        return operation
    }

    fun updatePoints(point: Int) {

        this.point =+ point
    }

}

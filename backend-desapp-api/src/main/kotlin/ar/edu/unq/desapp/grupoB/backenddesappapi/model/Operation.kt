package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table
class Operation() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
    @Column
    var nameOperation : String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCreated")
    var userCreated: User? =null
    @Column
    var cantNominal: Int?=null
    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto")*/
    @Transient
    var crypto: Crypto? = null
    @Column
    var amount: Double? = null
    @Transient
    var operationType: OperationType?=null
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userInterested_id")
    var userInterested :User? = null
    @Transient
    var direction:String? =null
    @Column
    var operativeDate: LocalDateTime? = null

    constructor(user: User?, cantNominal:Int, crypto: Crypto, amount:Double, operationType: OperationType) :this(){

        this.userCreated=user
        this.cantNominal=cantNominal
        this.crypto = crypto
        this.amount = amount
        this.operationType = operationType
        this.nameOperation = operationType.type
        this.userInterested = null
        this.operativeDate = LocalDateTime.now()
    }

    fun updateUserInterested(user: User): Operation? {

        this.userInterested=user

        return this
    }
    fun processAction() : Operation {

        return (this.operationType!!.realizeAction(this))

    }

    fun updateDirection(direction:String){

        this.direction = direction
    }

    fun updateUserCreated(user: User): Operation {

        this.userCreated = user

        return this
    }

    fun updateOperativeDate(date: LocalDateTime?) {

         this.operativeDate = date
    }
    
    fun isTypeCrypto(typeCrypto: TypeCrypto?): Boolean {

        return crypto!!.isTypeCrypto(typeCrypto)

    }
}

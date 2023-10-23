package ar.edu.unq.desapp.grupoB.backenddesappapi.model

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto")
    var crypto: Crypto? = null
    @Column
    var amount: Double? = null
    @Transient
    var operationType: OperationType?=null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInterested_id")
    var userInterested: User?= null
    @Column
    var direction:String? =null

    constructor(user: User, cantNominal:Int, crypto: Crypto, amount:Double, operationType: OperationType) :this(){

        this.userCreated=user
        this.cantNominal=cantNominal
        this.crypto = crypto
        this.amount = amount
        this.operationType = operationType
        this.nameOperation = operationType.type
        this.direction = user.directionWallet

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

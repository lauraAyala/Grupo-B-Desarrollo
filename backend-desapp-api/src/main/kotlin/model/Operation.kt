package model

import javax.persistence.*

@Entity
class Operation() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
    @JoinColumn(name = "userCreate_id")
    var userCreated: User? =null
    @Column
    var cantNominal: Int?=null
    @Column
    var crypto: Crypto? = null
    @Column
    var amount: Double? = null
    @Column
    var operationType: OperationType?=null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInterested_id")
    var userInterested:User?= null
    @Column
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

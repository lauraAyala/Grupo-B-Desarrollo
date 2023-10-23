package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
open class Crypto()  : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
    @Column
    var userCreate: User?=null
    @Column
    var quote:Double?= null
    @Column
    var operativeDate:LocalDateTime?= null
    @Column
    var typeCrypto: TypeCrypto? = null
    @JsonIgnore
    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy =  "crypto")
    var operations:MutableList<Operation> = ArrayList()

        constructor(user : User, quote : Double, operativeDate : LocalDateTime, typeCrypto: TypeCrypto) : this(){

            this.userCreate = user
            this.quote = quote
            this.operativeDate = operativeDate
            this.typeCrypto = typeCrypto
        }
}
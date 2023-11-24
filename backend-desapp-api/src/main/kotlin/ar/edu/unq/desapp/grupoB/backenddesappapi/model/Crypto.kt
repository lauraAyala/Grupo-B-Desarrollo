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
    var typeCrypto: TypeCrypto? = null
    @Transient
    var operations:MutableList<Operation> = ArrayList()

        constructor( quote : Double, typeCrypto: TypeCrypto) : this(){

            this.quote = quote
            this.typeCrypto = typeCrypto
        }

    fun isTypeCrypto(typeCrypto: TypeCrypto?): Boolean {

        return this.typeCrypto == typeCrypto

    }
}
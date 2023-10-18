package ar.edu.unq.desapp.grupoB.backenddesappapi.model

import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
open class Crypto {

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
}
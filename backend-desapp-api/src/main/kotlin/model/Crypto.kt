package model

import java.time.LocalDateTime

open class Crypto {

    var userCreate:User?=null
    var quote:Double?= null
    var operativeDate:LocalDateTime?= null
    var typeCrypto: TypeCrypto? = null
}